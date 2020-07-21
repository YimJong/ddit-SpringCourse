package kr.or.ddit.popup.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.ZipCodeVO;
import kr.or.ddit.zipcode.service.IZipCodeService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/zipcode/")
public class ZipcodeController {
	@Autowired
	private IZipCodeService service;
	
	@RequestMapping("zipcodeSearchForm")
	private String zipcodeSearchForm(){
		return "zipcode/zipcodeSearchForm";
	}
	
	@RequestMapping("zipcodeSearch")
	private ModelAndView zipcodeSearch(String dong, ModelAndView mav){
		Map<String, String> params = new HashMap<String, String>();
		params.put("dong", dong);
		
		List<ZipCodeVO> zipcodeList = service.getZipCodeList(params);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(zipcodeList);
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
}
