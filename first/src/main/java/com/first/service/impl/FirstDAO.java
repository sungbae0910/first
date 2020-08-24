package com.first.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.service.FirstVO;
import com.first.service.PagingVO;

@Repository
public class FirstDAO {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	/* 게시물 목록 */
	public List<FirstVO> listBrd(PagingVO paging) throws Exception{
		return sqlSession.selectList("board.listBrd", paging);
	}
	
	/* 게시물 갯수 */
	public int cntBrd(PagingVO paging) throws Exception{
		return sqlSession.selectOne("board.cntBrd", paging);
	}

	/* 게시물 입력 */
	public int insertBrd(FirstVO vo) throws Exception{
		return sqlSession.insert("board.insertBrd", vo);
	}
	
	/* 상세보기 */
	public FirstVO detailBrd(int bNo) throws Exception{
		return sqlSession.selectOne("board.detailBrd", bNo);
	}
	
	/* 상세보기 조회수x */
	public FirstVO detailBrd2(int bNo) throws Exception{
		return sqlSession.selectOne("board.detailBrd", bNo);
	}
	
	/* 조회수 증가 */
	public void upCnt(int bNo) throws Exception{
		sqlSession.update("board.updateCnt", bNo);
	}
	
	/* 수정 */
	public int updateBrd(FirstVO vo) throws Exception{
		return sqlSession.update("board.updateBrd", vo);
	}
	
	/* 삭제 */
	public int deleteBrd(int bNo) throws Exception{
		return sqlSession.delete("board.deleteBrd", bNo);
	}
	
}
