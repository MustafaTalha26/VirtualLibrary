package com.example.demo.bean;

public class BookandType {
	private Book book;
	private String type;
	
	
	public BookandType() {
	}

	public BookandType(Book book, String type) {
		this.book = book;
		this.type = type;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	//When I find a better usage I am going to change it.
	public String createType() {
		String type = "";
		if(book.getScifi() == 1) {
			type += "Sci-Fi, ";
		}
		if(book.getDetective() == 1) {
			type += "Detective, ";
		}
		if(!type.equals("")) {
    		type = type.substring(0, type.length()-2);
		}
		this.type = type;
		return type;
	}
}
