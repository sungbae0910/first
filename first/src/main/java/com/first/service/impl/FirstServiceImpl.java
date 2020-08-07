package com.first.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.service.FirstService;
import com.first.service.FirstVO;

@Service("FirstService")
public class FirstServiceImpl implements FirstService{

	@Autowired
	private FirstDAO firstDAO;
	
	/* 리스트 목록 */
	@Override
	public List<FirstVO> listBrd() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 게시물 입력 */
	@Override
	public int insertBrd(FirstVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* 상세보기 */
	@Override
	public FirstVO detailBrd(int bNo) {
		// TODO Auto-generated method stub
		return null;
	}

	/* 글 수정 */
	@Override
	public int updateBrd(FirstVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* 글 삭제 */
	@Override
	public int deleteBrd(int bNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
