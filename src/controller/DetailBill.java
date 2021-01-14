package controller;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BillDAO;
import DAO.CartDAO;
import model.Bill;
import model.Cart;
import model.Dictionary;

/**
 * Servlet implementation class DetailBill
 */
@WebServlet("/DetailBill")
public class DetailBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartDAO cartDAO;
    private BillDAO billDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailBill() {
        super();
        cartDAO = new CartDAO();
        billDAO = new BillDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Cart cart = cartDAO.getCartSold(id);
			for(Dictionary<model.Book, Integer> tmp : cart.getListBook()) {
				System.out.println(tmp.getKey().getName() + " " + tmp.getValue());
			}
			Bill bill = billDAO.getBill(id);
			double money = bill.getShipping() + cart.getMoney();
			HttpSession session = request.getSession();
			session.setAttribute("money", money);
			session.setAttribute("moneyBook", cart.getMoney());
			session.setAttribute("lBook", cart.getListBook());
			session.setAttribute("bill", bill);
			RequestDispatcher dispatcher = request.getRequestDispatcher("detailBill.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
