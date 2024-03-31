package com.poravelgo.pokemon.model.service;

import java.sql.SQLException;
import java.util.List;

import com.poravelgo.pokemon.model.PokemonDto;

public interface PokemonService {
	void insertPokemon(int no) throws SQLException;
	List<PokemonDto> selectPokemon(int no);
}
