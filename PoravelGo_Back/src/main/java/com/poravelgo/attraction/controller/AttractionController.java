package com.poravelgo.attraction.controller;

import com.poravelgo.attraction.model.AttractionHashDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poravelgo.attraction.model.AttractionInfoDto;
import com.poravelgo.attraction.model.service.AttractionService;

import javax.annotation.PostConstruct;

@CrossOrigin("*")
@RestController
@RequestMapping("/attraction")
public class AttractionController {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	AttractionService attractionService;
	private HashMap<String, String> ngramHashes;
	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}

	@PostConstruct
	public void init() {
		// 데이터를 로드합니다 (예: 데이터베이스, 파일 등에서).
//		AttractionHashDto[] data = attractionService.loadData();
		ngramHashes = new HashMap<>();

		// N-gram 해시값을 생성하고 저장합니다.
//		for (String item : data) {
//			String ngramHash = generateNgramHash(item);
//			ngramHashes.put(item, ngramHash);
//		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<AttractionInfoDto>> listAttraction(AttractionInfoDto attractionInfoDto) throws SQLException {
		return ResponseEntity.ok(attractionService.attractionList(attractionInfoDto));
	}

	@GetMapping("/search")
	public ResponseEntity<List<AttractionInfoDto>> searchByTitleAttraction(
			@RequestParam(required = false) String title) {
		long startTime = System.currentTimeMillis();

		if (title == null) title = "";
		List<AttractionInfoDto> results = attractionService.searchByTitle(title);

		long endTime = System.currentTimeMillis();
		logger.info("MySQL like문 실행시간 : {} ms", (endTime - startTime));

		return ResponseEntity.ok(results);
	}

	@GetMapping("/search2")
	public ResponseEntity<List<AttractionInfoDto>> searchByTitleAttraction2(
			@RequestParam(required = false) String title) {
		long startTime = System.currentTimeMillis();

		if (title == null) title = "";
		List<AttractionInfoDto> results = attractionService.searchByTitle(title);

		long endTime = System.currentTimeMillis();
		logger.info("ngram방식 실행 시간: {} ms", (endTime - startTime));

		return ResponseEntity.ok(results);
	}

}
