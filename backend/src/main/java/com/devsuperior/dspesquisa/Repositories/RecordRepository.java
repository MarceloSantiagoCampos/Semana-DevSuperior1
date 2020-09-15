package com.devsuperior.dspesquisa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dspesquisa.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Long>{

}
