package com.poravelgo.attraction.model.service;

import java.util.List;

import com.poravelgo.attraction.model.AttractionInfoDto;

public interface AttractionService {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
}
