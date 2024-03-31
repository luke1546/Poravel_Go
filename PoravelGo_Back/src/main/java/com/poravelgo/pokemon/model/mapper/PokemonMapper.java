package com.poravelgo.pokemon.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.poravelgo.pokemon.model.PokemonDto;

public interface PokemonMapper {
	void insertPokemon(int no) throws SQLException;
	List<PokemonDto> selectPokemon(int no);
}
