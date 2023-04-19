package com.example.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author MaryL
 *
 */
@Entity
@Table(name="user")
public class User {

	//declaracion de variables

	@Id
	private Long documentNumber;
	private String user;
	private String password;

	//Métodos constructores
	public User() {

	}

	public User(Long documentNumber, String user, String password) {
		this.documentNumber = documentNumber;
		this.user = user;
		this.password = password;
	}

	//Métodos get y set
	
	public Long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
