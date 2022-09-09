package com.example.demo.bean;

public class BookandType{
	private Book book;
	private String type;
	private String author;
	
	public BookandType() {
	}

	public BookandType(Book book, String type) {
		this.book = book;
		this.type = type;
	}
	
	public BookandType(Book book) {
		this.book = book;
		String type = "";
		
		if(book.getScifi() == 1) {
			type += "Sci-Fi, ";
		}
		if(book.getDetective() == 1) {
			type += "Detective, ";
		}
		if(book.getComic() == 1) {
			type += "Comic, ";
		}
		if(book.getCook() == 1) {
			type += "Cookbook, ";
		}
		if(book.getEducation() == 1) {
			type += "Education, ";
		}
		if(book.getHistory() == 1) {
			type += "History, ";
		}
		if(book.getLiterature() == 1) {
			type += "Literature, ";
		}
		if(book.getPhilosophy() == 1) {
			type += "Philosophy, ";
		}
		if(!type.equals("")) {
    		type = type.substring(0, type.length()-2);
		}
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
