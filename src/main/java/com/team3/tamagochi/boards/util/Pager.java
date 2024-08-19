package com.team3.tamagochi.boards.util;

import org.springframework.stereotype.Component;

@Component
public class Pager {
	// 컬럼종류(작성자,내용)
	private String kind;
	// 검색어 (사용자가 입력한 검색어)
	private String search;
	// 페이지 번호 (현재 있는 페이지 번호)
	private Long page;

	// 시작행(한 페이지에 보여줄 게시글의 수, 그중 시작행 번호)
	private Long startRow;
	// 종료행(한 페이지에 보여줄 게시글의 수, 그중 종료행 번호)
	private Long lastRow;

	// 페이징 나눌 때 시작 번호
	private Long startNum;
	// 페이징 나눌 때 종료 번호
	private Long lastNum;
	// 페이징 나누고 나서 이전 페이징으로 넘어가는 것
	private boolean pre;
	// 페이징 나누고 나서 다음 페이지로 넘어가는 것
	private boolean next;
	// 나눌 페이저의 갯수
	private long perPage = 10L;
	//정렬
	private Integer order;
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSearch() {
		if (this.search == null) {
			this.search = "";
		}
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Long getPage() {
		if (this.page == null || this.page < 1L) {
			this.page = 1L;
		}
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getStartRow() {
		
		if (this.startRow == null || this.startRow < 1L) {
			this.page = 1L;
		}
		return startRow;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public Long getLastRow() {
		return lastRow;
	}

	public void setLastRow(Long lastRow) {
		this.lastRow = lastRow;
	}

	public Long getStartNum() {
		return startNum;
	}

	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}

	public Long getLastNum() {
		return lastNum;
	}

	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}

	public boolean isPre() {
		return pre;
	}

	public void setPre(boolean pre) {
		this.pre = pre;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public long getPerPage() {
		return perPage;
	}

	public void setPerPage(long perPage) {
		this.perPage = perPage;
	}
	
	public Pager(){
		this.setPage(1L);
	}

	// rownum 계산
	public void makerow() throws Exception {
		this.startRow = (this.getPage() - 1) * perPage + 1;
		this.lastRow = this.getPage() * perPage;
	}

	// 페이징 처리하는 메서드
	public void makeNum(long totalCount) throws Exception {
		// 1. 총 갯수를 이용해서 총 페이지 수 구하기
		long totalPage = totalCount / perPage;
		if (totalCount % perPage != 0) {
			totalPage++;
		}

		long perBlock = 5L; // 한 페이지에 보여질 페이지 번호의 개수
		long totalBlock = 0;

		totalBlock = totalPage / perBlock;

		if (totalPage % perBlock != 0) {
			totalBlock++;
		}

		long curBlock = 0;
		curBlock = page / perBlock;
		

		if (page % perBlock != 0) {
			curBlock++;
		}
		// 현재 블럭번호로 시작번호, 끝번호 구하기
		this.startNum = (curBlock - 1) * perBlock + 1;
		this.lastNum = curBlock * perBlock;
		
	

		// 이전 다음
		this.pre = true; //true면 이전 블럭이 존재, false면 이전 블럭이 없음
		this.next = true;
		if (curBlock == 1) {
			pre = false; //페이지 1번의 앞이 있을 수 없으니까
		}
		if (curBlock == totalBlock) {
			next = false; //다음이 없으면 마지막페이지일테니끼
			lastNum = totalPage;
		}

	}

}
