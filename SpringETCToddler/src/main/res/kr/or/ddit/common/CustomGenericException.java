package kr.or.ddit.common;

public class CustomGenericException extends RuntimeException {

	private String errorCode;
	private String errorMSg;
	
	public CustomGenericException(String errorCode, String errorMSg) {
		this.errorCode = errorCode;
		this.errorMSg = errorMSg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMSg() {
		return errorMSg;
	}
	public void setErrorMSg(String errorMSg) {
		this.errorMSg = errorMSg;
	}
}
