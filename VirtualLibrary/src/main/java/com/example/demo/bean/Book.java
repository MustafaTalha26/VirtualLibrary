package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bid;
	
	private String name;
	private String image;
	private int aid;
	private int page;
	
	private int scifi;
	private int comic;
	private int literature;
	private int philosophy;
	private int education;
	private int detective;
	private int history;
	private int cook;
	
	public Book() {
		
	}

	public Book(String name, String image, int aid, int page) {
		this.name = name;
		this.image = image;
		this.aid = aid;
		this.page = page;
	}
	
	public Book(int bid, String name, String image, int aid, int page) {
		this.bid = bid;
		this.name = name;
		this.image = image;
		this.aid = aid;
		this.page = page;
	}
	
	public Book(int bid, String name, String image, int aid, int page, int scifi, int comic, int literature,
			int philosophy, int education, int detective, int history, int cook) {
		this.bid = bid;
		this.name = name;
		this.image = image;
		this.aid = aid;
		this.page = page;
		this.scifi = scifi;
		this.comic = comic;
		this.literature = literature;
		this.philosophy = philosophy;
		this.education = education;
		this.detective = detective;
		this.history = history;
		this.cook = cook;
	}

	public Book(String name, String image, int aid, int page, int scifi, int comic, int literature, int philosophy,
			int education, int detective, int history, int cook) {
		this.name = name;
		this.image = image;
		this.aid = aid;
		this.page = page;
		this.scifi = scifi;
		this.comic = comic;
		this.literature = literature;
		this.philosophy = philosophy;
		this.education = education;
		this.detective = detective;
		this.history = history;
		this.cook = cook;
	}

	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getScifi() {
		return scifi;
	}
	public void setScifi(int scifi) {
		this.scifi = scifi;
	}
	public int getComic() {
		return comic;
	}
	public void setComic(int comic) {
		this.comic = comic;
	}
	public int getLiterature() {
		return literature;
	}
	public void setLiterature(int literature) {
		this.literature = literature;
	}
	public int getPhilosophy() {
		return philosophy;
	}
	public void setPhilosophy(int philosophy) {
		this.philosophy = philosophy;
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	public int getDetective() {
		return detective;
	}
	public void setDetective(int detective) {
		this.detective = detective;
	}
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	public int getCook() {
		return cook;
	}
	public void setCook(int cook) {
		this.cook = cook;
	}
}
