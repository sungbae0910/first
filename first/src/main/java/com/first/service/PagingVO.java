package com.first.service;

public class PagingVO {

	/* 현재페이지 */
	private int nowPage = 1;
	/* 시작페이지 */
	private int startPage = 0;
	/* 끝페이지 */
	private int endPage = 0;
	/* 총 게시글 갯수 */
	private int total = 0;
	/* 페이지당 글 갯수 */
	private int cntPerPage = 5;
	/* 마지막페이지 */
	private int lastPage = 0;
	/* 쿼리에 쓰일 스타트넘버 */
	private int start = 0; 
	/* 쿼리에 쓰일 라스트넘버 */
	private int end = 0;
	/* 보여줄 칸수 */
	private int cntPage = 5;
	/* 검색방식 */
	private String searchType = "";
	/* 키워드 */
	private String keyword = "";
	
	public PagingVO() {}
	public PagingVO(int total, int nowPage, int cntPerPage, String keyword, String searchType) {
		this.total = total;
		this.nowPage = nowPage;
		this.cntPerPage = cntPerPage;
		this.keyword = keyword;
		this.searchType = searchType;
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());
		
	}
	
	//제일 마지막 페이지 
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage((int) Math.ceil((double)total/(double)cntPerPage));
		setTotal(total);
	}
	
	// 현재 보이는 시작과 끝 계산
	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage))*cntPage);
		if(getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		
		setStartPage(getEndPage() - cntPage+1);
		if(getStartPage() < 1) {
			setStartPage(1);
		}
	}
	
	// 쿼리에 쓰일 시작값, 끝값 계산
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		setStart(getEnd()-cntPerPage+1);
	}
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCntPerPage() {
		return cntPerPage;
	}
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "PagingVO [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", total=" + total
				+ ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end
				+ ", cntPage=" + cntPage + ", searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
	
}
