package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BookDAO;
import model.Book;

import java.util.*;

/**
 * Servlet implementation class listBookServlet
 */
@WebServlet("/listBookServlet")
public class listBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    public void init() {
    	bookDAO = new BookDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(request.getParameter("searchingbook") == null) {
				List <Book> lBook = bookDAO.listBookAll();
				request.setAttribute("listBook",lBook);
				RequestDispatcher dispatchere = request.getRequestDispatcher("home.jsp");
				dispatchere.forward(request, response);
				return;
			}
			List <Book> lBook = bookDAO.listBook(request.getParameter("searchingbook"));
			request.setAttribute("listBook",lBook);
			System.out.println(lBook.size());
			for(Book e : lBook) {
				System.out.println(e.getId()+" "+e.getName()+" "+e.getAuthor()+" "+e.getPrice());
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
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
