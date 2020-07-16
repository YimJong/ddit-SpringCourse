package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.vo.FreeBoardVO;

public interface IFreeBoardService {
	
	public List<FreeBoardVO> freeboardList(Map<String, String> params) throws Exception;
	
	public String insertFreeboard(FreeBoardVO freeboardInfo,
									FileItem[] items) throws Exception;

	public String insertFreeboardReply(FreeBoardVO freeboardInfo) throws Exception;

	public void updateFreeboard(FreeBoardVO freeboardInfo) throws Exception;
	
	public FreeBoardVO freeboardInfo(Map<String, String> params) throws Exception;
	
	public void deleteFreeboard(Map<String, String> params) throws Exception;
	
	public String totalCount(Map<String, String> params) throws Exception;
	
	public String insertFreeboardTemp(FreeBoardVO freeboardInfo) throws Exception;
}
