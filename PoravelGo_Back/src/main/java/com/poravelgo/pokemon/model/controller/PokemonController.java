package com.poravelgo.pokemon.model.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poravelgo.pokemon.model.PokemonDto;
import com.poravelgo.pokemon.model.service.PokemonService;

@CrossOrigin("*")
@RestController
@RequestMapping("/pokemon")
public class PokemonController extends HttpServlet{
	private PokemonService pokemonService;
	
	public PokemonController(PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}

//	포켓몬 뽑기
	@PostMapping("/{no}")
	public ResponseEntity<String> insertPokemon(@PathVariable int no) throws SQLException {
		pokemonService.insertPokemon(no);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
//	유저가 가진 포켓몬 리스트 가져오기
	@GetMapping("/{no}")
	public ResponseEntity<List<PokemonDto>> selectPokemon(@PathVariable int no) throws SQLException {
		System.out.println(no);
		return ResponseEntity.ok(pokemonService.selectPokemon(no));
	}
	
}

