package model;

import java.sql.Timestamp;
import java.sql.Date;

public class Bill {
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int id;
	private int id_cart;
	private String shipper;
	private double shipping;
	private String detail;
	private Timestamp day;
	
	public Bill(int id, int id_cart, String shipper, double shipping, String detail, Timestamp day) {
		super();
		this.id = id;
		this.id_cart = id_cart;
		this.shipper = shipper;
		this.shipping = shipping;
		this.detail = detail;
		this.day = day;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_cart() {
		return id_cart;
	}

	public void setId_cart(int id_cart) {
		this.id_cart = id_cart;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public double getShipping() {
		return shipping;
	}

	public void setShipping(double shipping) {
		this.shipping = shipping;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Timestamp getDay() {
		return day;
	}

	public void setDay(Timestamp day) {
		this.day = day;
	}
	
}
