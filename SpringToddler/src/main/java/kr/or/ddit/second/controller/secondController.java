package kr.or.ddit.second.controller;

import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class secondController implements ApplicationContextAware{
//public class secondController {
	@RequestMapping("/second/hello.second")
	public String secondMethod() {
		System.out.println("secondController 실행!");
		return "second/hello";
	}
	
	@Override
	public void setApplicationContext(ApplicationContext webApplicationContext)
			throws BeansException {
		// setApplicationContext()은 컨트롤러 메서드(firstMethod()) 콜백 전 호출
		// 설정 파일 내 해당 빈 등록시 id속성값을 활용한 해당 빈의 인스턴스 취득
		MemberVO memberInfo = (MemberVO) webApplicationContext.getBean("memberInfo");
		MemberVO m1 = (MemberVO) webApplicationContext.getBean("m1");
		MemberVO m2 = (MemberVO) webApplicationContext.getBean("m2");
		MemberVO m3 = (MemberVO) webApplicationContext.getBean("m3", MemberVO.class);
		
		System.out.println(System.identityHashCode(memberInfo));
		System.out.println(System.identityHashCode(m1));
		System.out.println(System.identityHashCode(m2));
		System.out.println(System.identityHashCode(m3));
	}
}
