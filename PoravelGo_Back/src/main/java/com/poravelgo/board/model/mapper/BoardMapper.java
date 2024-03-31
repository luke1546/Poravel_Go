package com.poravelgo.board.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.poravelgo.board.model.BoardDto;
import com.poravelgo.board.model.PageDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> selectBoard(@Param("start")int s, @Param("end") int e) throws SQLException;
	
	public int selectBoardCount() throws SQLException;
	void registerBoard(BoardDto boardDto) throws SQLException;
	List<BoardDto> searchListAll() throws SQLException;
	List<BoardDto> searchListBySubject(String subject) throws SQLException;
	BoardDto viewArticle(int no) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int no) throws SQLException;
	void updateHit(int no) throws SQLException;
	void updateHeart(int no) throws SQLException;

}
