package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import DAO.BookDAO;
import DAO.BookSoldDAO;
import DAO.CartDAO;
import model.Cart;
import model.Account;
import model.Book;

import java.util.*;

/**
 * Servlet implementation class chooseItem
 */
@WebServlet(urlPatterns = { "/chooseItem", "/delete", "/cart-show" })
public class choosenItemServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;
	private CartDAO cartDAO;
	private BookSoldDAO bookSoldDAO;

	public void init() {
		bookDAO = new BookDAO();
		cartDAO = new CartDAO();
		bookSoldDAO = new BookSoldDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public choosenItemServerlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if ((Account) session.getAttribute("account") == null) {
			System.out.println("NO account");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login");
			dispatcher.forward(request, response);
		}
		Account tmp = (Account) session.getAttribute("account");
		String action = request.getServletPath();
		switch (action) {
		case "/delete": {
			try {
				deleteBook(request, response,tmp);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/cart-show": {
			try {
				cartShow(request, response, tmp);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default: {
			addBook(request, response,tmp);
		}
		}
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response, Account tmp)
			throws ServletException, IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id delete" + id);
		HttpSession session = request.getSession();
		Cart c = (Cart) session.getAttribute("Cart");
		for (int i = 0; i < c.getListBook().size(); i++) {
			if (c.getListBook().get(i).getKey().getId() == id) {
				bookSoldDAO.deleteBookSold(c.getId(), id);
				c.getListBook().remove(i);
				break;
			}
		}
		session.setAttribute("Cart", c);
		cartShow(request, response,tmp);
	}

	private void cartShow(HttpServletRequest request, HttpServletResponse response, Account tmp)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		Cart c = (Cart) session.getAttribute("Cart");
		if (c == null) {
			c = cartDAO.getCart(tmp.getId());
			session.setAttribute("Cart", c);
		}
		System.out.print(c.getId());;
		List<model.Dictionary<Book, Integer>> l = c.getListBook();
		request.setAttribute("lBook", l);
//		for (model.Dictionary<Book, Integer> e : l) {
////			System.out.println(e.getId()+" "+e.getName());
//			moneyBook += e.getValue() * e.getKey().getPrice();
//		}
//		session.setAttribute("moneyBook", moneyBook);
		System.out.println(c.getMoney());
		RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
		dispatcher.forward(request, response);

	}

	private void addBook(HttpServletRequest request, HttpServletResponse response, Account tmp)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		try {
			Book book = bookDAO.searchBook(id);
			HttpSession session = request.getSession();
			System.out.println("Has account???");
			Cart c = new Cart();
			System.out.println(tmp.getId());
			if ((Cart) session.getAttribute("Cart") == null) {
				System.out.println("null");
				c = cartDAO.getCart(tmp.getId());
			} else {
				c = (Cart) session.getAttribute("Cart");
			}
			bookSoldDAO.addBookSold(c.getId(), id);
			c.addBook(book);
			session.setAttribute("Cart", c);

			cartShow(request, response, tmp);
		} catch (SQLException e) {
			System.out.println("Exception");
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
