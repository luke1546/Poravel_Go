//package com.poravelgo.member.model.service;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import com.poravelgo.member.model.Member;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Map;
//
//// SpringSecurity 를 이용한 로그인에서 사용되는
//@Data
//public class PrincipalDetails implements UserDetails, OAuth2User {
//
//	// ChatUserDTO
//	private Member member;
//
//	// 소셜 로그인 유저의 정보 확인을 위한 attributes
//	private Map<String, Object> attributes;
//
//	// 소셜 유저 타입 정보 -> 네이버, 카카오, 일반 등
//	private String provider;
//
//	// 일반 유저
//	public PrincipalDetails(Member member, String provider) {
//		this.member = member;
//		this.provider = provider;
//	}
//
//	// OAuth2User 유저 -> 소셜 로그인 유저
//	public PrincipalDetails(Member member, Map<String, Object> attributes, String provider) {
//		this.member = member;
//		this.attributes = attributes;
//		this.provider = provider;
//	}
//
//	@Override
//	public Map<String, Object> getAttributes() {
//		return null;
//	}
//
//	@Override
//	public String getName() {
//		return member.getMemberName();
//	}
//
//	// 해당 유저의 권한을 return
//	// 원래는 회원 가입 시 유저의 권한을 설정해두고 해당 유저의 권한을 return 해야하나
//	// 현재는 DB 를 사용해서 회원가입을 하는게 아니라 소셜 로그인을 하는 것! 이 목적이기 때문에
//	// 모든 유저의 권한은 "user" 로 return 한다.
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> role = new ArrayList<>();
//
//		role.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//				return "member";
//			}
//		});
//
//		return role;
//	}
//
//	@Override
//	public String getPassword() {
//		return "nopwd";
//	}
//
//	@Override
//	public String getUsername() {
//		return member.getMemberName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//}
