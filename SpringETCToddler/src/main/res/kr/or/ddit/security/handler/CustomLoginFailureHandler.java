package kr.or.ddit.security.handler;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("loginFailureHandler")
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		System.out.println("CustomFailureHandler의 onAuthenticationFailure()");
		// 비인가 사용자를 실제 익셉션을 추가하여 익셉션 처리.
		exception.addSuppressed(new RuntimeException("비인가된 사용자입니다."));
		// 익셉션 발생 원인(BadCredentialsException)
		Throwable exception_type =  exception.getCause();
		String localizedMsg = exception.getLocalizedMessage();
		exception.printStackTrace();
		
		String message = URLEncoder.encode("비인가된 사용자입니다.", "UTF-8");
		
		// authentication-failure-url와 authentication-failure-handler-ref는 병행 실행되지 않으므로 포워딩 처리.
		RequestDispatcher rd = 
				request.getRequestDispatcher("/security/join/loginForm.do?flag="+message);
		rd.forward(request, response);
	}
	
}
