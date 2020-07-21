package kr.or.ddit.security.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component("loginSuccessHandler")
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	// 로그인 성공 후 처리.
	// HttpSessionSecurityContextRepository에서 관리하는 SessionRequestWrapper로 랩핑된 HttpServletRequest
	// HttpSessionSecurityContextRepository에서 관리하는 SessionResponseWrapper로 랩핑된 HttpServletResponse
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("CustomSuccessHandler의 onAuthenticationSuccess()");
		// 로그인 아이디
		String name = authentication.getName();
		// 로그인 패스워드(취득 불가)
		Object credential = authentication.getCredentials();
		// 로그인 사용자 객체(ROLE권한 확인)
		User principal = (User) authentication.getPrincipal();
		// 클라이언트 아이피주소, JSESSIONID 확인.
		WebAuthenticationDetails detaile = (WebAuthenticationDetails) authentication.getDetails();
		boolean isAuthenticated = authentication.isAuthenticated();
		
		// default-target-url와 authentication-success-handler-ref는 병행 실행되지 않으므로 포워딩 처리.
		RequestDispatcher rd = 
				request.getRequestDispatcher("/member/memberList.do");
		rd.forward(request, response);
	}
	
}
