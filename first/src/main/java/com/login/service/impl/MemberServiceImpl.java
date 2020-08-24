package com.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.service.MemberService;
import com.login.service.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public int emailCk(String email) throws Exception{
		return memberDAO.emailCk(email);
	}

	@Override
	public void membership(MemberVO member) throws Exception{
		memberDAO.membership(member);
	}

	
}
