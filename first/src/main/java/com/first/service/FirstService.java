package com.first.service;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FirstService {
	
	/* 게시물 데이터 조회 */
	public List<FirstVO> listBrd(PagingVO paging);
	
	/* 게시물 총 갯수 */
	public int cntBrd(PagingVO paging);
	
	/* 게시글 입력 */
	public int insertBrd(FirstVO vo);
	
	/* 상세보기 */
	public FirstVO detailBrd(int bNo);
	
	/* 수정 */
	public int updateBrd(FirstVO vo);
	
	/* 삭제 */
	public int deleteBrd(int bNo);

	

}
