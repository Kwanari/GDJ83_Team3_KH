package com.team3.tamagochi.qna;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team3.tamagochi.boards.util.Pager;


@Repository
public class QnaDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE ="com.team3.tamagochi.qna.QnaDAO.";
	
	public List<QnaDTO> list(Pager pager) throws Exception{
		return sqlSession.selectList(NAMESPACE + "list",pager);
	}
	
	public Long getTotalCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE + "getTotalCount",pager);
		
	}

}