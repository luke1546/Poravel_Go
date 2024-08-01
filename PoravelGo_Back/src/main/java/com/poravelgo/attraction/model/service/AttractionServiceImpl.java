package com.poravelgo.attraction.model.service;

import java.util.List;

import com.poravelgo.attraction.model.AttractionHashDto;
import org.springframework.stereotype.Service;

import com.poravelgo.attraction.model.AttractionInfoDto;
import com.poravelgo.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {
	private AttractionMapper attractionMapper;
	
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}

	@Override
	public List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto) {
		return attractionMapper.attractionList(attractionInfoDto);
	}

	@Override
	public List<AttractionInfoDto> searchByTitle(String title) {
		return attractionMapper.searchByTitle(title);
	}

	@Override
	public AttractionHashDto[] loadData() {
		return attractionMapper.loadData();
	}

	@Override
	public List<AttractionInfoDto> searchByTitle2(String title) {
		return attractionMapper.searchByTitle(title);
	}
}
