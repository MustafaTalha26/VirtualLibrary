package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "termination")
public class Terms {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;
	
	private int admin;
	private int user;
	private String date;
	private String cause;

	public Terms() {
		
	}
	public Terms(int admin, int user, String date, String cause) {
		this.admin = admin;
		this.user = user;
		this.date = date;
		this.cause = cause;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
}
