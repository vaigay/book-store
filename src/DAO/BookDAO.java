package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Book;

public class BookDAO {
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
    
    public List<Book> listBook(String x) throws SQLException{
    	List<Book> listB = new ArrayList<>();
    	String sql = "SELECT * FROM book WHERE name LIKE ?";
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    		preparedStatement.setString(1,"%"+x+"%");
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()) {
    			int id = resultSet.getInt("id");
    			String name = resultSet.getString("name");
    			String author = resultSet.getString("author");
    			double price = resultSet.getDouble("price");
    			Book tmp = new Book(id,name,author,price);
    			listB.add(tmp);
    		}
    	}
    	return listB;
    }
    
    public Book searchBook(int id) throws SQLException {
    	String sql = "SELECT * FROM book WHERE id = ?";
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    		preparedStatement.setInt(1,id);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		System.out.println(preparedStatement);
    		if(resultSet.next()) {
    			String name = resultSet.getString("name");
    			String author = resultSet.getString("author");
    			double price = resultSet.getDouble("price");
    			Book tmp = new Book(id,name,author,price);
    			return tmp;
    		}
    		else
    			return null;
    	}
    }
    
    public List<Book> listBookAll() throws SQLException{
    	List<Book> listB = new ArrayList<>();
    	String sql = "SELECT * FROM book ";
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()) {
    			int id = resultSet.getInt("id");
    			String name = resultSet.getString("name");
    			String author = resultSet.getString("author");
    			double price = resultSet.getDouble("price");
    			Book tmp = new Book(id,name,author,price);
    			listB.add(tmp);
    		}
    	}
    	return listB;
    }
    
}
