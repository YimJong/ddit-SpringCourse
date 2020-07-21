package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SearchConditionVO;

public interface IMemberService {
	public MemberVO getMemberInfo(Map<String, String> params) throws SQLException;
	public List<MemberVO> getMemberList(SearchConditionVO conditionVO) throws SQLException;
	public String insertIdPictureInfo(MultipartHttpServletRequest request) throws SQLException;
	public String insertMemberInfo(MemberVO memberInfo) throws SQLException;
	public void updateMemberInfo(MemberVO memberInfo) throws SQLException;
}
