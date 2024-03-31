package com.poravelgo.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.poravelgo.board.model.BoardDto;
import com.poravelgo.board.model.PageDto;
import com.poravelgo.board.model.PageResult;
import com.poravelgo.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	private BoardMapper boardMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public Map<String, Object> listBoard2(PageDto page) throws Exception {
		List<BoardDto> list = boardMapper.selectBoard(page.getBegin(), page.getListSize());
		int count = boardMapper.selectBoardCount();
		PageResult pg = new PageResult(page.getPageNo(), count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("pg", pg);
		return result;
	}
	
	@Override
	public void registerBoard(BoardDto boardDto) throws SQLException{
		boardMapper.registerBoard(boardDto);
	}

	@Override
	public List<BoardDto> searchListAll() throws SQLException{
		return boardMapper.searchListAll();
	}

	@Override
	public List<BoardDto> searchListBySubject(String subject) throws SQLException{
		return boardMapper.searchListBySubject(subject);
	}

	@Override
	public BoardDto viewArticle(int no) throws SQLException{
		boardMapper.updateHit(no);
		return boardMapper.viewArticle(no);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException{
		boardMapper.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int no) throws SQLException {
		boardMapper.deleteArticle(no);
	}

	@Override
	public void addHit(int no) throws SQLException {
		boardMapper.updateHit(no);
	}

	@Override
	public void addHeart(int no) throws SQLException {
		boardMapper.updateHeart(no);
	}
}
