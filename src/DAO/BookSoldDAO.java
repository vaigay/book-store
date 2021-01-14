package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Book;
import model.Dictionary;

public class BookSoldDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/ex3";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    
    protected Connection getConnection(){
    	Connection connection = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return connection;
    }
    
    public ArrayList<Dictionary<Book, Integer>>  getBookInCart(int idCart) throws SQLException {
    	ArrayList<Dictionary<Book, Integer>> listBook = new ArrayList<Dictionary<Book, Integer>>();
    	String sql = "SELECT * FROM booksold where id_cart = ?";
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    			preparedStatement.setInt(1, idCart);
				ResultSet resultSet = preparedStatement.executeQuery();
				System.out.println(preparedStatement);
				while(resultSet.next()) {
					int id = resultSet.getInt("id_book");
	    			String name = resultSet.getString("name");
	    			String author = resultSet.getString("author");
	    			double price = resultSet.getDouble("price");
	    			Book tmp = new Book(id,name,author,price);
	    			int mark  = 0;
	    			for(Dictionary<Book, Integer> tmpBook : listBook) {
	    				if(tmpBook.getKey().getId() == id) {
	    					tmpBook.setValue(tmpBook.getValue() + 1);
	    					mark = 1;
	    					break;
	    				}
	    			}
	    			if(mark == 0)
	    				listBook.add(new Dictionary<Book, Integer>(tmp, 1));
				}
    	}
		return listBook; 
    }
    
    public void addBookSold(int idCart,int idBook) throws SQLException {
    	BookDAO bookDAO = new BookDAO();
    	Book tmp = bookDAO.searchBook(idBook);
    	String sql = "INSERT INTO booksold (id_book, id_cart, name, author, price) values (?,?,?,?,?);";
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    		preparedStatement.setInt(1, idBook);
    		preparedStatement.setInt(2, idCart);
    		preparedStatement.setString(3, tmp.getName());
    		preparedStatement.setString(4, tmp.getAuthor());
    		preparedStatement.setDouble(5, tmp.getPrice());
    		preparedStatement.execute();
    	}
    }
    
    public void deleteBookSold(int idCart,int idBook) throws SQLException {
    	String sql = "DELETE FROM booksold WHERE id_cart = ? AND id_book = ?;";{
    		try(Connection connection = getConnection();
        			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    			preparedStatement.setInt(1, idCart);
        		preparedStatement.setInt(2, idBook);
        		System.out.println(preparedStatement);
        		preparedStatement.execute();
    		}
    	}
    }
}
