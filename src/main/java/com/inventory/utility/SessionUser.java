package com.inventory.utility;

import javax.servlet.http.HttpSession;

import com.inventory.model.Admin;
import com.inventory.model.Checker;
import com.inventory.model.Maker;
import com.inventory.model.SalesPerson;

public class SessionUser {
	
	private static final String HttpSessionUser = "HTTP_SESSION_USER";
	private long id;
	private String name;
	private String email;
	private String userName;
	private String address;
	private String role;
	
	public static SessionUser createAdmin(Admin admin, HttpSession httpSession) {
		
		SessionUser user = new SessionUser();
		user.id=admin.getId();
		user.name = admin.getName();
		user.email = admin.getEmail();
		user.userName = admin.getUsername();
		user.address=admin.getAddress();
		user.role = admin.getUserRole();
		
		httpSession.setAttribute(HttpSessionUser, user);
		
		return user;
	}
	
	public static SessionUser createMaker(Maker maker, HttpSession httpSession) {
		
		SessionUser user = new SessionUser();
		user.id=maker.getId();
		user.name = maker.getName();
		user.email = maker.getEmail();
		user.userName = maker.getUsername();
		user.address=maker.getAddress();
		user.role = maker.getUserRole();
		
		httpSession.setAttribute(HttpSessionUser, user);
		
		return user;
	}

public static SessionUser createChecker(Checker checker, HttpSession httpSession) {
	
	SessionUser user = new SessionUser();
	user.id=checker.getId();
	user.name = checker.getName();
	user.email = checker.getEmail();
	user.userName = checker.getUsername();
	user.address=checker.getAddress();
	user.role = checker.getUserRole();
	
	httpSession.setAttribute(HttpSessionUser, user);
	
	return user;
}

public static SessionUser createSalesPerson(SalesPerson salesPerson, HttpSession httpSession) {
	
	SessionUser user = new SessionUser();
	user.id=salesPerson.getId();
	user.name = salesPerson.getName();
	user.email = salesPerson.getEmail();
	user.userName = salesPerson.getUsername();
	user.address=salesPerson.getAddress();
	user.role = salesPerson.getUserRole();
	
	httpSession.setAttribute(HttpSessionUser, user);
	
	return user;
}

	public static SessionUser getHttpSessionUser(HttpSession httpSession) {
	return (SessionUser) httpSession.getAttribute(HttpSessionUser);
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getRole() {
		return role;
	}

	public long getId() {
		return id;
	}
		

}
