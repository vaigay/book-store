package model;

public class Account {
	public Account(String username, String password, Customer customer) {
		super();
		this.username = username;
		this.password = password;
		this.customer = customer;
	}

	public Account(int id, String username, String password, Customer customer) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.customer = customer;
	}
	private int id;
	private String username;
	private String password;
	private Customer customer;
	

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
