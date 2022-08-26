package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authors")

public class Author{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	
	private String photourl;
	private String name;
	private String born;
	private String died;
	
	public Author(String photourl, String name, String born, String died) {
		super();
		this.photourl = photourl;
		this.name = name;
		this.born = born;
		this.died = died;
	}
	
	public Author(int aid, String photourl, String name, String born, String died) {
		super();
		this.aid = aid;
		this.photourl = photourl;
		this.name = name;
		this.born = born;
		this.died = died;
	}
	
	public int getAid() {
		return aid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public String getPhotourl() {
		return photourl;
	}
	
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBorn() {
		return born;
	}
	
	public void setBorn(String born) {
		this.born = born;
	}
	
	public String getDied() {
		return died;
	}
	
	public void setDied(String died) {
		this.died = died;
	}
	
}