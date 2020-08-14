package com.first.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.service.FirstService;
import com.first.service.FirstVO;
import com.first.service.PagingVO;

@Service("FirstService")
public class FirstServiceImpl implements FirstService{

	@Autowired
	private FirstDAO firstDAO;
	
	/* 리스트 목록 */
	@Override
	public List<FirstVO> listBrd(PagingVO paging) {
		return firstDAO.listBrd(paging);
	}

	/* 게시물 총 갯수 */
	@Override
	public int cntBrd(PagingVO paging) {
		return firstDAO.cntBrd(paging);
	}
	
	/* 게시물 입력 */
	@Override
	public int insertBrd(FirstVO vo) {
		return firstDAO.insertBrd(vo);
	}

	/* 상세보기 */
	@Override
	public FirstVO detailBrd(int bNo) {
		//조회수 증가
		firstDAO.upCnt(bNo);
		
		return firstDAO.detailBrd(bNo);
	}

	/* 글 수정 */
	@Override
	public int updateBrd(FirstVO vo) {
		return firstDAO.updateBrd(vo);
	}

	/* 글 삭제 */
	@Override
	public int deleteBrd(int bNo) {
		return firstDAO.deleteBrd(bNo);
	}

}
