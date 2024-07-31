package com.poravelgo.attraction.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poravelgo.attraction.model.AttractionInfoDto;
import com.poravelgo.attraction.model.service.AttractionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/attraction")
public class AttractionController {
	
	AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<AttractionInfoDto>> listAttraction(AttractionInfoDto attractionInfoDto) throws SQLException {
		return ResponseEntity.ok(attractionService.attractionList(attractionInfoDto));
	}
	
	@GetMapping("/{title}/{sidoCode}")
	public ResponseEntity<List<AttractionInfoDto>> searchByTitleAttraction(
			@PathVariable String title, @PathVariable int sidoCode) {
		return ResponseEntity.ok(attractionService.searchByTitle(title, sidoCode));
	}
	
}
