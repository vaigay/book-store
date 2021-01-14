package model;
public class Address {
	private String country;
	private String city;
	private String detail;
	public Address(String country, String city, String detail) {
		super();
		this.country = country;
		this.city = city;
		this.detail = detail;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
