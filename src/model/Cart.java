package model;

import java.util.*;

public class Cart {

	

	public Cart(int id, List<Dictionary<Book, Integer>> listBook, int idAccount, double money) {
		super();
		this.id = id;
		this.listBook = listBook;
		this.idAccount = idAccount;
		this.money = money;
	}

	public Cart() {
		super();
		this.listBook = new ArrayList<Dictionary<Book, Integer>>();
		// TODO Auto-generated constructor stub
	}

	private int id;
	private List<Dictionary<Book, Integer>> listBook;
	private int idAccount;
	private double money;

	

	public List<Dictionary<Book, Integer>> getListBook() {
		return listBook;
	}

	public void setListBook(List<Dictionary<Book, Integer>> listBook) {
		this.listBook = listBook;
		this.money = 0;
		for(Dictionary<Book, Integer> tmp : this.listBook) {
			this.money += tmp.getKey().getPrice() * tmp.getValue();
		}
	}

	public void addBook(Book e) {
		if (this.listBook.size() > 0) {
			for (Dictionary<Book, Integer> tmp : this.listBook) {
				if (tmp.getKey().getId() == e.getId()) {
					tmp.setValue(tmp.getValue() + 1);
					money += e.getPrice();
					return;
				}
			}
		}
		this.listBook.add(new Dictionary<Book, Integer>(e, 1));
		money += e.getPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
