package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Book;
import model.Cart;
import model.Dictionary;

public class CartDAO {
	
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
    
    public Cart getCart(int idAccount) throws SQLException {
    	String sql = "SELECT * FROM cart WHERE id_account = ? AND status = 'not';";
    	Cart c = new Cart();
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    			preparedStatement.setInt(1, idAccount);
    			System.out.println(preparedStatement);
    			ResultSet resultSet = preparedStatement.executeQuery();
    			if(!resultSet.next()) {
    				String sql2 = "INSERT INTO cart " + "(id_account,status) values" + "(?,'not');";
    				PreparedStatement ps = connection.prepareStatement(sql2,PreparedStatement.RETURN_GENERATED_KEYS);
    				ps.setInt(1, idAccount);
    				System.out.println(idAccount);
    				c.setIdAccount(idAccount);
    				System.out.println(ps);
    				ps.execute();
    				ResultSet rs = ps.getGeneratedKeys();
    				if(rs.next()) {
    					int id = rs.getInt(1);
    					System.out.println(id);
    					c.setId(id);
    				}
    				else {
    					System.out.println("not");
    				}
    			}
    			else {
    				System.out.println("has");
    				BookSoldDAO bookS = new BookSoldDAO();
    				c.setId(resultSet.getInt("id"));
    				c.setIdAccount(resultSet.getInt("id_account"));
    				ArrayList<Dictionary<Book, Integer>> listBook = bookS.getBookInCart(c.getId());
    				double money = 0;
    				c.setListBook(listBook);
    			}
    	}
    	System.out.println(c.getId());
    	return c;
    }
    
    public void updateStatus(int idCart) throws SQLException {
    	String sql = "UPDATE cart SET status = 'oke' WHERE id = ?";
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    		preparedStatement.setInt(1, idCart);
    		preparedStatement.execute();
    	}
    }
    
    public Cart getCartSold(int idCart) throws SQLException {
    	String sql = "SELECT * FROM cart,booksold WHERE cart.id = ? AND booksold.id_cart = cart.id";
    	Cart c = new Cart();
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    		preparedStatement.setInt(1, idCart);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()) {
    			c.setId(resultSet.getInt("cart.id"));
    			c.setIdAccount(resultSet.getInt("cart.id_account"));
    			int id = resultSet.getInt("booksold.id_book");
    			String name = resultSet.getString("booksold.name");
    			String author = resultSet.getString("booksold.author");
    			double price  = resultSet.getDouble("booksold.price");
    			Book tmp = new Book(id,name,author,price);
    			c.addBook(tmp);
    		}
    		for(Dictionary<model.Book, Integer> tmp : c.getListBook()) {
				System.out.println(tmp.getKey().getName() + " " + tmp.getValue());
			}
    		return c;
    	}
    }
    


}
