package com.poravelgo.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.poravelgo.board.model.BoardDto;
import com.poravelgo.board.model.PageDto;

public interface BoardService {

	Map<String, Object> listBoard2(PageDto page) throws Exception;
	
	void registerBoard(BoardDto boardDto) throws SQLException;					//

	List<BoardDto> searchListAll() throws SQLException;							//

	List<BoardDto> searchListBySubject(String subject) throws SQLException;

	BoardDto viewArticle(int no) throws SQLException;							//

	void modifyArticle(BoardDto boardDto) throws SQLException;

	void deleteArticle(int no) throws SQLException;								//
	
	void addHit(int no) throws SQLException;									//

	void addHeart(int no) throws SQLException;									//
}
