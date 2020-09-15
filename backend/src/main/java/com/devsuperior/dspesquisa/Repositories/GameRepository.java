package com.devsuperior.dspesquisa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dspesquisa.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
