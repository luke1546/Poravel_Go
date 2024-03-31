package com.poravelgo.member.model.service;

import com.poravelgo.member.model.Member;

public interface MemberService {

	Member login(Member member) throws Exception;

	Member userInfo(String userId) throws Exception;

	void saveRefreshToken(String userId, String refreshToken) throws Exception;

	Object getRefreshToken(String userId) throws Exception;

	void deleRefreshToken(String userId) throws Exception;

}
