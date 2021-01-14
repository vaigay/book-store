package model;
public class Customer {
	public Customer(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Customer(int id, String name, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public Customer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int id;
	private String name;
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
