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
	
	private String photo;
	private String name;
	private String birth;
	private String death;

	
	public Author() {
		
	}

	public Author(String photo, String name, String birth, String death) {
		super();
		this.photo = photo;
		this.name = name;
		this.birth = birth;
		this.death = death;
	}

	public Author(int aid, String photo, String name, String birth, String death) {
		super();
		this.aid = aid;
		this.photo = photo;
		this.name = name;
		this.birth = birth;
		this.death = death;
	}

	public int getAid() {
		return aid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getDeath() {
		return death;
	}

	public void setDeath(String death) {
		this.death = death;
	}
	
}