package kr.or.ddit.security.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security/join/")
public class SecurityMemberController {
	@Autowired
	private IMemberService service;
	
	@RequestMapping("loginForm")
	public String loginForm() throws Exception{
		return "security/loginForm";
	}
	
	@RequestMapping("loginCheck")
	public void loginCheck(String mem_id, String mem_pass,
				HttpSession session) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		MemberVO memberInfo = service.getMemberInfo(params);
		
		session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
	}
	
	@RequestMapping("logout")
	public void logout(HttpSession session) throws Exception{}
	
	@RequestMapping("sessionExpired")
	public void sessionDeplicate(HttpSession session){
		System.out.println("세션 유효시간 만료에따른 시간 연장");
		session.setMaxInactiveInterval(60*30);
	}
	
	@RequestMapping("sessionInvalid")
	public String sessionInvalid(HttpSession session){
		System.out.println("세션 무효화 처리에따른 후속 처리 : 최초 요청시 WAS에의해 생성된 세션은 삭제 후 ");
		System.out.println("\t\t\t 스프링 시큐리티컨텍스트에의해 재생성 처리 및 활용");
		return "security/loginForm";
	}
}





