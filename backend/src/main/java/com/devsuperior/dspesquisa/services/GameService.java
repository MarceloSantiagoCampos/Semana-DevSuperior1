package com.devsuperior.dspesquisa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.Repositories.GameRepository;
import com.devsuperior.dspesquisa.dto.GameDTO;
import com.devsuperior.dspesquisa.entities.Game;

/**
 * Essa clase faz a transferencia dos dados da Entidade para o DTO
 * E envia o DTO para o controlador para ser utilizado para os webservices
 * 
 */

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	
	/* Anotação do Spring framework, não é do Javax.persistence [olhar imports]
	 * Utilizado para garantir que a parte de banco de dados vai ser feita e finalizada
	 * no service
	 * (readOnly = true) = nivel de bloqueio baixo pois é uma operação somente de leitura.
	*/
	@Transactional(readOnly = true)
	public List<GameDTO> findAll(){
		List<Game> list = gameRepository.findAll();
		
		return list.stream().map(x -> new GameDTO(x)).collect(Collectors.toList());
		
		/*
		 *list.stream() = transforma uma lista em uma Stream, que aceita funções mais complexas (tipo lambda)
		 *
		 *map(x -> new GameDTO(x)) = Mapeia a stream, transformando os objetos x [entidades] em objetos DTO [Data Transfer Object]
		 *
		 * collect(Collectors.toList()) = Transforma a Stream de volta em uma Lista.
		 * 
		 */
		
	}
	
	
}
