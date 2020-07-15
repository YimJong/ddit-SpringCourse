package kr.or.ddit.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


// /SpringToddler/user/member/memberList.do
// /SpringToddler/user/member/memberView.do
// /SpringToddler/user/member/memberForm.do
@Controller
@RequestMapping("/user/member")
public class MemberController {
	@Autowired
	private IMemberService service;
	
	@RequestMapping("memberList")
	public Model memberList(String search_keycode,
							String search_keyword,
							Map<String, String> params,
							Model model) {
//		Map<String, String> params = new HashMap<String, String>();
		params.put("search_keycode", search_keycode);
		params.put("search_keyword", search_keyword);
		
		List<MemberVO> memberList = this.service.memberList(params);
		
		// memberList => view resolver => memberList.jsp 전달
		// Model model = new ExtendedModelMap();
		model.addAttribute("memberList", memberList);
		// 모델은 viewResolver에 보내야할 문자열을 알아서 추출하여 전달함.
		
		return model;
	}
	
	@RequestMapping("memberView")
	public ModelMap memberView(String mem_id,
							   Map<String, String> params,
							   ModelMap modelMap){
		params.put("mem_id", mem_id);
		
		MemberVO memberInfo = this.service.memberInfo(params);
		
//		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("memberInfo", memberInfo);
		
		return modelMap;
	}
	
	@RequestMapping("updateMemberInfo")
	private String updateMember(MemberVO memberInfo) {
		this.service.updateMemberInfo(memberInfo);
		
		return "redirect:/user/member/memberList.do";
	}
	
	
	// /user/member/deleteMemberInfo.do?user_id=a001
	@RequestMapping("deleteMemberInfo")
	public String deleteMemberInfo(@RequestParam(required=false, defaultValue="null 대체값") String mem_id,
									Map<String, String> params,
									String message) throws UnsupportedEncodingException {
		params.put("mem_id", mem_id);
		this.service.deleteMemberInfo(params);
		message = URLEncoder.encode("탈퇴 되었습니다.", "UTF-8");
		
		return "forward:/user/member/memberList.do?message=" + message;
	}
	
}
