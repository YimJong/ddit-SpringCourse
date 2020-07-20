package kr.or.ddit.join.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

// /SpringToddler/user/join/loginForm.do
// /SpringToddler/user/join/loginCheck.do
// /SpringToddler/user/join/loginOut.do
@Controller
@RequestMapping("/user/join/")
public class JoinController {
	@Autowired
	private IMemberService service;
	@Autowired
	private MessageSourceAccessor accessor;
	
	@RequestMapping("loginForm")
	public void loginForm(HttpServletRequest request) {
		// 반환값 : join/loginForm
		// InternalResourceViewResolver가 반환값을 취득
		// prefix(/WEB-INF/views/user/)
		// suffix(.jsp)
		// /WEB-INF/views/user/join/loginForm.jsp 포워딩 처리
		//return "user/join/loginForm";
		
		// RedirectAttribute를 활용해 전송되는 값 취득
		Map<String, ?> paramMap = RequestContextUtils.getInputFlashMap(request);
		if(paramMap != null) {
			String message = (String) paramMap.get("message");
			System.out.println("RedirectAttribute 전달된 취득값 : " + message);
		}
	}
	
//	/SpringToddler/user/join/loginCheck.do
//  POST 전송방식 : mem_id=a001&mem_pass=asdfasdf	
	@RequestMapping(value="loginCheck", method=RequestMethod.POST, // GET일 경우 GET으로 요청됬을 때만 메서드 콜백됨.
			      params={"mem_id=a001"}) 
	public String loginCheck(String mem_id, 
							 String mem_pass, 
							 HttpServletRequest request,
							 HttpSession session,
							 HttpServletResponse response,
							 String message) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		MemberVO memberInfo = this.service.memberInfo(params);
		
		if(memberInfo == null) {
			// 리다이렉트(컨텍스트 루트|패스 생략)
			message = this.accessor.getMessage("fail.common.join", Locale.KOREA);
			message = URLEncoder.encode(message, "UTF-8");
			return "redirect:/user/join/loginForm.do?message=" + message;
		} else {
			// 포워드(컨텍스트 루트|패스 생략)
			session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
			return "forward:/user/freeboard/freeboardList.do";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session,
						 String message) throws UnsupportedEncodingException {
		session.invalidate();
		message = this.accessor.getMessage("success.common.logout", Locale.KOREA);
		message = URLEncoder.encode(message, "UTF-8");
		
		return "redirect:loginForm.do?message=" + message;
	}
}


