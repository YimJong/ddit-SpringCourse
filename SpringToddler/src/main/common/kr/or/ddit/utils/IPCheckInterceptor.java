package kr.or.ddit.utils;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class IPCheckInterceptor extends HandlerInterceptorAdapter {
	private static Map<String, String> ipMap;
	
	static {
		ipMap = new HashMap<String, String>();
		ipMap.put("127.0.0.1", "A");
		ipMap.put("0:0:0:0:0:0:0:1", "A");
		ipMap.put("192.168.206.48", "A");
		ipMap.put("192.168.206.23", "A");
		ipMap.put("192.168.206.52", "F");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
							    HttpServletResponse response, 
							    Object handler, 
							    Exception ex)
			throws Exception {
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
											   HttpServletResponse response, 
											   Object handler) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			 			   HttpServletResponse response,
			 			   Object handler,  
			 			   ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, 
							 Object handler) throws Exception {
		
		String clientIP = request.getRemoteAddr(); // 클라이언트 아이피 주소 취득
		boolean flag = true;
		
		if(StringUtils.isNotEmpty(clientIP) && ipMap.containsKey(clientIP)) { // null, ""이 아닌경우
			if("A".intern() == ipMap.get(clientIP).intern()) { // "A"등급인지 아닌지 결정.
			} else {
				flag = false;
			}
		} else { // Map에 없는 아이피로 접근하는 경우
			flag = false;
		}
		
		if(!flag) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charsht=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<font color='red'>잘생긴놈 꺼져..</font>");
			out.println("</body>");
			out.println("</html>");
		}
		
		return flag;
	}
}
