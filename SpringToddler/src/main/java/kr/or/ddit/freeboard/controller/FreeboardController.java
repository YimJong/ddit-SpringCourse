package kr.or.ddit.freeboard.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.freeboard.service.IFreeBoardService;
import kr.or.ddit.utils.CryptoGenerator;
import kr.or.ddit.utils.RolePaginationUtil;
import kr.or.ddit.vo.FreeBoardVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/freeboard/")
public class FreeboardController {
	@Autowired
	private IFreeBoardService service;
	@Autowired
	private CryptoGenerator cryptoGen;
	@Autowired
	private MessageSourceAccessor accessor;
	
	// localhost/SpringToddler/user/freeboard/freeboardList.do
	@RequestMapping("freeboardList")
	public ModelAndView freeboardList(Map<String, String> params,
									  ModelAndView andView,
									  HttpServletRequest request,
									  HttpSession session,
									  String search_keyword,
									  String search_keycode,
									  String currentPage) throws Exception {
		Map<String, String> publicKeyMap = this.cryptoGen.generatePairKey(session);
		
		if(currentPage == null) {
			currentPage = "1";
		}
		
		params.put("search_keyword", search_keyword);
		params.put("search_keycode", search_keycode);
		String totalCount = service.totalCount(params);
		
		RolePaginationUtil pagination = new RolePaginationUtil(request, 
											Integer.parseInt(currentPage),
											Integer.parseInt(totalCount));
		
		params.put("startCount", String.valueOf(pagination.getStartCount()));
		params.put("endCount", String.valueOf(pagination.getEndCount()));
		
		List<FreeBoardVO> freeboardList = this.service.freeboardList(params);
		
		andView.addObject("freeboardList", freeboardList);
		andView.addObject("publicKeyMap", publicKeyMap);
		andView.addObject("paginationHtml", pagination.getPagingHtmls());
		andView.setViewName("user/freeboard/freeboardList");
		
		return andView;
	}
	
	
	@RequestMapping("freeboardForm")
	public void freeboardForm() {}
	
	@RequestMapping("insertFreeboard")
	public String insertFreeboard(FreeBoardVO freeboardInfo,
										ModelAndView andView,
										String message) throws Throwable {
		service.insertFreeboardTemp(freeboardInfo);
		
		message = accessor.getMessage("success.common.insert", Locale.KOREA);
		message = URLEncoder.encode(message, "UTF-8");

		return "redirect:/user/freeboard/freeboardList.do?insertMessage=" + message;
	}
	
	
	@RequestMapping("freeboardView")
	public Model freeboardView(String bo_no,
							   Model model,
							   Map<String, String> params,
							   FreeBoardVO freeboardInfo) throws Exception {
		
		params.put("bo_no", bo_no);
		freeboardInfo = service.freeboardInfo(params);
		
		model.addAttribute("freeboardInfo", freeboardInfo);
		
		return model;
	}
	
	
	@RequestMapping("deleteFreeboard")
	public String deleteFreeboard(ModelAndView andView,
										String bo_no,
										String message,
										Map<String, String> params) throws Exception {
		params.put("bo_no", bo_no);
		service.deleteFreeboard(params);
		
		message = accessor.getMessage("success.common.delete", Locale.KOREA);
		message = URLEncoder.encode(message, "UTF-8");
		
		return "redirect:/user/freeboard/freeboardList.do?deleteMessage=" + message;
	}
	
	
	@RequestMapping("freeboardReplyForm")
	public ModelMap freeboardReplyForm(ModelMap modelMap,
									FreeBoardVO freeboardInfo) {
		
		modelMap.addAttribute("freeboardInfo", freeboardInfo);
		
		return modelMap;
	}
}
