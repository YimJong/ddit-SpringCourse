package kr.or.ddit.security.handler;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	// HTTP Status 403 - Access is denied : 권한없는 사용자의 접근 및 허용되지않는 url 접근 처리
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		String message = URLEncoder.encode("미인가된 권한 또는 허용되지않는 URL로 접근하였습니다.", "UTF-8");
		response.sendRedirect("/security/join/loginForm.do?flag="+message);
	}
}
