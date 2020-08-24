package com.login.service;

import java.sql.Date;

public class MemberVO {

	/* 아이디 */
	private String id;
	/* 비밀번호  */
	private String password;
	/* 이름 */
	private String userName;
	/* 주소 */
	private String addr;
	/* 우편번호 */
	private int zipCode;
	/* 가입시기 */
	private Date create_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
