package kr.or.ddit.security.event.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public class SessionDestroyListener implements ApplicationListener<SessionDestroyedEvent> {
	
	@Autowired
	private HttpSession session;
	
	// SecurityContext의 생성 시점은 SecurityContextPersistenceFilter 필터내에서 HttpSessionSecurityContextRepository 
	// 클래스를 사용하여 생성
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		// 1. SecurityContext 취득 방법  
		List<SecurityContext> contexts = event.getSecurityContexts();
		for(SecurityContext context : contexts){
			String mem_id = (String) context.getAuthentication().getPrincipal();
			String mem_pass = (String) context.getAuthentication().getCredentials();
			System.out.println("1) 아이디 : " + mem_id + " | 패스워드 : " + mem_pass);
		}
		
		long destroyTime = event.getTimestamp();
		System.out.println("세션 무효화 시간 : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(destroyTime)));
	}
	
}
