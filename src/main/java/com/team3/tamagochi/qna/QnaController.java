package com.team3.tamagochi.qna;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team3.tamagochi.boards.util.Pager;

@Controller
@RequestMapping(value="/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value="qnaList", method= RequestMethod.GET)
	public String list(Model model, Pager pager) throws Exception {
		List<QnaDTO> list = qnaService.list(pager);
		model.addAttribute("list",list);
		model.addAttribute("pager", pager);
		System.out.println(qnaService.list(pager).size());
		
		return "qna/qnaList";
		
	}
}