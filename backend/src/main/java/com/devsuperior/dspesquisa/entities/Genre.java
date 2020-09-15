package com.devsuperior.dspesquisa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_genre")
public class Genre implements Serializable {
	/**
	 * Serial ID, necessario pelo fato da classe implementar o Serializable.
	 * Implementa essa interface para poder transformar a classe em bit, para trafego de dados (internet) 
	 */
	private static final long serialVersionUID = 1L;
	
	//--- Atributos Básicos --------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "genre")
	private List<Game> games = new ArrayList<>();
	
	//--- Construtores --------------------------
	
	public Genre() {
		super();
	}

	public Genre(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	//--- Getters & Setters ---------------------------
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Game> getGames() {
		return games;
	}

	
	//--- Hashcode & Equals -------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
