package com.example.demo.bean;

public class User_Register {
	private int Lid;
	private String FirstName;
	private String LastName;
	private String Email;
	private int PhoneNumber;
	private int Term;
	
	
	public User_Register() {
		this.Term = 0;
	}
	
	public User_Register(String firstName, String lastName, String email, int phoneNumber, int term) {
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		PhoneNumber = phoneNumber;
		Term = term;
	}

	public int getLid() {
		return Lid;
	}

	public void setLid(int lid) {
		Lid = lid;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public int getTerm() {
		return Term;
	}

	public void setTerm(int term) {
		Term = term;
	}
}
