package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author MaryL
 *
 */
@Entity
@Table(name="favorite_pc_mark")
public class FavoritePcMark {

	//Declaración de variables

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMark;
	private String markName;

	//Métodos constructores
	public FavoritePcMark() {

	}

	public FavoritePcMark(int idMark, String markName) {
		this.idMark = idMark;
		this.markName = markName;
	}

	public int getIdMark() {
		return idMark;
	}

	public void setIdMark(int idMark) {
		this.idMark = idMark;
	}

	public String getMarkName() {
		return markName;
	}

	public void setMarkName(String markName) {
		this.markName = markName;
	}

}
