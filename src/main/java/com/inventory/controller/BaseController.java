package com.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class BaseController {
	
	public static final String ERORR_KEY="hasError";
	public static final String MESSAGE_KEY="message";

	public void setError(Model model, List<String> errorList){
		
		if(model == null || errorList == null || errorList.isEmpty()){
			throw new IllegalArgumentException("NULL or EMPTY value not allowed.");
		}
			
		model.addAttribute(ERORR_KEY, true);
		StringBuilder error = new StringBuilder(errorList.size()*25);
		
		for(String er : errorList){
			error.append(er).append(" ");
		}
			
		model.addAttribute(MESSAGE_KEY, error);
		
	}
	
	public void setError(Model model, String message){
		
		if(model == null || message == null || message.trim().length() == 0 ){
			throw new IllegalArgumentException("NULL or EMPTY value not allowed.");
		}
			
		model.addAttribute(ERORR_KEY, true);
		model.addAttribute(MESSAGE_KEY, message);
		
	}
	
	public void setUnknownError(Model model){
		
		if(model == null){
			throw new IllegalArgumentException("NULL or EMPTY value not allowed.");
		}
			
		model.addAttribute(ERORR_KEY, true);
		model.addAttribute(MESSAGE_KEY, "Some unknown error occured. Please try later.");
		
	}
	 	
	public static String fetchWebDomain(HttpServletRequest request){
		StringBuilder requestURL = new StringBuilder(25); 
		requestURL.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort());
		return requestURL.toString();
	}

}
