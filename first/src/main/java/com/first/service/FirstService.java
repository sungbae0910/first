package com.first.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

@Repository
public interface FirstService {
	
	/* 게시물 데이터 조회 */
	public List<FirstVO> listBrd(PagingVO paging) throws Exception;
	
	/* 게시물 총 갯수 */
	public int cntBrd(PagingVO paging) throws Exception;
	
	/* 게시글 입력 */
	public int insertBrd(FirstVO vo) throws Exception;
	
	/* 상세보기 조회증가 */
	public FirstVO detailBrd(int bNo, HttpServletRequest req) throws Exception;
	
	/* 수정 */
	public int updateBrd(FirstVO vo) throws Exception;
	
	/* 삭제 */
	public int deleteBrd(int bNo) throws Exception;

	/* 상세보기2 조회증가x */
	public FirstVO detailBrd2(int bNo) throws Exception;

}
