package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.IDPictureVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SearchConditionVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class IMemberDaoImpl implements IMemberDao {
	@Autowired
	private SqlMapClient client;

	public MemberVO getMemberInfo(Map<String, String> params)
			throws SQLException {
		return (MemberVO) client.queryForObject("member.memberInfo", params);
	}

	public List<MemberVO> getMemberList(SearchConditionVO conditionVO) throws SQLException {
		return client.queryForList("member.memberList", conditionVO);
	}

	public String insertIdPictureInfo(IDPictureVO pictureInfo) throws SQLException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("lprod_id", "12");
		params.put("lprod_nm", "기타2");
		params.put("lprod_gu", "etc2");
		
		String rtnValue = "";
		client.insert("member.insertLprodInfo", params);
		
		pictureInfo.setFile_type(null);
		rtnValue = (String) client.insert("member.insertIdPictureInfo", pictureInfo);
		
		return rtnValue;
	}

	public String insertMemberInfo(MemberVO memberInfo) throws SQLException {
		String rtnValue = (String) client.insert("member.insertMemberInfo", memberInfo);
//		throw new SQLException("강제 발생");
		return rtnValue;
	}

	@Override
	public void updateMemberInfo(MemberVO memberInfo) throws SQLException {
		client.update("member.updateMemberInfo", memberInfo);
	}

	public void updateIDPicture(MemberVO memberInfo) throws SQLException{
		client.update("member.updateIDPicture", memberInfo);
	}
}
