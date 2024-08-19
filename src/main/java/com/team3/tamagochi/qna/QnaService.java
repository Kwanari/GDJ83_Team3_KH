package com.team3.tamagochi.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team3.tamagochi.boards.util.Pager;

@Service
public class QnaService {
	@Autowired
	private QnaDAO qnaDAO;
	
	public List<QnaDTO> list(Pager pager) throws Exception{
		pager.makerow();
		pager.makeNum(qnaDAO.getTotalCount(pager));
		return qnaDAO.list(pager);
		
		
	}
	

}
