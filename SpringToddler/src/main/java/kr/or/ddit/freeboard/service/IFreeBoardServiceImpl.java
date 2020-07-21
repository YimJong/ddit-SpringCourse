package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.fileitem.dao.IFileItemDaoImpl;
import kr.or.ddit.freeboard.dao.IFreeBoardDao;
import kr.or.ddit.freeboard.dao.IFreeBoardDaoImpl;
import kr.or.ddit.utils.AttachFileMapper;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeBoardVO;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service("freeboardService") // 빈 등록 시 이름이 바뀜
public class IFreeBoardServiceImpl implements IFreeBoardService{
	@Autowired
	private IFreeBoardDao dao;
	@Autowired
	private IFileItemDao fileitemDao;
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public List<FreeBoardVO> freeboardList(Map<String, String> params) throws Exception {
		return dao.freeboardList(params);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public String insertFreeboard(FreeBoardVO freeboardInfo, MultipartFile[] items) throws Exception {
		String bo_no = dao.insertFreeboard(freeboardInfo);
		
		List<FileItemVO> fileItemList = AttachFileMapper.mapper(items, bo_no);
			 
		fileitemDao.insertFileItem(fileItemList);
		return bo_no;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public String insertFreeboardReply(FreeBoardVO freeboardInfo) throws Exception {
		return dao.insertFreeboardReply(freeboardInfo);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public FreeBoardVO freeboardInfo(Map<String, String> params) throws Exception {
		return dao.freeboardInfo(params);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void deleteFreeboard(Map<String, String> params) throws Exception {
		dao.deleteFreeboard(params);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void updateFreeboard(FreeBoardVO freeboardInfo) throws Exception {
		dao.updateFreeboard(freeboardInfo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public String totalCount(Map<String, String> params) throws Exception {
		return dao.totalCount(params);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public String insertFreeboardTemp(FreeBoardVO freeboardInfo) throws Exception{
		return dao.insertFreeboardTemp(freeboardInfo);
	}
}
