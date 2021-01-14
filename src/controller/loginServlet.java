package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountDAO;
import model.Account;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = { "/login", "/"})
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO;
    
    public void init() {
    	accountDAO = new AccountDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession sessionc = request.getSession();
		if((Account) sessionc.getAttribute("account") != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        	dispatcher.forward(request, response);
        	System.out.println( "has account");
		}
		doPost(request,response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account = new Account();
		account.setUsername(request.getParameter("username"));
		account.setPassword(request.getParameter("password"));
		try {
			account = accountDAO.checkAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(account != null) {
    		HttpSession session = request.getSession();
    		session.setAttribute("account", account);
    		System.out.println( account.getCustomer().getName());
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/listBookServlet");
        	dispatcher.forward(request, response);
    	}
    	else {
    		response.setContentType("text/html");
        	PrintWriter printWriter = response.getWriter();
        	printWriter.println("<font color='red'><b>You have entered incorrect password</b></font>");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        	dispatcher.include(request, response);
        	printWriter.close();
    	}
	}

}
