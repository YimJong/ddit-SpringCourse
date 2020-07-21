package kr.or.ddit.member.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.engine.transaction.spi.IsolationDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ibatis.sqlmap.engine.transaction.IsolationLevel;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.vo.IDPictureVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.SearchConditionVO;

@Service
public class IMemberServiceImpl implements IMemberService {
	@Autowired
	private IMemberDao dao;
	
//	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor=Exception.class)
	public MemberVO getMemberInfo(Map<String, String> params) throws SQLException{
		MemberVO memberInfo = null;
		memberInfo = dao.getMemberInfo(params);
		return memberInfo;
	}

//	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor=Exception.class)
	public List<MemberVO> getMemberList(SearchConditionVO conditionVO) throws SQLException{
		List<MemberVO> memberList = null;
		memberList = dao.getMemberList(conditionVO);
		return memberList;
	}

//	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={SQLException.class})
	public String insertIdPictureInfo(MultipartHttpServletRequest request) throws SQLException{
		// 저장 위치
		String uploadPath = request.getSession().getServletContext().getRealPath("upload");
		Iterator<String> fieldNames = request.getFileNames();
		String originalFileName = "";
		MultipartFile file = null;
		while (fieldNames.hasNext()) {
			String fieldName = (String) fieldNames.next();
			file = request.getFile(fieldName );
			
			originalFileName = file.getOriginalFilename();
			File destfile = new File(uploadPath, originalFileName);
			try {
				file.transferTo(destfile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		IDPictureVO pictureInfo = new IDPictureVO();
		pictureInfo.setFile_name(originalFileName);
		pictureInfo.setFile_type(file.getContentType());
		
		String idPicture_seq = "";

		idPicture_seq =  this.dao.insertIdPictureInfo(pictureInfo);
		
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put("fileName", originalFileName);
		jsonMap.put("file_seq", idPicture_seq);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(jsonMap);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonData;
	}

	// SQLException 발생시 롤백되도록 반드시 선언.
	@Transactional(rollbackFor={SQLException.class})
	public String insertMemberInfo(MemberVO memberInfo) throws SQLException{
		String idPicture = null;
		idPicture = this.dao.insertMemberInfo(memberInfo);
//		this.dao.updateIDPicture(memberInfo);
		return idPicture;
	}

	@Transactional(rollbackFor={SQLException.class})
	public void updateMemberInfo(MemberVO memberInfo) throws SQLException{
		this.dao.updateMemberInfo(memberInfo);
	}
}
