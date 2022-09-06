package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "borrowlist")
public class BorrowList {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int borrowid;
	
	private int user;
	private int book;
	private String date;
	private String action;
	
	public BorrowList() {
		
	}

	public BorrowList(int user, int book, String date, String action) {
		this.user = user;
		this.book = book;
		this.date = date;
		this.action = action;
	}

	public int getBorrowid() {
		return borrowid;
	}
	public void setBorrowid(int borrowid) {
		this.borrowid = borrowid;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
