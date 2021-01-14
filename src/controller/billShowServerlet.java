package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import DAO.BillDAO;
import model.Bill;

/**
 * Servlet implementation class billShowServerlet
 */
@WebServlet("/bill-show")
public class billShowServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BillDAO billDAO; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public billShowServerlet() {
        super();
        billDAO = new BillDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("account") == null ) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login");
			dispatcher.forward(request, response);
		}
		Account tmp = (Account) session.getAttribute("account");
		try {
			ArrayList<Bill> listBill = (ArrayList<Bill>) billDAO.allBill(tmp.getId());
			request.setAttribute("listBill",listBill);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListBill.jsp");
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
