package kr.or.ddit.security.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomCryptoAuthenticationService implements UserDetailsService {

	@Autowired
	private IMemberService service;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", username);
		
		MemberVO memberInfo = null;
		try {
			memberInfo = this.service.getMemberInfo(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(memberInfo == null){
			throw new BadCredentialsException("사용자 인증에 실패하였습니다.");
		}else{
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
			
			UserDetails userDetails = new User(memberInfo.getMem_id(), memberInfo.getMem_pass()+"ㅆㅆ", 
					                           true, true, true, true, roleGrants);
			return userDetails;
		}
	}
}
