package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Customer;

public class AccountDAO {
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
    
    public int insertAccount(Account account) throws Exception {
    	String sql_insert = "INSERT INTO account" + "(id_customer, username, password) values" + "(?, ?, ?);";
    	CustomerDAO customerDAO = new CustomerDAO();
    	int id = customerDAO.insertCustomer(account.getCustomer());
    	if(id == 0)
    		return -1;
//    	System.out.println(sql_insert);
    	try (Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(sql_insert,PreparedStatement.RETURN_GENERATED_KEYS);	){
    		preparedStatement.setInt(1,id);
    		preparedStatement.setString(2, account.getUsername() );
    		preparedStatement.setString(3, account.getPassword() );
    		preparedStatement.executeUpdate();
    		ResultSet resultSet = preparedStatement.getGeneratedKeys();
    		if(resultSet.next()) {
    			return resultSet.getInt(1);
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return -1;
    	}
    	return -1;
    }
    
    public Account checkAccount(Account account) throws Exception {   	
    	String sql_check = "select * from account where username = ? and password = ?";
    	try (Connection connection = getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(sql_check);){
    		preparedStatement.setString(1, account.getUsername());
    		preparedStatement.setString(2, account.getPassword());
    		System.out.println(preparedStatement);
    		ResultSet rs = preparedStatement.executeQuery();
    		CustomerDAO customerDAO = new CustomerDAO();
    		if(rs.next()) {
    			account.setId(rs.getInt("id"));    			
    			Customer ct = customerDAO.searchCustomer(rs.getInt("id_customer"));
    			account.setCustomer(ct);
    		}
    		else
    			return null;
    		return account;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
