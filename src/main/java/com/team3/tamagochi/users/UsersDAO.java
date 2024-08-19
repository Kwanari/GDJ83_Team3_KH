package com.team3.tamagochi.users;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team3.tamagochi.mypet.MyPetDTO;
import com.team3.tamagochi.store.ItemDTO;
import com.team3.tamagochi.store.WeaponDTO;

@Repository
public class UsersDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.team3.tamagochi.users.UsersDAO.";
	
	
	// 회원가입 메서드
	public int registerUsers(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.insert(NAMESPACE + "registerUsers", usersDTO);		
	}
	
	// 로그인 메서드
	public UsersDTO loginUsers(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.selectOne(NAMESPACE + "loginUsers", usersDTO);
	}
	
	// 회원정보 수정 메서드
	public int updateUsers(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.update(NAMESPACE + "updateUsers", usersDTO);
	}
	
	// 회원 탈퇴 메서드(데이터는 남아있으나 resign값을 0으로 만들어 더이상 로그인 하지 못하게 만듬)
	public int deleteAccount(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.update(NAMESPACE + "deleteAccount", usersDTO);
	}
	
	// 인벤토리에서 내가 보유한 무기를 가져오는 메서드
	// 무기 데이터를 가져와야 하므로 리턴 타입은 weaponDTO가 된다
	public List<ItemDTO> getInvenList(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.selectList(NAMESPACE + "getInvenList", usersDTO);
	}
	
	// 아이템 착용 메서드
	// 1. 아이템 장착을 하기 위해선 현재 사용중인 pet_num이 필요
	// pet_num을 구하는 메서드
	public MyPetDTO getMyPetNum(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.selectOne(NAMESPACE + "getMyPetNum", usersDTO);
	}
	
	// 2. 현재 사용중인 캐릭터에 선택한 아이템 능력치만큼 업데이트 해주는 메서드
	public Integer equipItem(MyPetDTO myPetDTO) throws Exception{
		
		return sqlSession.update(NAMESPACE + "equipItem", myPetDTO);
	}
	
	// 3. 현재 사용중인 캐릭터가 장비한 아이템을 해제하는 메서드
	public Integer takeOffItem(MyPetDTO myPetDTO) throws Exception{
		
		return sqlSession.update(NAMESPACE + "takeOffItem", myPetDTO);
	}
	
	
	
	// 회원가입시 입력한 id값이 중복인지 아닌지 검사하는 JS 이벤트 메서드
	public UsersDTO checkID(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.selectOne(NAMESPACE + "checkID", usersDTO);
	}
	// 회원 정보 수정시 입력한 패스워드가 올바른 패스워드인지 검사하는 JS 이벤트 메서드
	public UsersDTO checkPW(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.selectOne(NAMESPACE + "checkPW", usersDTO);
	}
	
	// 이름, 이메일, 전화번호로 회원의 아이디를 찾는 메서드
	public List<UsersDTO> findID(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.selectList(NAMESPACE + "findID", usersDTO);
	}
	
	// 아이디, 이름, 이메일, 전화번호로 회원의 패스워드를 찾는 메서드
	public UsersDTO findPW(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.selectOne(NAMESPACE + "findPW", usersDTO);
	}
	
	// 네이버 API로 로그인시 회원 정보를 DB에 저장하는 메서드
	public int insertNaverProfile(UsersDTO usersDTO) throws Exception{
		
		return sqlSession.insert(NAMESPACE + "insertNaverProfile", usersDTO);
	}
	
	// 거래내역을 조회하는 메서드
	public List<TransactionDTO> getTradeList(TransactionDTO transactionDTO) throws Exception{
		
		return sqlSession.selectList(NAMESPACE + "getTradeList", transactionDTO);
	}
	
	// 거래내역 중 transaction type을 선택해서 조회하는 메소드
	public List<TransactionDTO> selectType(TransactionDTO transactionDTO) throws Exception{
		
		return sqlSession.selectList(NAMESPACE + "selectType", transactionDTO);
	}
	
	
	
	
	
	
	// 회원가입시 디폴트 캐릭터를 만들어주는 메서드 4개
	public int setDefaultCharacter1(UsersDTO usersDTO) throws Exception{
		return sqlSession.insert(NAMESPACE + "setDefaultCharacter1", usersDTO);
	}
	public int setDefaultCharacter2(UsersDTO usersDTO) throws Exception{
		return sqlSession.insert(NAMESPACE + "setDefaultCharacter2", usersDTO);
	}
	public int setDefaultCharacter3(UsersDTO usersDTO) throws Exception{
		return sqlSession.insert(NAMESPACE + "setDefaultCharacter3", usersDTO);
	}
	public int setDefaultCharacter4(UsersDTO usersDTO) throws Exception{
		return sqlSession.insert(NAMESPACE + "setDefaultCharacter4", usersDTO);
	}
}
