package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AccountDAO;
import model.Account;
import model.Address;
import model.Customer;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet(urlPatterns = {"/register","/check-register"})
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO;
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	accountDAO = new AccountDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
    	doPost(request,response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		switch (action){
		case "/register":{
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
			}
		default:{
			createUser(request,response);
		}
		}
	}
	private void createUser(HttpServletRequest request, HttpServletResponse response) {
		String n = request.getParameter("name");
		String us = request.getParameter("username");
		String p = request.getParameter("password");
		String cou = request.getParameter("country");
		String ci = request.getParameter("city");
		String d = request.getParameter("detail");
		Address address = new Address(cou,ci,d);
		Customer customer = new Customer(n,address);
		Account account = new Account(us,p,customer);
		System.out.println(n+" "+us+" "+p+" "+" "+cou+" "+" "+ci+" "+d);
		try {
			int check = accountDAO.insertAccount(account);
			if(check == -1) {
				return;
			}
			account.setId(check);
			HttpSession session = request.getSession();
			session.setAttribute("name", n);
			session.setAttribute("account", account);
			RequestDispatcher dispatcher = request.getRequestDispatcher("registersuccess.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
