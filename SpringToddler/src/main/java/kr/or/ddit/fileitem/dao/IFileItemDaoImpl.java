package kr.or.ddit.fileitem.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.FileItemVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class IFileItemDaoImpl implements IFileItemDao{
	@Autowired
	private SqlMapClient client;
	
	@Override
	public void insertFileItem(List<FileItemVO> fileitemList) throws Exception {
		try {
			// iBatis 트랜잭션
			// 	Commit : startTransation() => 쿼리 질의(전체 성공)
			// 			=> commitTransaction()
			//          => client.endTransaction();
			//  Rollback : startTransaction() => 쿼리 질의(일부 성공 후 에러)
			//          => endTransaction();
			for(FileItemVO fileItemInfo : fileitemList) {
				client.insert("fileitem.insertFileItem", fileItemInfo);
			}
			
		} finally {
		}
	}

	@Override
	public FileItemVO fileitemInfo(Map<String, String> params) throws Exception {
		return (FileItemVO) client.queryForObject("fileitem.fileitemInfo", params);
	}
}
