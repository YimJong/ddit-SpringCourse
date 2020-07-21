package kr.or.ddit.security.handler;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component("logoutSuccessHandler")
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("CustomLogoutSuccessHandler onLogoutSuccess()");
		
		if (authentication != null && authentication.getDetails() != null) {  
			request.getSession().invalidate();  
		}  
		
		response.setStatus(HttpServletResponse.SC_OK);
		// logout-success-url와 success-handler-ref는 병행 선언될수 없음.
		String message = URLEncoder.encode("로그아웃되었읍니다.", "UTF-8");
		response.sendRedirect(request.getContextPath() + "/security/join/loginForm.do?flag="+message);  

	}
	
}
