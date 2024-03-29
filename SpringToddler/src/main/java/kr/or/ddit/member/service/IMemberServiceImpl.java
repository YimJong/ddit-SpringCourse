package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.IMemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

// 설정파일 : <bean name="iMemberServiceImpl" class="kr.or.ddit.member.service.IMemberServiceImpl"
//					  c:dao-ref="iMemberDao"></bean> 자동 등록
@Service
public class IMemberServiceImpl implements IMemberService{
	@Autowired // DI
	private IMemberDao dao;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public MemberVO memberInfo(Map<String, String> parms) throws Exception {
		return dao.memberInfo(parms);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public List<MemberVO> memberList(Map<String, String> params) throws Exception {
			return dao.memberList(params);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void deleteMemberInfo(Map<String, String> params) throws Exception{
			dao.deleteMemberInfo(params);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void updateMemberInfo(MemberVO memberInfo) throws Exception{
			dao.updateMemberInfo(memberInfo);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void insertMember(MemberVO memberInfo) throws Exception {
			dao.insertMember(memberInfo);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public String totalCount(Map<String, String> params) throws Exception {
			return dao.totalCount(params);
	}
}
