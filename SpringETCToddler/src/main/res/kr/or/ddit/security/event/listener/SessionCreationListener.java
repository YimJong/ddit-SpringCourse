package kr.or.ddit.security.event.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionCreationEvent;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public class SessionCreationListener implements ApplicationListener<SessionCreationEvent> {
	
	@Override
	public void onApplicationEvent(SessionCreationEvent event) {
		long creationTime = event.getTimestamp();
		System.out.println("세션 생성 시간 : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(creationTime)));
	}
	
}
