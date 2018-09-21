package com.MVCWithBoot.exceptions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request,Exception ex){
		System.out.println("SQL Exception occured:"+request.getRequestURL());
		return "database_error";
	}
	
	
	@ResponseStatus(code=HttpStatus.NOT_FOUND,reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		System.out.println("IOException Handler executed");
	}
	

}
