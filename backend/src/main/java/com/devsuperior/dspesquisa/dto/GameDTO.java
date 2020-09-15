package com.devsuperior.dspesquisa.dto;

import java.io.Serializable;

import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.enums.Platform;

/**
 * 
 * @author Marcelo
 *
 *Tem a finalidade de gerar um objeto que carregue os dados da entidade [no caso Game]
 *Faz com que não haja uma troca direta entre o Controlador e a entidade.
 */

public class GameDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//--- Atributos ---------------------
	private Long id;
	private String title;
	private Platform platform;
	
	//--- Constructors ----------------------------
	public GameDTO() {
		
	}
	
	public GameDTO(Game entity){
		id = entity.getId();
		title = entity.getTitle();
		platform = entity.getPlatform();
		
	}

	//--- Getters & Setters --------------------------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	
	
	
}
