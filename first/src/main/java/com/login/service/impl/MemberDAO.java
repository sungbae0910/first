package com.login.service.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.login.service.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/* 이메일 체크 */
	public int emailCk(String email) throws Exception{
		return sqlSession.selectOne("member.emailCk",email);
	}
	
	/* 회원가입 */
	public void membership(MemberVO member) throws Exception{
		sqlSession.insert("member.membership", member);
	}
}
