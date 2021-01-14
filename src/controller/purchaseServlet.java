package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BillDAO;
import DAO.CartDAO;
import model.Account;
import model.Book;
import model.Cart;

/**
 * Servlet implementation class purchaseServlet
 */
@WebServlet(urlPatterns = {"/purchase", "/confirm"})
public class purchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartDAO cartDAO;
    private BillDAO billDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public purchaseServlet() {
        super();
        cartDAO = new CartDAO();
        billDAO = new BillDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ((Account) session.getAttribute("account") == null) {
			System.out.println("NO account");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login");
			dispatcher.forward(request, response);
		}
		Cart tmp = (Cart) session.getAttribute("Cart");
		List<model.Dictionary<Book, Integer>> l = tmp.getListBook();
		request.setAttribute("lBook", l);
		String action = request.getServletPath();
		switch (action) {
			case "/purchase":{
				RequestDispatcher dispatcher = request.getRequestDispatcher("purchaseConfirm.jsp");
				dispatcher.forward(request, response);
				break;
			}
			default:{
				confirm(request,response,tmp);
			}
		}
	}

	private void confirm(HttpServletRequest request, HttpServletResponse response,Cart tmp) throws IOException {
		HttpSession session = request.getSession();
		String detail = request.getParameter("detail");
		String shipper = request.getParameter("shipper");
		double shipping = (shipper.equals("FastShip") ? 10 : 20);
		System.out.println(detail + " " + shipper + " " + shipping );
		try {
			cartDAO.updateStatus(tmp.getId());
			billDAO.newBill(tmp.getId(), shipper, shipping, detail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cart c = (Cart) session.getAttribute("Cart");
		double money = c.getMoney();
		session.setAttribute("moneyBook", money);
		session.setAttribute("moneyShip", shipping);
		money += shipping;
		session.setAttribute("money", money);
		response.sendRedirect("purchase.jsp");
		session.removeAttribute("Cart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
