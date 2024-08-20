package com.team3.tamagochi.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team3.tamagochi.boards.util.Pager;
import com.team3.tamagochi.users.TransactionDTO;
import com.team3.tamagochi.users.UsersDTO;

@Repository
public class StoreDAO {

	@Autowired
	SqlSession sqlSession;

	private final String NAMESPACE = "com.team3.tamagochi.store.StoreDAO.";
	
	public int addBag (Map<String, Object> map) {
		return sqlSession.insert(NAMESPACE+"addBag", map);
	}
	
	public int addTransaction (TransactionDTO transactionDTO) {
		return sqlSession.insert(NAMESPACE+"addTransaction", transactionDTO);
	}
	
	public int addfile(ItemFileDTO itemFileDTO) {
		return sqlSession.insert(NAMESPACE+"addfile", itemFileDTO);
	}
	
	public Long getNum () {
		return sqlSession.selectOne(NAMESPACE+"getNum");
	}
	
	public int deleteWishList (WishListDTO wishListDTO) {
		return sqlSession.delete(NAMESPACE+"deleteWishList", wishListDTO);
	}
	
	public ItemFileDTO filedetail(ItemDTO itemDTO){
		
		return sqlSession.selectOne(NAMESPACE+"filedetail", itemDTO);
	}
	
	public List<WishListDTO> getWishList(UsersDTO usersDTO) {
		return sqlSession.selectList(NAMESPACE+"getWishList", usersDTO);
	}

	public List<ItemDTO> getItemList(Map<String, Object> map) {
	
		return sqlSession.selectList(NAMESPACE + "getItemList", map);
	}

	public Integer getTotalCount(Map<String, Object> map) {
		
		
		return sqlSession.selectOne(NAMESPACE + "getTotalCount", map);
	}

	public ItemDTO getItemDetail(ItemDTO itemDTO) {
		return sqlSession.selectOne(NAMESPACE + "getItemDetail", itemDTO);
	}

	public int addItem(ItemDTO itemDTO) {
		return sqlSession.insert(NAMESPACE + "addItem", itemDTO);
	}

	public int updateItem(ItemDTO itemDTO) {
		return sqlSession.update(NAMESPACE + "updateItem", itemDTO);
	}

	public int deleteItem(ItemDTO itemDTO) {
		return sqlSession.update(NAMESPACE + "deleteItem", itemDTO);
	}
	
	public int addWishList(WishListDTO wishListDTO) {
		return sqlSession.insert(NAMESPACE+"addWishList", wishListDTO);
	}

}
