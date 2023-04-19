/**
 * 
 */
package com.example.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author MaryL
 *
 */
@Entity
@Table(name="form")
public class Form {

	//declaracion de variables

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idForm;
	@OneToOne
	@JoinColumn(name="document_number")
	private User user;
	private String email;
	private String comments;
	@OneToOne
	@JoinColumn(name="id_mark")
	private FavoritePcMark favoritePcMark;
	private Timestamp responseDate;

	//Métodos constructores

	public Form() {

	}

	public Form(int idForm, User user, String email, String comments, FavoritePcMark favoritePcMark,
			Timestamp responseDate) {
		this.idForm = idForm;
		this.user = user;
		this.email = email;
		this.comments = comments;
		this.favoritePcMark = favoritePcMark;
		this.responseDate = responseDate;
	}

	//Métodos get y set
	public int getIdForm() {
		return idForm;
	}

	public void setIdForm(int idForm) {
		this.idForm = idForm;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public FavoritePcMark getFavoritePcMark() {
		return favoritePcMark;
	}

	public void setFavoritePcMark(FavoritePcMark favoritePcMark) {
		this.favoritePcMark = favoritePcMark;
	}

	public Timestamp getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Timestamp responseDate) {
		this.responseDate = responseDate;
	}




}
