package kr.or.ddit.zipcode.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.ZipCodeVO;

public interface IZipCodeService {
	public List<ZipCodeVO> getZipCodeList(Map<String, String> params);
}
