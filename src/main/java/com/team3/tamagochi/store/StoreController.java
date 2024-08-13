package com.team3.tamagochi.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.team3.tamagochi.boards.util.Pager;
import com.team3.tamagochi.users.UsersDTO;

@RequestMapping("/store/*")
@Controller
public class StoreController {

	@Autowired
	StoreService storeService;
	
	
	//==============================위시리스트 관련 메소드=============================
	//==============================위시리스트 관련 메소드=============================
	//==============================위시리스트 관련 메소드=============================
	
	@GetMapping("deleteWishList")
	public String deleteWishList (WishListDTO wishListDTO, HttpSession session, Model model) {
		
		UsersDTO usersDTO = (UsersDTO) session.getAttribute("users_info");
		wishListDTO.setUser_id(usersDTO.getUser_id());
		
		int result = storeService.deleteWishList(wishListDTO);
		
		if(result > 0) {
			List<WishListDTO> list = storeService.getWishList(usersDTO);
			model.addAttribute("wishlist", list);
			return "store/wishListRefresh";
		}
		
		return "/";
	}
	
	@GetMapping("addWishList")
	public String addWishList(WishListDTO wishListDTO, HttpSession session, Model model) throws Exception {
		
		UsersDTO usersDTO = (UsersDTO) session.getAttribute("users_info");
		
		if(usersDTO == null) {
			model.addAttribute("msg", -1);
			
			return "commons/result";
		}
		
		wishListDTO.setUser_id(usersDTO.getUser_id());
		
		int result = storeService.addWishList (wishListDTO);
		
		model.addAttribute("msg", result);
		
		return "commons/result";
	}
	
	
	@GetMapping("getWishList")
	public String getWishList(HttpSession session, Model model) throws Exception {
		
		UsersDTO usersDTO = (UsersDTO) session.getAttribute("users_info");
		
		if(usersDTO == null) {
			model.addAttribute("result", "로그인 후 이용하세요.");
			model.addAttribute("url","/users/login");
			
			return "commons/message";
		}
		
		List<WishListDTO> list = storeService.getWishList(usersDTO);
		
		model.addAttribute("wishlist", list);
		
		return "store/getWishList";
	}
	
	
	//============================== 상점,아이템관련 메소드 =============================
	//============================== 상점,아이템관련 메소드 =============================
	//============================== 상점,아이템관련 메소드 =============================

	@GetMapping("itemListRefresh")
	public String getItemList(ItemDTO itemDTO, Pager pager, Model model) throws Exception {

		pager.setPerPage(6);

		List<ItemDTO> list = storeService.getItemList(itemDTO, pager);
		
		//나중에 return하면 itemListRefresh.jsp로 리턴됨 > 비동기식
//		if(list.size()==0) {
//			System.out.println("go");
//			
//			model.addAttribute("result","result");
//			model.addAttribute("url", "/");
//			
//			return "commons/message";
//		}
		

		model.addAttribute("itemList", list);
		model.addAttribute("pager", pager);

		return "store/itemListRefresh";
	}

	// ajax로 리스트 조회하기 위해 jsp 찾아가는 경로만 작성
	// resources/js/storelist.js 스트립트 작성
	@GetMapping("itemList")
	public void getItemList(ItemDTO itemDTO, Model model) {
		model.addAttribute("itemDTO", itemDTO);
	}

	// Item 상세정보 조회
	@GetMapping("itemDetail")
	// category 리스트 조회 때 생긴 parameter 재사용
	public void getItemDetail(ItemDTO itemDTO, Model model) throws Exception {

		itemDTO = storeService.getItemDetail(itemDTO);

		model.addAttribute("dto", itemDTO);
	}

	@GetMapping("addItem")
	public void addItem() throws Exception {
	}

	// weaponDTO의 변수에 무기설명 변수가 더 있어 매개변수로 받아옴
	@PostMapping("addItem")
	public String addItem(ItemDTO itemDTO, MultipartFile attach, Model model, HttpSession session) throws Exception {

		int result = storeService.addItem(itemDTO, attach, session);

		if (result > 0) {
			model.addAttribute("result", "추가 성공");
		} else {
			model.addAttribute("result", "추가 실패");
		}

		model.addAttribute("url", "addItem");
		return "commons/message";
	}

	@GetMapping("updateItem")
	public String updateItem(ItemDTO itemDTO, Model model) throws Exception {

		itemDTO = storeService.getItemDetail(itemDTO);

		model.addAttribute("dto", itemDTO);

		return "store/addItem";
	}

	@PostMapping("updateItem")
	public String updateItem(ItemDTO itemDTO, HttpServletRequest request) throws Exception {
		
		int result = storeService.updateItem(itemDTO);

		if (result == 1) {
			request.setAttribute("result", "수정 성공");
		} else {
			request.setAttribute("result", "수정 실패");;
		}
		
		request.setAttribute("url", "itemDetail?item_num="+itemDTO.getItem_num());
		
		return "commons/message";
	}

	@GetMapping("deleteItem")
	public String deleteItem(ItemDTO itemDTO, Model model) throws Exception {

		int result = storeService.deleteItem(itemDTO);

		if (result == 1) {
			model.addAttribute("result", "삭제 성공");
		} else {
			model.addAttribute("result", "삭제 실패");
		}
		
		itemDTO = storeService.getItemDetail(itemDTO);

		model.addAttribute("url", "itemList?category_num="+itemDTO.getCategory_num());

		return "commons/message";
	}
}
