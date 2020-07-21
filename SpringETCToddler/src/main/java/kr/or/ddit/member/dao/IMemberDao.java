package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.IDPictureVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SearchConditionVO;

public interface IMemberDao {
	public MemberVO getMemberInfo(Map<String, String> params) throws SQLException;
	public List<MemberVO> getMemberList(SearchConditionVO conditionVO) throws SQLException;
	public String insertIdPictureInfo(IDPictureVO pictureInfo) throws SQLException;
	public String insertMemberInfo(MemberVO memberInfo) throws SQLException;
	public void updateIDPicture(MemberVO memberInfo) throws SQLException;
	public void updateMemberInfo(MemberVO memberInfo) throws SQLException;
}
