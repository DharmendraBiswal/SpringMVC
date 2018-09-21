package com.MVCWithBoot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Üser Not Found")
public class UserNotFoundException extends Exception{
	
	public UserNotFoundException(int id){
		super("UserNotFound with Id "+id);
	}
	

}
