package com.devsuperior.dspesquisa.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.Repositories.GameRepository;
import com.devsuperior.dspesquisa.Repositories.RecordRepository;
import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;

/**
 * Essa clase faz a transferencia dos dados da Entidade para o DTO
 * E envia o DTO para o controlador para ser utilizado para os webservices
 * 
 */

@Service
public class RecordService {

	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional
	public RecordDTO insert(RecordInsertDTO dto) {
		Record entity = new Record();
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setMoment(Instant.now());
		
		Game game = gameRepository.getOne(dto.getGameId());
		entity.setGame(game);
		
		entity = recordRepository.save(entity);
		
		return new RecordDTO(entity);
		
	}

	@Transactional(readOnly = true)
	public Page<RecordDTO> findByMoments(Instant minDate, Instant maxDate, PageRequest pageRequest) {
		
		//O tipo Page, já é uma Strem, então não precisa converter para Stream primeiro.
		return recordRepository.findByMoments(minDate, maxDate, pageRequest).map(x -> new RecordDTO(x));
	}
	
	
}
