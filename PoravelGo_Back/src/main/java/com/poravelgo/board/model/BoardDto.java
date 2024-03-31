package com.poravelgo.board.model;

import lombok.Data;

@Data
public class BoardDto {
	private int boardNo;
	private String subject;
	private String content;
	private String userId;
	private int hit;
	private int heart;
	private String registerTime;
	private int memberNo;
}
