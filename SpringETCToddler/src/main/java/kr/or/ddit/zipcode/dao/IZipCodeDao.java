package kr.or.ddit.zipcode.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.ZipCodeVO;

public interface IZipCodeDao {
	public List<ZipCodeVO> getZipCodeList(Map<String, String> params) throws SQLException;
}
