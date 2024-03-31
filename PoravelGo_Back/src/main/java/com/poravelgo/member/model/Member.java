package com.poravelgo.member.model;

import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "Member : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class Member {

	@ApiModelProperty(value = "아이디")
	private String userId;
	@ApiModelProperty(value = "이름")
	private String userName;
	@ApiModelProperty(value = "비밀번호")
	private String userPwd;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "생일")
	private String birthDay;
	@ApiModelProperty(value = "refreshToken")
	private String refreshToken;
	@ApiModelProperty(value = "사용자 이미지")
	private int pokemonNo;
}