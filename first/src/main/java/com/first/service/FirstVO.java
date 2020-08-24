package com.first.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class FirstVO extends PagingVO{

	/* 시퀀스번호  */
	private int bNo = 0;
	/* 글 제목 */
	private String bSubject = "";
	/* 글 내용 */
	private String bContent = "";
	/* 작성자 */
	private String bWriter = "";
	/* 작성일자 */
	private String bDate = "";
	/* 조회수 */
	private int bCnt = 0;
	
	
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getbSubject() {
		return bSubject;
	}
	public void setbSubject(String bSubject) {
		this.bSubject = bSubject;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.bDate = sdf.format(bDate);
	}
	public int getbCnt() {
		return bCnt;
	}
	public void setbCnt(int bCnt) {
		this.bCnt = bCnt;
	}
	
	@Override
	public String toString() {
		return "FirstVO [bNo=" + bNo + ", bSubject=" + bSubject + ", bContent=" + bContent
				+ ", bWriter=" + bWriter + ", bDate=" + bDate + ", bCnt=" + bCnt + "]";
	}

	

	
	
	
	
}
