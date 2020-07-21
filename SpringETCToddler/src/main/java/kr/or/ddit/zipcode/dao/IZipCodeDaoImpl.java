package kr.or.ddit.zipcode.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.vo.ZipCodeVO;

@Repository
public class IZipCodeDaoImpl implements IZipCodeDao {
	@Autowired
	private SqlMapClient client;
	
	public List<ZipCodeVO> getZipCodeList(Map<String, String> params)
			throws SQLException {
		return client.queryForList("zipcode.zipcodeList", params);
	}

}
