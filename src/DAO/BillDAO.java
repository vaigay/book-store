package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Bill;
import model.Cart;

public class BillDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/ex3";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    private CartDAO cartDAO;
    
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
    
    public void newBill(int idCart, String shipper, double shipping, String detail) throws SQLException {
    	String sql = "INSERT INTO bill (id_cart, shipper, shipping, detail) values (?, ?, ?, ?)";
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    			preparedStatement.setInt(1, idCart);
    			preparedStatement.setString(2, shipper);
    			preparedStatement.setDouble(3, shipping);;
    			preparedStatement.setString(4, detail);
    			preparedStatement.execute();
    	}
    }
    
    public List<Bill> allBill(int idAccount) throws SQLException {
    	String sql = "SELECT cart.id, bill.id, bill.day FROM bill, cart WHERE cart.id_account = ? AND status = 'oke' AND bill.id_cart = cart.id";
    	ArrayList<Bill> listBill = new ArrayList<>();
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    			preparedStatement.setInt(1, idAccount);
    			ResultSet resultSet = preparedStatement.executeQuery();
    			while(resultSet.next()) {
    				Bill tmp = new Bill();
    				tmp.setId(resultSet.getInt("bill.id"));
    				tmp.setId_cart(resultSet.getInt("cart.id"));
    				tmp.setDay(resultSet.getTimestamp("day"));
    				listBill.add(tmp);
    			}
    	}
    	return listBill;
    }
    
    public Bill getBill(int idCart) throws SQLException {
    	String sql = "SELECT* FROM bill WHERE id_cart = ?";
    	Bill bill = new Bill();
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(sql);){
    		preparedStatement.setInt(1, idCart);
    		ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				bill.setId(resultSet.getInt("id"));
				bill.setId_cart(idCart);
				bill.setShipper(resultSet.getString("shipper"));
				bill.setShipping(resultSet.getDouble("shipping"));
				bill.setDay(resultSet.getTimestamp("day"));
				bill.setDetail(resultSet.getString("detail"));
			}
    	}
    	return bill;
    }
}
