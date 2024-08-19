package com.team3.tamagochi.users;

import java.sql.Date;

import com.team3.tamagochi.store.ItemDTO;

public class TransactionDTO {
	
	private Long transaction_num;
	private String user_id;
	private Long item_num;
	private Date transaction_date;
	private Long transaction_amount;
	private String transaction_type;
	// ITEM 테이블을 조인시 필요한 dto
	private ItemDTO itemDTO;
	
	
	public ItemDTO getItemDTO() {
		return itemDTO;
	}
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}
	public Long getTransaction_num() {
		return transaction_num;
	}
	public void setTransaction_num(Long transaction_num) {
		this.transaction_num = transaction_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Long getItem_num() {
		return item_num;
	}
	public void setItem_num(Long item_num) {
		this.item_num = item_num;
	}
	public Date getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	public Long getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(Long transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	
	

}
