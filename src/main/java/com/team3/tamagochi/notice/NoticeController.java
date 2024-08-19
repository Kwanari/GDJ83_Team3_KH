package com.team3.tamagochi.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team3.tamagochi.boards.util.Pager;
import com.team3.tamagochi.users.UsersDTO;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="noticeList", method= RequestMethod.GET)
	public String list(Model model, Pager pager) throws Exception {
		List<NoticeDTO> list = noticeService.list(pager);
		model.addAttribute("list",list);
		model.addAttribute("pager", pager);
		
		return "notice/noticeList";
		
	}
	
	@RequestMapping(value="noticeAdd", method= RequestMethod.GET)
	public String add() {
		return "notice/noticeAdd";
	}
	
	
	@RequestMapping(value="noticeAdd", method= RequestMethod.POST)
	public String add(NoticeDTO noticeDTO, HttpSession session, Model model) throws Exception{
			UsersDTO usersDTO = (UsersDTO)session.getAttribute("users_info");
			noticeDTO.setUser_id(usersDTO.getUser_id());
			int result = noticeService.add(noticeDTO, session);
			
			
		return "redirect:./noticeList";
	}
	
	@RequestMapping(value="noticeDetail", method=RequestMethod.GET)
	public String detail(NoticeDTO noticeDTO, Model model) throws Exception{
		hit(noticeDTO);
		noticeDTO = noticeService.detail(noticeDTO);
		model.addAttribute("detail",noticeDTO);
		return "notice/noticeDetail";
		
	}
	
	public void hit(NoticeDTO noticeDTO) throws Exception {
		noticeService.hit(noticeDTO);
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.GET)
	public String update(Model model, NoticeDTO noticeDTO) throws Exception {
		noticeDTO =noticeService.detail(noticeDTO);
		model.addAttribute("detail", noticeDTO);
		return "notice/noticeUpdate";
	}

	@RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	public String update(NoticeDTO noticeDTO) throws Exception {
		int num = noticeService.update(noticeDTO);
		return "redirect:./noticeDetail?notice_num=" + noticeDTO.getNotice_num();
	}
	
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(NoticeDTO noticeDTO, Model model) throws Exception {
		int num = noticeService.delete(noticeDTO);
		
		String path = "commons/message";
		if (num > 0) {
			path = "redirect:./noticeList";
		} else {
			model.addAttribute("result", "게시물을 지우지 못했습니다.");
			model.addAttribute("url", "./noticeList");
		}
		return path;
	}
	


}
