package com.team3.tamagochi.notice;

import java.sql.Date;

import com.team3.tamagochi.files.FileDTO;

public class NoticeDTO extends FileDTO{
	
	private Long notice_num;
	private String user_id;
	private String notice_title;
	private Date notice_create_date;
	private Long notice_hit;
	private String notice_contents;
	
	
	public Long getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(Long notice_num) {
		this.notice_num = notice_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public Date getNotice_create_date() {
		return notice_create_date;
	}
	public void setNotice_create_date(Date notice_create_date) {
		this.notice_create_date = notice_create_date;
	}
	public Long getNotice_hit() {
		return notice_hit;
	}
	public void setNotice_hit(Long notice_hit) {
		this.notice_hit = notice_hit;
	}
	public String getNotice_contents() {
		return notice_contents;
	}
	public void setNotice_contents(String notice_contents) {
		this.notice_contents = notice_contents;
	}
	
	

}
