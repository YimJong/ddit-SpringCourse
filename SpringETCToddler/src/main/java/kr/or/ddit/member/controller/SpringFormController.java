package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.common.CommonData;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOFormTag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// Spring Form Tag 활용
@Controller
@RequestMapping("/formTag/")
public class SpringFormController {
	@Autowired
	private IMemberService service;
	
	@RequestMapping("memberForm_formTag")
	private void memberForm(){
	}
	
	// <form:form commandName과 modelAttribute속성 테스트
	@RequestMapping("memberView_formTag")
//	private ModelAndView memberView(MemberVOFormTag memberInfo, ModelAndView mav){
	private String memberView(@ModelAttribute("memberInfo") MemberVOFormTag memberInfo){
		
		memberInfo.setMem_hometelMap(CommonData.getAreaCode());
		memberInfo.setMem_comtelMap(CommonData.getAreaCode());
		memberInfo.setMem_hpMap(CommonData.getHPCode());
		memberInfo.setMem_mailMap(CommonData.getMailHostCode());
		// form:checkboxes의 path값
		memberInfo.setMem_gender("male");
		// form:checkboxes의 items 값
		memberInfo.setMem_genderes(CommonData.getGender());
		
//		mav.addObject("memberInfo", memberInfo);
//		mav.setViewName("formTag/memberView_formTag");
//		return mav;
		return "formTag/memberView_formTag";
	}
	
	@RequestMapping("updateMemberInfo_formTag")
	private String updateMemberInfo(MemberVOFormTag memberInfo,
			HttpServletRequest request){
		System.out.println("mem_id : " + memberInfo.getMem_id());
		System.out.println("mem_pass : " + memberInfo.getMem_pass());
		System.out.println("mem_name : " + memberInfo.getMem_name());
		
		request.setAttribute("memberInfo", memberInfo);
		return "redirect:/member/memberList.do";
	}
	
	@RequestMapping("insertMemberInfo")
	private String insertMemberInfo(MemberVOFormTag memberInfo){
		return "redirect:/member/memberList.do";
	}
}
