package kr.or.ddit.security.provider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private IMemberService service;
	
	private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		logger.debug("로그인 사용자 인증 설정 처리 시작");
		
		String mem_id = (String) authentication.getPrincipal();
		String mem_pass = (String) authentication.getCredentials();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
	
		MemberVO memberInfo = null;
		try {
			 memberInfo = this.service.getMemberInfo(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(memberInfo != null){
			logger.info("사용자를 확인하였습니다.");
			
			List<GrantedAuthority> roleGrants = new ArrayList<GrantedAuthority>();

			if("admin".intern() != memberInfo.getMem_id()){
				// 일반 유저
				// GrantedAuthority : 권한 선언 및 설정 자원.
				roleGrants.add(new SimpleGrantedAuthority("ROLE_USER"));
			}else{
				// 관리자 
				roleGrants.add(0, new SimpleGrantedAuthority("ROLE_ADMIN"));
				roleGrants.add(1, new SimpleGrantedAuthority("ROLE_USER"));
			}
			UsernamePasswordAuthenticationToken result = 
					new UsernamePasswordAuthenticationToken(mem_id, mem_pass, roleGrants);
			
			result.setDetails(memberInfo);
			
			return result;
		}else{
			logger.error("사용자를 확인할수없습니다.", 
					new BadCredentialsException("사용자를 확인할수없습니다."));
		}
		// null 반환시 사용자 인증 실패 처리됨.
		return null;
	}
	
	// false 반환시 상기 authenticate() 콜백을 수행하지 않음.
	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
