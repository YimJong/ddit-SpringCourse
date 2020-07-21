package kr.or.ddit.member.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SearchConditionVO;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/")
public class MemberController{
	@Autowired
	private IMemberService service;
	
	// 프로퍼티스 Injection 처리
	// util:properties 등록시의 id 속성값과 일치하는 변수명 선언과 @Autowired를통해
	// 주입처리가능
	@Autowired
	private Properties jdbc;
	@Autowired
	private Properties mysql;
	@Autowired
	private Properties oracle;
	
	@RequestMapping("memberForm")
	private String memberForm(){
		Iterator<Object> keys = this.jdbc.keySet().iterator();
		while(keys.hasNext()){
			String key = (String) keys.next();
			System.out.println("key : " + key + " / value : " + this.jdbc.get(key));
		}
		
		return "member/memberForm";
	}
	
	@RequestMapping("idCheck")
	private ModelAndView idCheck(String mem_id, ModelAndView mav) throws SQLException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		MemberVO memberInfo = service.getMemberInfo(params);
		
		Map<String, String> jsonMap = new HashMap<String, String>();
		if(memberInfo == null){
			jsonMap.put("flag", "true");
		}else{
			jsonMap.put("flag", "false");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(jsonMap);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mav.addObject("jsonData", jsonData);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping("idPictureForm")
	private String idPictureForm(){
		return "member/idPictureForm";
	}
	
	@RequestMapping("idPicFileupload")
	public ModelAndView idPicFileupload(MultipartHttpServletRequest request,
			ModelAndView mav) throws SQLException{
		
		String jsonData = this.service.insertIdPictureInfo(request);
		
		mav.addObject("jsonData", jsonData);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping("memberList")
	private ModelAndView memberList(ModelAndView mav, SearchConditionVO conditionVO,
			@RequestHeader(value="Content-Type", required=false) String contentType,
			HttpSession session) throws SQLException{
		// 스프링에의해서 로그인시 세션에 저장되는 정보.(디버깅 정보)
//		{SPRING_SECURITY_CONTEXT=org.springframework.security.core.context.SecurityContextImpl@44316364
//								Authentication: org.springframework.security.authentication.UsernamePasswordAuthenticationToken@44316364: 
//								Principal     : org.springframework.security.core.userdetails.User@2cd230: 
//								Username      : a001; 
//								Password: [PROTECTED]; 
//								Enabled: true; 
//								AccountNonExpired: true; 
//								credentialsNonExpired: true; 
//								AccountNonLocked: true; 
//								Granted Authorities: ROLE_USER; 
//								Credentials: [PROTECTED]; 
//								Authenticated: true; 
//								Details: org.springframework.security.web.authentication.WebAuthenticationDetails@b364: 
//								RemoteIpAddress: 0:0:0:0:0:0:0:1; 
//								SessionId: null; 
//								Granted Authorities: ROLE_USER
//		}
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
	        String name = (String) attributeNames.nextElement();
	        if (name.equals("SPRING_SECURITY_CONTEXT")) {
	            SecurityContext securityContext = 
	                (SecurityContext) session.getAttribute(name);
	            Authentication authentication = securityContext.getAuthentication();
	            User principal = (User) authentication.getPrincipal();
	            WebAuthenticationDetails details = 
	                (WebAuthenticationDetails) authentication.getDetails();
	            String username = authentication.getName();
	            String password = (String) authentication.getCredentials();
	 
	            System.out.println();
	            System.out.println("name = " + name + " , value = " + securityContext.toString());
				System.out.println("authentication : " + authentication.toString());
				System.out.println("principal : " + principal);
				System.out.println("details : " + details.toString());
				System.out.println("username : " + username);
				System.out.println("password : " + password);
				System.out.println();
		    }
		}

		if(conditionVO.getSearch_count() == null){
			conditionVO.setSearch_count(String.valueOf(10));
		}
		List<MemberVO> memberList = service.getMemberList(conditionVO);

		// ajax 요청시의 content-type application/x-www-form-urlencoded
		// 그외 요청은 null
		if(contentType != null && contentType.startsWith("application")){
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = "";
			try {
				jsonData = mapper.writeValueAsString(memberList);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mav.addObject("jsonData", jsonData);
			mav.setViewName("jsonView");
		}else{
			mav.addObject("memberList", memberList);
			mav.setViewName("member/memberMgr");
		}
		return mav;
	}
	
	@RequestMapping("memberView")
	private ModelAndView memberView(String mem_id, ModelAndView mav) throws SQLException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		
		MemberVO memberInfo = service.getMemberInfo(params);
		
		mav.addObject("memberInfo", memberInfo);
		mav.setViewName("member/memberView");
		return mav;
	}
	
	@RequestMapping("memberUpdate")
	private String updateMemberInfo(MemberVO memberInfo) throws SQLException{
		service.updateMemberInfo(memberInfo);
		return "redirect:/member/memberList.do";
	}
	
	@RequestMapping("insertMemberInfo")
	public String insertMemberInfo(MemberVO memberInfo) throws SQLException{
		this.service.insertMemberInfo(memberInfo);
		return "redirect:/member/memberList.do";
	}
}





















