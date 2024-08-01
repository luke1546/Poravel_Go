package com.poravelgo.attraction.model.mapper;

import java.util.List;

import com.poravelgo.attraction.model.AttractionHashDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.poravelgo.attraction.model.AttractionInfoDto;

@Mapper
public interface AttractionMapper {
	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);
	List<AttractionInfoDto> searchByTitle(@Param("title") String title);

	AttractionHashDto[] loadData();
}
