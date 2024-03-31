package com.poravelgo.member.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.poravelgo.member.model.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member) throws SQLException;

	Member userInfo(String userId) throws SQLException;

	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String userId) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;

}
