package com.devsuperior.dspesquisa.controllers;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;

@RestController
@RequestMapping(value = "/records")
public class RecordController {

	@Autowired
	private RecordService recordService;
	//--- Post new Record / Show all info about Record ---------------------------------------------
	@PostMapping
	public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO dto) {
		
		RecordDTO newDTO = recordService.insert(dto);
		
		return ResponseEntity.ok().body(newDTO);		
	}
	
	//--- Get Record ------------------------------------------------------
	@GetMapping
	public ResponseEntity<Page<RecordDTO>> findAll(
			@RequestParam(value = "min", defaultValue = "") String min, // data minima aplicação web
			@RequestParam(value = "max", defaultValue = "") String max, // data máxima aplicação web
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction
			) {
		//-- Tratativa dos Dados ---------------------------------------
		Instant minDate = ("".equals(min))? null : Instant.parse(min);
		Instant maxDate = ("".equals(max))? null : Instant.parse(max);
		if(linesPerPage == 0) linesPerPage = Integer.MAX_VALUE;
		
		//--- Objeto de Paginação --------------------------------------
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		
		Page<RecordDTO> list = recordService.findByMoments(minDate, maxDate, pageRequest);
		return ResponseEntity.ok().body(list);
	}
	
	
}
