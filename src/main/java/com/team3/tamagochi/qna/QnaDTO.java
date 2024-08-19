package com.team3.tamagochi.qna;

import java.sql.Date;

public class QnaDTO {
	
	private Long question_num;
	private String user_id;
	private String question_title;
	private Date question_create_date;
	private Long question_hit;
	private String question_contents;
	
	private Long question_ref;
	private Long question_step;
	private Long question_depth;
	private Integer question_del;
	
	public Long getQuestion_num() {
		return question_num;
	}
	public void setQuestion_num(Long question_num) {
		this.question_num = question_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public Date getQuestion_create_date() {
		return question_create_date;
	}
	public void setQuestion_create_date(Date question_create_date) {
		this.question_create_date = question_create_date;
	}
	public Long getQuestion_hit() {
		return question_hit;
	}
	public void setQuestion_hit(Long question_hit) {
		this.question_hit = question_hit;
	}
	public String getQuestion_contents() {
		return question_contents;
	}
	public void setQuestion_contents(String question_contents) {
		this.question_contents = question_contents;
	}
	public Long getQuestion_ref() {
		return question_ref;
	}
	public void setQuestion_ref(Long question_ref) {
		this.question_ref = question_ref;
	}
	public Long getQuestion_step() {
		return question_step;
	}
	public void setQuestion_step(Long question_step) {
		this.question_step = question_step;
	}
	public Long getQuestion_depth() {
		return question_depth;
	}
	public void setQuestion_depth(Long question_depth) {
		this.question_depth = question_depth;
	}
	public Integer getQuestion_del() {
		if (this. question_del == null) {
			 question_del = 0;
		}
		return  question_del;
	}
	public void setQuestion_del(Integer question_del) {
		this.question_del = question_del;
	}

	
}
