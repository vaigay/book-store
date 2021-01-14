package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import model.Address;
import model.Customer;

public class CustomerDAO {
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
    
    public int insertCustomer(Customer customer) throws Exception {
    	String sql_insert = "INSERT INTO customer" + "( name, country, city, detail) values" + "(?, ?, ?, ?);";
//    	System.out.println(sql_insert);
    	try (Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(sql_insert,PreparedStatement.RETURN_GENERATED_KEYS);	){
    		System.out.println("test");
    		preparedStatement.setString(1, customer.getName());
    		preparedStatement.setString(2, customer.getAddress().getCountry() );
    		preparedStatement.setString(3, customer.getAddress().getCity());
    		preparedStatement.setString(4, customer.getAddress().getDetail());
    		preparedStatement.executeUpdate();
    		ResultSet rs = preparedStatement.getGeneratedKeys();
    		if(rs.next()) {
    			int id = rs.getInt(1);
        		System.out.println("id: "+ id);
        		return id;
    		}
    		return 0;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return 0;
    	}
    }
    
    public Customer searchCustomer(int id) throws Exception {
    	String sql_insert = "SELECT * FROM customer WHERE id = ?";
//    	System.out.println(sql_insert);
    	try (Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(sql_insert);	){
    		preparedStatement.setInt(1, id);
    		ResultSet rs = preparedStatement.executeQuery();
    		if(rs.next()) {
    			Customer customer = new Customer();
    			customer.setId(rs.getInt("id"));
    			customer.setName(rs.getString("name"));
    			String country = rs.getString("country");
    			String city = rs.getString("city");
    			String detail = rs.getString("detail");
    			customer.setAddress(new Address(country,city,detail));
    			return customer;
    		}
    		return null;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
}
