package kr.or.ddit.security.encode;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("customPWDEncoder")
public class CustomPasswordEncoder implements PasswordEncoder {
	
	// 회원가입시 유저가 입력한 비밀번호를 암호화하여 DB에 저장.
	@Override
	public String encode(CharSequence rawPassword) {
		if(!"userNotFoundPassword".equals(rawPassword)){
			// 암호화 처리. 랜덤한 8bit salt를 통한 취득한 로그인시 패스워드 대상 암호화 처리.
			String cryptPWD = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
			
			System.out.println("CustomPasswordEncoder encode() rawPassword : " + rawPassword + " | cryptPWD : " + cryptPWD);
			return cryptPWD;
		}else{
			return null;
		}
	}

	// 파라메터 : 1. 로그인 UI로부터 취득한 패스워드
	//            2. DB로부터 취득한 암호화된? 패스워드
	// 비교 후 일치 여부 반환.
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("CustomPasswordEncoder matches() rawPassword : " + rawPassword + " | encodedPassword : " + encodedPassword);
        return true;
	}
	
}
