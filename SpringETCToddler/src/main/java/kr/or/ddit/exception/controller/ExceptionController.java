package kr.or.ddit.exception.controller;

import kr.or.ddit.common.CustomGenericException;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(CustomGenericException.class)
	private String exceptionHandling(CustomGenericException ex){
		System.out.println("errorCode : " + ex.getErrorCode());
		System.out.println("errorMsg : " + ex.getErrorMSg());
		return "error/error500";
	}
}
