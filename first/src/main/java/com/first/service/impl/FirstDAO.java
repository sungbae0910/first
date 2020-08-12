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
	
	protected void printQueryId(String queryId) {
		if(log.isDebugEnabled()) {
			log.debug("\t QueryId \t" + queryId);
		}
	}
	
	/* 게시물 목록 */
	public List<FirstVO> listBrd(PagingVO paging){
		List<FirstVO> vo = sqlSession.selectList("board.listBrd", paging);
		return vo;
	}
	
	/* 게시물 갯수 */
	public int cntBrd(PagingVO paging) {
		return sqlSession.selectOne("board.cntBrd", paging);
	}

	/* 게시물 입력 */
	public int insertBrd(FirstVO vo) {
		return sqlSession.insert("board.insertBrd", vo);
	}
	
	/* 상세보기 */
	public FirstVO detailBrd(int bNo) {
		return sqlSession.selectOne("board.detailBrd", bNo);
	}
	
	/* 조회수 증가 */
	public void upCnt(int bNo){
		sqlSession.update("board.updateCnt", bNo);
	}
	
	/* 수정 */
	public int updateBrd(FirstVO vo) {
		return sqlSession.update("board.updateBrd", vo);
	}
	
	/* 삭제 */
	public int deleteBrd(int bNo){
		return sqlSession.delete("board.deleteBrd", bNo);
	}
	
}
