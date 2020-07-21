package kr.or.ddit.zipcode.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.vo.ZipCodeVO;
import kr.or.ddit.zipcode.dao.IZipCodeDao;

@Service
public class IZipCodeServiceImpl implements IZipCodeService {
	@Autowired
	private IZipCodeDao dao;
	
	public List<ZipCodeVO> getZipCodeList(Map<String, String> params) {
		List<ZipCodeVO> zipcodeList = null;
		try{
			zipcodeList = dao.getZipCodeList(params);
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		return zipcodeList;
	}

}
