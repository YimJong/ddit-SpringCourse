package kr.or.ddit.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestContrller {
	@RequestMapping("/test/hello.first")
	public String TestResult() {
		System.out.println("테스트 컨트롤러 실행!");
		return "test/hello";
	}
}
