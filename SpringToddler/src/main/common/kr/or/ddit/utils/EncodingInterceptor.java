package kr.or.ddit.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


// 클라이언트 요청(인터셉터 선언 순서대로 동작)
//	 1. preHandle() 콜백 (default return true)
//	 2. 컨트롤러 클래스 내 클라이언트의 해당 요청을 처리하는 컨트롤러 메서드 콜백
//   3. postHandle() 콜백
//	 4. afterCompletion() 콜백
//   5. 응답 컨텐츠가 클라이언트 대상 전송

// 클라이언트 비동기식 요청(인터셉터 선언 순서대로 동작)
//	 1. preHandle() 콜백 (default return true)
//	 2. 컨트롤러 클래스 내 클라이언트의 해당 요청을 처리하는 컨트롤러 메서드 콜백
//   3. 응답 컨텐츠가 클라이언트 대상 전송 (@ResponseBody 이용)



								// *** 상속 필요
public class EncodingInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void afterCompletion(HttpServletRequest request,
							    HttpServletResponse response, 
							    Object handler, 
							    Exception ex)
			throws Exception {
		System.out.println("postHandle() 종료 후 컨트롤러 메서드의 반환값이 ViewResolver에 "
				+ "전달로 응답 컨텐츠가 HttpServletResponse의 출력버퍼에 저장된 직후 콜백 (가장 마지막)");
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
		  									   HttpServletResponse response, 
		  									   Object handler) throws Exception {
		System.out.println("비동기식 통신(Ajax) 요청 시 postHandle() 콜백 메서드와 afterCompletion()콜백 메서드가 무시되고 콜백");
	}

	@Override
	public void postHandle(HttpServletRequest request,
		  	               HttpServletResponse response, 
		  	               Object handler,
		  	               ModelAndView modelAndView) throws Exception {
		System.out.println("컨트롤러 클래스 내 클라이언트의 해당 요청을 처리하는 컨트롤러 메서드 종료 후 콜백");
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
						     HttpServletResponse response, 
						     Object handler) throws Exception {
		System.out.println("컨트롤러 클래스 내 클라이언트의 해당 요청을 처리하는 컨트롤러 메서드 호출 전에 콜백");
		
		String encodingType = request.getCharacterEncoding(); // 특정 인코딩을 취득하는 메서드
		
		if(StringUtils.isEmpty(encodingType)) { // 인코딩 타입이 null 이고 "" 일 때
			encodingType = "UTF-8";
		}
		
		request.setCharacterEncoding(encodingType);
		
		
		return true;
		// return false; : 컨트롤러 메서드 호출 무시.
		// return true;  : 컨트롤러 메서드 호출(default)
	}
}
