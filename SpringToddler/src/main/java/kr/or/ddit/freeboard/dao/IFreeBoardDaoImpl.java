package kr.or.ddit.freeboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.vo.FreeBoardVO;

@Repository("freeboardDao")
public class IFreeBoardDaoImpl implements IFreeBoardDao{
	@Autowired
	private SqlMapClient client;
	
	@Override
	public List<FreeBoardVO> freeboardList(Map<String, String> params) throws Exception {
		return client.queryForList("freeboard.freeboardList", params);
	}

	@Override
	public String insertFreeboard(FreeBoardVO freeboardInfo) throws Exception {
		return (String) client.insert("freeboard.insertFreeboard", freeboardInfo);
	}
	
	@Override
	public String insertFreeboardReply(FreeBoardVO freeboardInfo) throws Exception {
		// freeboardInfo : 댓글 정보(bo_title, bo_nickname, bo_pwd, bo_mail, bo_content, bo_writer, bo_ip)
		//                 부모게시글 정보(bo_group, bo_seq, bo_depth)
		String bo_no = "";
		try {
			client.startTransaction();
			
			String bo_seq;
			if("0".intern() == freeboardInfo.getBo_seq().intern()) {
				bo_seq = (String) client.queryForObject("freeboard.incrementSeq", freeboardInfo); // 마지막 seq 값 산출
			} else {
				client.update("freeboard.updateSeq", freeboardInfo); // 부모 댓글이 아닌 나머지 댓글 seq 1씩 증가
				bo_seq = String.valueOf(Integer.parseInt(freeboardInfo.getBo_seq()) + 1);
			}
			
			freeboardInfo.setBo_seq(bo_seq); // 자신의 seq 셋팅
			
			String bo_depth = String.valueOf(Integer.parseInt(freeboardInfo.getBo_depth()) + 1); // 부모의 depth에서 +1 한 값
			freeboardInfo.setBo_depth(bo_depth);
			
			bo_no = (String) client.insert("freeboard.insertFreeboardReply", freeboardInfo);
			
			client.commitTransaction();
		} finally {
			client.endTransaction();
		}
		
		return bo_no;
	}

	@Override
	public FreeBoardVO freeboardInfo(Map<String, String> params)
			throws Exception {
		return (FreeBoardVO) client.queryForObject("freeboard.freeboardInfo", params);
	}

	@Override
	public void deleteFreeboard(Map<String, String> params) throws Exception {
		client.update("freeboard.deleteFreeboard", params);
	}

	@Override
	public void updateFreeboard(FreeBoardVO freeboardInfo) throws Exception {
		client.update("freeboard.updateFreeboard", freeboardInfo);
	}

	@Override
	public String totalCount(Map<String, String> params) throws Exception {
		return (String) client.queryForObject("freeboard.totalCount", params);
	}

	@Override
	public String insertFreeboardTemp(FreeBoardVO freeboardInfo)
			throws Exception {
		return (String) client.insert("freeboard.insertFreeboardTemp", freeboardInfo);
	}

	
	
}
