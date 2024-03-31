package com.poravelgo.pokemon.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.poravelgo.pokemon.model.PokemonDto;
import com.poravelgo.pokemon.model.mapper.PokemonMapper;

@Service
public class PokemonServiceImpl implements PokemonService {
	private PokemonMapper pokemonMapper;

	public PokemonServiceImpl(PokemonMapper pokemonMapper) {
		super();
		this.pokemonMapper = pokemonMapper;
	}

	@Override
	public void insertPokemon(int no) throws SQLException {
		System.out.println(no);
		pokemonMapper.insertPokemon(no);
	}

	@Override
	public List<PokemonDto> selectPokemon(int no) {
		return pokemonMapper.selectPokemon(no);
	}
}
