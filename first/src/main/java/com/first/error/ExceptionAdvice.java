package com.first.error;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public String nullException(Model model, Exception e) {
		log.error("Exception : "+e.getMessage());
		
		model.addAttribute("exception", e);
		
		return "error/error";
	}
	
}
