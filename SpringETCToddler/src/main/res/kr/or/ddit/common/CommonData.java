package kr.or.ddit.common;

import java.util.HashMap;
import java.util.Map;

public class CommonData {
	private static Map<String, String> areaCodeMap;
	private static Map<String, String> hpCodeMap;
	private static Map<String, String> mailHostMap;
	private static String[] gender;
	
	static{
		// 지역번호
		areaCodeMap = new HashMap<String, String>();
		areaCodeMap.put("02", "02");
		areaCodeMap.put("031","031");
		areaCodeMap.put("032","032");								        	
		areaCodeMap.put("033","033");				        	
		areaCodeMap.put("041","041");
		areaCodeMap.put("042","042");				        	
		areaCodeMap.put("043","043");				        	
		areaCodeMap.put("051","051");				        	
		areaCodeMap.put("052","052");
		areaCodeMap.put("053","053");				        					        	
		areaCodeMap.put("061","061");
		areaCodeMap.put("062","062");
		areaCodeMap.put("063","063");				        					        					        	
		areaCodeMap.put("064","064");				        					        					        	
		areaCodeMap.put("070","070");
		
		hpCodeMap = new HashMap<String, String>();
		hpCodeMap.put("010","010");
		hpCodeMap.put("016","016");				        	
		hpCodeMap.put("017","017");				        	
		hpCodeMap.put("019","019");
		
		mailHostMap = new HashMap<String, String>();
		mailHostMap.put("naver.com","naver.com");
		mailHostMap.put("daum.net","daum.net"); 
		mailHostMap.put("hanmail.net","hanmail.net");
		mailHostMap.put("nate.com","nate.com");
		mailHostMap.put("gmail.com","gmail.com");
		mailHostMap.put("lycos.com","lycos.com");
		
		gender = new String[]{ "male", "female"};
	}
	
	public static Map<String, String> getAreaCode(){
		return areaCodeMap;
	}
	
	public static Map<String, String> getHPCode(){
		return hpCodeMap;
	}
	
	public static Map<String, String> getMailHostCode(){
		return mailHostMap;
	}
	
	public static String[] getGender(){
		return gender;
	}
}
