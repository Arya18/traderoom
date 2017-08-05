package com.inventory.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.common.service.PreLoginService;
import com.inventory.model.Admin;
import com.inventory.model.Checker;
import com.inventory.model.Maker;
import com.inventory.model.SalesPerson;
import com.inventory.services.AdminServices;
import com.inventory.services.CheckerServices;
import com.inventory.services.MakerServices;
import com.inventory.services.SalesPersonServices;
import com.inventory.utility.CommonUtils;
import com.inventory.utility.SessionUser;

@Controller

public class PreLoginController extends BaseController {
	@Autowired AdminServices adminServices;
	@Autowired PreLoginService preLoginService;
	@Autowired CheckerServices checkerServices;
	@Autowired MakerServices makerServices;
	@Autowired SalesPersonServices salesPersonServices;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String rendorsingUp(){
		return "register";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(Model model,@ModelAttribute Admin admin) throws NoSuchAlgorithmException{
		
		if(admin.getName().equalsIgnoreCase("") || admin.getName().trim().length()==0 ||admin.getEmail().equalsIgnoreCase("") || admin.getEmail().trim().length()==0 || admin.getUsername().equals("") ||
				admin.getUsername().trim().length()==0 || admin.getPassword().equals("") ||admin.getPassword().trim().length()==0){
			setError(model,"Name or Email or Username or password cannot be blank");
			
		}
		
		if(!adminServices.checkUnique(admin.getUsername(),admin.getEmail()))
		{
		   setError(model, "User name or email must be unique");
		   System.out.println("User name or email must be unique");
		   
		}			
		else
		{ 
			admin.setPassword(CommonUtils.getMD5(admin.getPassword()));
			if(adminServices.saveSignUpDetails(admin));
			{
				model.addAttribute("SUCCESS", true);
				
			}	
		}
		
		return "register";
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String forgot(){
		return "forgot";
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgotSubmit(Model model,HttpSession httpSession,@RequestParam(value="email") String email,@RequestParam(value="password") String password,
			@RequestParam(value="role_value") String role_value) throws NoSuchAlgorithmException{
		
		if(email.equals("")||email.trim().length()==0 ||password.equals("")||password.trim().length()==0 || role_value.equals("-1")||
				role_value.trim().length()==0){
			setError(model, "Email or password or role cannot be blank");
			return "forgot";
		}
		 
			String passwordInMD5=CommonUtils.getMD5(password.trim());
		
		if(role_value.equalsIgnoreCase("Admin")){
			
			
			Admin admin=preLoginService.getAdminByEmail(email);
			
			if(admin==null){
				setError(model, "Email corresponding to role Admin does not exist");
				return "forgot";
			}
			
			admin.setPassword(passwordInMD5);
			adminServices.addOrUpdateAdmin(admin);
			model.addAttribute("Reset", true);
		}
		

		
		else if(role_value.equalsIgnoreCase("Checker")){
			Checker checker=preLoginService.getCheckerByEmail(email);
			if(checker==null){
				setError(model, "Email corresponding to role Checker does not exist");
				return "forgot";
			}
			
			checker.setPassword(passwordInMD5);
			checkerServices.addOrUpdateChecker(checker);
			model.addAttribute("Reset", true);
			
		}
		
		else if(role_value.equalsIgnoreCase("Maker")){
			
			Maker maker=preLoginService.getMakerByEmail(email);
			if(maker==null){
				setError(model, "Email corresponding to role Maker does not exist");
				return "forgot";
			}
			maker.setPassword(passwordInMD5);
			makerServices.addOrUpdateMaker(maker);
			model.addAttribute("Reset", true);
			
		}
		else if(role_value.equalsIgnoreCase("Sales Person")){
			
			SalesPerson  salesPerson=preLoginService.getSalesPersonDetailByEmail(email);
			if(salesPerson==null){
				setError(model, "Email corresponding to role Sales Person does not exist");
				return "forgot";
			}
			salesPerson.setPassword(passwordInMD5);
			salesPersonServices.addOrUpdateSalesPerson(salesPerson);
			model.addAttribute("Reset", true);
		}
		
		return "forgot";
		}
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String renderLogin(){
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String checkLoginDetails(Model model,HttpSession httpSession,@RequestParam(value="email") String email,@RequestParam(value="password") String password,
			@RequestParam(value="role_value") String role_value){
		
		if(email.equals("")||email.trim().length()==0 ||password.equals("")||password.trim().length()==0 || role_value.equals("-1")||
				role_value.trim().length()==0){
			setError(model, "Email or password or role cannot be blank");
			return "login";
		}
		 
		try {
			String passwordInMD5=CommonUtils.getMD5(password.trim());
		
		if(role_value.equalsIgnoreCase("Admin")){
			
			System.out.println("enter in admin");
			
			Admin admin=preLoginService.checkAdminDetails(email, passwordInMD5);
			
			if(admin==null){
				System.out.println("nulll vallue");
				setError(model, "invalid combination of UserName or Password");
				return "login";
			}
			
			else{
				SessionUser.createAdmin(admin, httpSession);
				return "redirect:/dashboard";
			}
		}
		
		else if(role_value.equalsIgnoreCase("Checker")){
			System.out.println("Enter in checker");
			Checker checker=preLoginService.checkCheckerDetails(email, passwordInMD5);
			if(checker==null){
				setError(model, "invalid combination of UserName or Password");
				return "login";
			}
			
			else{
				SessionUser.createChecker(checker, httpSession);
				return "redirect:/dashboard";
			}
			
		}
		
		else if(role_value.equalsIgnoreCase("Maker")){
			
			Maker maker=preLoginService.checkMakerDetails(email, passwordInMD5);
			if(maker==null){
				setError(model, "invalid combination of UserName or Password");
				return "login";
			}
			
			else{
				
				if(maker.getStatus()==1){
				SessionUser.createMaker(maker, httpSession);
				return "redirect:/dashboard";
				}
				else{
					setError(model, "Maker is not Active,Please Contact Admin for Activation");
					return "login";
				}
			}
			
		}
		else if(role_value.equalsIgnoreCase("Sales Person")){
			
			SalesPerson  salesPerson=preLoginService.checkSalesPersonDetails(email, passwordInMD5);
			if(salesPerson==null){
				setError(model, "invalid combination of UserName or Password");
				return "login";
			}
			
			else{
				if(salesPerson.getStatus()==1){
				SessionUser.createSalesPerson(salesPerson, httpSession);
				return "redirect:/dashboard";
				}
				else{
					setError(model, "Sales Person is not Active,Please Contact Admin for Activation");
					return "login";
				}
			}
		}
		return "login";
		} 
		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login";
	}
	
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String rendorHomePage(){
		return "register";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(){
		return "redirect:/home";
	}

	
}
