package com.login.service;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberService {

	/* 이메일 체크 */
	public int emailCk(String email) throws Exception;
	
	/* 회원가입 */
	public void membership(MemberVO member) throws Exception;
	
}
