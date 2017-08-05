package com.inventory.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.inventory.controller.BaseController;
import com.inventory.utility.SessionUser;

@Component("loginCheckInterceptor")
public class LoginInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession httpSession = request.getSession();
		
		SessionUser user = SessionUser.getHttpSessionUser(httpSession);
		String uri=request.getRequestURI();
		System.out.println("uri>>>"+uri);
	/*	if(!uri.endsWith("login") ||!uri.endsWith("/")||!uri.endsWith("signup")||
		!uri.endsWith("home")||!uri.endsWith("signup"))*/
		if(uri.contains("dashboard")){
		if(user==null){
			response.sendRedirect(BaseController.fetchWebDomain(request)+"?session=exp");
			return false;
		}
		}
		
		else{
			if(user!=null){
			response.sendRedirect(BaseController.fetchWebDomain(request)+"/dashboard");
			return false;
			}
		}
		
		return true;
	}
}
