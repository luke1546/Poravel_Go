package com.poravelgo.board.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poravelgo.board.model.BoardDto;
import com.poravelgo.board.model.PageDto;
import com.poravelgo.board.model.service.BoardService;

@CrossOrigin("*")
@RestController
@RequestMapping("/board")
public class BoardController extends HttpServlet {
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerBoard(@RequestBody BoardDto boardDto) throws SQLException {
		boardService.registerBoard(boardDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<BoardDto>> listBoard() throws SQLException {
		return ResponseEntity.ok(boardService.searchListAll());
	}
	
	@GetMapping("/search/{subject}")
	public ResponseEntity<List<BoardDto>> searchListBySubject(@PathVariable String subject) throws SQLException {
		return ResponseEntity.ok(boardService.searchListBySubject(subject));
	}
	
	@GetMapping("/{no}")
	public ResponseEntity<BoardDto> detailBoard(@PathVariable int no) throws SQLException {
		BoardDto b = boardService.viewArticle(no);
		System.out.println(b);
		return ResponseEntity.ok(b);
	}
	
	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) throws SQLException {
		boardService.deleteArticle(no);
		return ResponseEntity.ok("OK");
	}
	// --------------------
	
	@PutMapping("/{no}")
	public ResponseEntity<String> modifyBoard(@RequestBody BoardDto boardDto,
			@PathVariable int no) throws SQLException {
		System.out.println(no);
		boardDto.setBoardNo(no);
		boardService.modifyArticle(boardDto);
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("/hit/{no}")		//조회수 증가
	public ResponseEntity<String> addHit(@PathVariable int no) throws SQLException {
		boardService.addHit(no);
		return ResponseEntity.ok("OK");
	}
	
	@PutMapping("/heart/{no}")		//좋아요 수 증가
	public ResponseEntity<String> addHeart(@PathVariable int no) throws SQLException {
		boardService.addHeart(no);
		return ResponseEntity.ok("OK");
	}
	
	@GetMapping("/list/{pageNo}")
	public ResponseEntity<Map<String, Object>> listBoard2(@PathVariable int pageNo) throws Exception {
		PageDto page = new PageDto();
		page.setPageNo(pageNo);
		return ResponseEntity.ok(boardService.listBoard2(page));
	}
}

