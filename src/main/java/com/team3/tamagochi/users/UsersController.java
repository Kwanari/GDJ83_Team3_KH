package com.team3.tamagochi.users;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.team3.tamagochi.mypet.MyPetDTO;
import com.team3.tamagochi.store.ItemDTO;
import com.team3.tamagochi.store.WeaponDTO;

@Controller
@RequestMapping("/users/*")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	// NaverLoginBO
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	
	@GetMapping("register")
	public void registerUsers() throws Exception{}
	
	@PostMapping("register")
	public String registerUsers(UsersDTO usersDTO, Model model) throws Exception{
		
		int result = usersService.registerUsers(usersDTO);
		
		if(result>0) {
			model.addAttribute("result", "회원가입 성공!");
			model.addAttribute("url", "/");
			
			// 회원가입시 디폴트 캐릭터 4개가 자동으로 생성되는 코드
			result = usersService.setDefaultCharacter(usersDTO);
		}
		
		return "commons/message";
	}
	
	
	@GetMapping("login")
	public void loginUsers(Model model, @CookieValue(name="remember", required=false, defaultValue="") String value, HttpSession session) throws Exception{
		
		model.addAttribute("remember", value);
		
		// 네이버 로그인 코드
		String naverAuthURL = naverLoginBO.getAuthorizationUrl(session);
		
		System.out.println("네이버:" + naverAuthURL);
		
		model.addAttribute("url", naverAuthURL);
		
	}
	
	@PostMapping("login")
	public String loginUsers(UsersDTO usersDTO, Model model, HttpSession session,
			String remember, HttpServletResponse response) throws Exception{
		
		// 아이디 저장 코드 (쿠키 사용)
		if(remember != null) {
			Cookie cookie = new Cookie("remember", usersDTO.getUser_id());
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("remember", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}	
		
		
		usersDTO = usersService.loginUsers(usersDTO);
		
		// 탈퇴한 회원은 resign값이 0이기 때문에 이 값으로 탈퇴한 회원인지 아닌지 구분.
		if(usersDTO != null && usersDTO.getUser_resign() == 1) {
			session.setAttribute("users_info", usersDTO);
			model.addAttribute("result", "환영합니다!");
			model.addAttribute("url", "/");
		}else if(usersDTO != null && usersDTO.getUser_resign() == 0){
			model.addAttribute("result", "탈퇴한 회원입니다.");
			model.addAttribute("url", "/");
		}else {
			model.addAttribute("result", "로그인 실패!");
			model.addAttribute("url", "/");
		}
		
		
		return "commons/message";
	}
	
	
	@GetMapping("logout")
	public String logoutUsers(HttpSession session) throws Exception{	
		
		session.invalidate(); // session의 유지시간을 0으로 만들어서 로그아웃 상태로 만듬
		
		return "redirect:/";
	}
	
	
	@GetMapping("mypage")
	public void getMyPage(HttpSession session, Model model) throws Exception{
		
		UsersDTO usersDTO = (UsersDTO)session.getAttribute("users_info");
		
		// 회원 정보 수정시, 로그인 정보가 session에 담겨있기 때문에 로그아웃을 했다가 다시 로그인해야 수정된 정보가 출력됨
		// updateUsersData라는 메서드를 만들어서 업데이트된 정보를 새로 호출함으로서 바로 보여줄 수 있게 만듬
		usersDTO = usersService.updateUsersData(usersDTO);
		
		model.addAttribute("usersDTO", usersDTO);	
	}
	
	
	@PostMapping("update")
	public String updateUsers(Model model, UsersDTO usersDTO) throws Exception{
					
		int result = usersService.updateUsers(usersDTO);
		
		if(result > 0) {
			model.addAttribute("result", "수정 성공!");
			model.addAttribute("url", "/users/mypage");
		}else {
			model.addAttribute("result", "수정 실패!!");
			model.addAttribute("url", "/users/mypage");
		}
		
		return "commons/message";
	}
	
	
	@GetMapping("deleteAccount")
	public void deleteAccount() throws Exception{}
	
	@PostMapping("deleteAccount")
	public String deleteAccount(UsersDTO usersDTO, HttpSession session, Model model) throws Exception{
		
		// session에 담긴 로그인 id를 임시 DTO에 담아둔 뒤, 그걸 다시 꺼내서 입력받은 usersDTO에 넣는 코드
		// 입력받은 usersDTO에는 id값이 없기 때문에 이러한 과정을 거침
		UsersDTO tempDTO = (UsersDTO)session.getAttribute("users_info");
		usersDTO.setUser_id(tempDTO.getUser_id());
		
		int result = usersService.deleteAccount(usersDTO);
		
		if(result > 0) {
			model.addAttribute("result", "회원 탈퇴가 완료되었습니다. 감사합니다.");
			model.addAttribute("url", "/");
			session.invalidate();
		}else {
			model.addAttribute("result", "올바른 비밀번호를 입력해주세요.");
			model.addAttribute("url", "/users/deleteAccount");
		}
		
		return "commons/message";
	}
	
	
	@GetMapping("inventory")
	public void getInvenList(UsersDTO usersDTO, Model model, HttpSession session) throws Exception{
		
		usersDTO = (UsersDTO)session.getAttribute("users_info");
		
		List<ItemDTO> list = usersService.getInvenList(usersDTO);
		
		model.addAttribute("list", list);
		
		// 인벤토리에서 '현재 캐릭터'가 착용한 장비 여부를 확인하는 메서드
		// Mapper에서 만들어둔 getMyPetNum 쿼리를 재활용
		MyPetDTO myPetDTO = new MyPetDTO();
		myPetDTO = usersService.getMyPetNum(usersDTO);
		model.addAttribute("myPetDTO", myPetDTO);
	}
	
	@GetMapping("equipItem")
	public String equipItem(ItemDTO itemDTO, HttpSession session, Model model, String inventory_num) throws Exception{
		
		// 아이템 착용 버튼 클릭시 '어느 캐릭터'에 아이템 값을 업데이트할건지 정하려면 현재 사용중인 캐릭터를 알아야 됨
		// 현재 사용중인 Pet_num을 구하기 위해 session에서 user_id를 꺼내서 계산
		UsersDTO usersDTO = (UsersDTO)session.getAttribute("users_info");
		MyPetDTO myPetDTO = new MyPetDTO();
		myPetDTO = usersService.getMyPetNum(usersDTO);
		
		
		// 업데이트를 하려면 MyPetDTO의 Pet_num과 ItemDTO 총 2개의 parameter가 필요
		// 때문에 MyPetDTO에 ItemDTO 값을 대입해서 parameter를 MyPetDTO 하나만 사용
		myPetDTO.setEquip_num(Long.parseLong(inventory_num));
		myPetDTO.setAdd_hp(itemDTO.getItem_hp());
		myPetDTO.setAdd_atk(itemDTO.getItem_atk());
		myPetDTO.setAdd_dod(itemDTO.getItem_dod());
		int result = usersService.equipItem(myPetDTO);
		
		model.addAttribute("msg", result);
		
		return "commons/result";
	}
	
	@GetMapping("takeOffItem")
	public String takeOffItem(HttpSession session, Model model) throws Exception{
		
		// 아이템 해제 버튼 클릭시 '어느 캐릭터'에 아이템 값을 초기화할건지 정하려면 현재 사용중인 캐릭터를 알아야 됨
		// 현재 사용중인 Pet_num을 구하기 위해 session에서 user_id를 꺼내서 계산
		UsersDTO usersDTO = (UsersDTO)session.getAttribute("users_info");
		MyPetDTO myPetDTO = new MyPetDTO();
		myPetDTO = usersService.getMyPetNum(usersDTO);
		
		int result = usersService.takeOffItem(myPetDTO);
		
		model.addAttribute("msg", result);
		
		return "commons/result";
	}
	
	
	// 회원가입시 입력한 id값이 중복인지 아닌지 검사하는 JS 이벤트 메서드
	@GetMapping("checkID")
	public String checkID(UsersDTO usersDTO, Model model) throws Exception{
		
		UsersDTO check = usersService.checkID(usersDTO);
		int result;
		
		// 넘겨받은 user_id를 DB에서 검색, 검색 결과가 있으면 result를 1로 만듬
		// 검색 결과가 없으면 result를 0으로 만듬
		if(usersDTO.getUser_id().equals(check.getUser_id())) {
			result = 1;
		}else {
			result = 0;
		}
		
		model.addAttribute("msg", result);
		
		return "commons/result";
	}
	
	// 회원 정보 수정시 입력한 패스워드가 올바른 패스워드인지 검사하는 JS 이벤트 메서드
	@GetMapping("checkPW")
	public String checkPW(UsersDTO usersDTO, HttpSession session, Model model) throws Exception{
		
		UsersDTO tempDTO= (UsersDTO)session.getAttribute("users_info");
		usersDTO.setUser_id(tempDTO.getUser_id());
		System.out.println(usersDTO.getUser_pw());
		System.out.println(usersDTO.getUser_id());
		
		usersDTO = usersService.checkPW(usersDTO);
		int result;
		
		
		if(usersDTO != null) {
			result = 1;
		}else {
			result = 0;
		}
		
		model.addAttribute("msg", result);
		
		return "commons/result";		
	}
	
	// 이름, 이메일, 전화번호로 아이디를 찾는 메서드
	@GetMapping("findID")
	public void findID() throws Exception{}
	
	@PostMapping("findID")
	public String findID(UsersDTO usersDTO, Model model) throws Exception{
		
		List<UsersDTO> usersDTOs = usersService.findID(usersDTO);
		
		
		if(usersDTOs.size() == 0) {
			model.addAttribute("result", "입력하신 정보로 가입된 아이디가 없습니다. 올바르게 입력하였는지 다시 한 번 확인해 주세요.");
			model.addAttribute("url", "/users/findID");
		}else if(usersDTOs.size() > 0){
			model.addAttribute("usersDTOs", usersDTOs);
			return "users/resultID";  // 주소는 findID그대로, 보여주는 창만 resultID JSP를 보여줌
		}
		
		return "commons/message";
	}
	
	// 아이디, 이름, 이메일, 전화번호로 패스워드를 찾는 메서드
	@GetMapping("findPW")
	public void findPW() throws Exception{}

	@PostMapping("findPW")
	public String findPW(UsersDTO usersDTO, Model model) throws Exception{
		
		usersDTO = usersService.findPW(usersDTO);
		
		if(usersDTO == null) {
			model.addAttribute("result", "입력하신 정보와 일치하는 패스워드가 없습니다. 올바르게 입력하였는지 다시 한 번 확인해 주세요.");
			model.addAttribute("url", "/users/findPW");
		}else if(usersDTO != null) {
			model.addAttribute("usersDTO", usersDTO);
			return "users/resultPW";
		}
		
		return "commons/message";
	}
	
	// 네이버 로그인 성공시 callback 호출 메서드
	@RequestMapping(value = "callback", method= {RequestMethod.GET, RequestMethod.POST})
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws Exception{
		
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		// 1. 로그인 사용자 정보를 읽어온다
		apiResult = naverLoginBO.getUserProfile(oauthToken);  // String 형식의 JSON 데이터
		
		/**
		apiResult json 구조
		{"resultcode":"00",
		"message":"success",
		"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		**/
		
		// 2. String 형식인 apiResult를 JSON형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject)obj;
		
		System.out.println(jsonObj);
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		
		//response에서 DB에 저장할 데이터 파싱
		String nickname = (String)response_obj.get("nickname");
		String mobile = (String)response_obj.get("mobile");
		String name = (String)response_obj.get("name");
		String id = (String)response_obj.get("id");
		String email = (String)response_obj.get("email");
		
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setUser_nickname(nickname);
		usersDTO.setUser_phone(mobile);
		usersDTO.setUser_name(name);
		usersDTO.setUser_id(id);
		usersDTO.setUser_email(email);
		
		//4.파싱 데이터 세션으로 저장
		session.setAttribute("users_info", usersDTO); //세션 생성
		
		// 5. API로 로그인 했을 때 해당 id가 DB에 없으면 DB에 유저 정보를 insert한다
		// DB에 유저 정보를 insert할 때 동시에 기본 캐릭터 생성 메서드도 실행
		List<UsersDTO> checkID = usersService.findID(usersDTO);
		if(checkID.size()==0) {
			int result = usersService.insertNaverProfile(usersDTO);
			result = usersService.setDefaultCharacter(usersDTO);
		}
		
		
		return "redirect:/";
	}
	
	/*  거래 관련 메서드들 (이렇게 많을 줄 알았으면 패키지를 따로 만드는건데 실수함)  */
	// 유저의 거래 내역 조회 메서드
	@GetMapping("tradeList")
	public void getTradeList(Model model, TransactionDTO transactionDTO, HttpSession session) throws Exception{
		
		UsersDTO usersDTO = new UsersDTO();
		usersDTO = (UsersDTO)session.getAttribute("users_info");
		transactionDTO.setUser_id(usersDTO.getUser_id());
		
		List<TransactionDTO> list = usersService.getTradeList(transactionDTO);
		
		model.addAttribute("list", list);
	}
	
	// transaction_type에 따른 거래 내역 정렬 메서드
	@GetMapping("tradeList/select")
	public String selectType(Model model, TransactionDTO transactionDTO, HttpSession session) throws Exception{
		
		UsersDTO usersDTO = new UsersDTO();
		usersDTO = (UsersDTO)session.getAttribute("users_info");
		transactionDTO.setUser_id(usersDTO.getUser_id());
		
		List<TransactionDTO> list = usersService.selectType(transactionDTO);
		
		model.addAttribute("list", list);
		
		return "users/selectTypeForm"; 
	}
	
	
	
	
}
