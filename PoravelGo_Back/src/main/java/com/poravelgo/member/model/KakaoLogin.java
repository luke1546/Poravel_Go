package com.poravelgo.member.model;

import java.util.HashMap;
import java.util.Map;

public class KakaoLogin implements SocialLogin{

    private Map<String, Object> kakaoAttributes;

    // 넘어오는 정보 를 kakaoAttributes 변수에 저장
    public KakaoLogin(Map<String, Object> kakaoAttributes) {
        this.kakaoAttributes = kakaoAttributes;
    }

    // 제공자 가져오기
    @Override
    public String getProvider() {
        // TODO Auto-generated method stub

        return "kakao";
    }
    
    // 이메일 파싱해서 가져오기
    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        HashMap<String, Object> account = (HashMap<String, Object>) kakaoAttributes.get("kakao_account");

        String email = (String) account.get("email");
//    System.out.println("memail : "+memail);

        return email;
    }

    // 닉네임 파싱해서 가져오기
    @Override
    public String getNickName() {
        // TODO Auto-generated method stub
        HashMap<String, Object> properties = (HashMap<String, Object>) kakaoAttributes.get("properties");
        String nickName = (String) properties.get("nickname");

        return nickName;
    }
}
