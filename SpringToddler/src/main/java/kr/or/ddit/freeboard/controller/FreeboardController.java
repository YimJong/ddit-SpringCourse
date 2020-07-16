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

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
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
	public ModelAndView insertFreeboard(FreeBoardVO freeboardInfo,
										ModelAndView andView,
										String message) throws Throwable {
		service.insertFreeboardTemp(freeboardInfo);
		
		message = accessor.getMessage("success.common.insert", Locale.KOREA);
		message = URLEncoder.encode(message, "UTF-8");
		andView.addObject("insertMessage", message);
		andView.setViewName("user/freeboard/freeboardList");

		return andView;
	}
}