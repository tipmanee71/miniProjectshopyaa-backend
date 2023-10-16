package com.example.demo.Model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer usersId;
	private String usersName;
	private String usersPass;
	private String usersFirst;
	private String usersLast;
	private String usersEmail;
	private String usersAddress;
	private String usersPhone;
	

	
	 public User() {
		super();
	}

	public User(String usersName, String usersPass, String usersFirst, String usersLast, String usersEmail, String usersAddress, String usersPhone) {
	        this.usersName = usersName;
	        this.usersPass = usersPass;
	        this.usersFirst = usersFirst;
	        this.usersLast = usersLast;
	        this.usersEmail = usersEmail;
	        this.usersAddress = usersAddress;
	        this.usersPhone = usersPhone;
	    }
	
	@OneToMany
	@JoinColumn(name =" orderId")
	
	@OneToOne
	@JoinColumn(name ="cartsId")
	
	
	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

	public String getUsersPass() {
		return usersPass;
	}

	public void setUsersPass(String usersPass) {
		this.usersPass = usersPass;
	}

	public String getUsersFirst() {
		return usersFirst;
	}

	public void setUsersFirst(String usersFirst) {
		this.usersFirst = usersFirst;
	}

	public String getUsersLast() {
		return usersLast;
	}

	public void setUsersLast(String usersLast) {
		this.usersLast = usersLast;
	}

	public String getUsersEmail() {
		return usersEmail;
	}

	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}

	public String getUsersAddress() {
		return usersAddress;
	}

	public void setUsersAddress(String usersAddress) {
		this.usersAddress = usersAddress;
	}

	public String getUsersPhone() {
		return usersPhone;
	}

	public void setUsersPhone(String usersPhone) {
		this.usersPhone = usersPhone;
	}
	 
	
}
