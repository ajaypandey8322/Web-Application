package com.mySpringboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/* ****NOTES****
 * This is to create login page which is an conrtoller through jsp /html
 * 
*/
@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	

	//http://localhost:8080/login?name=ajay
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model)
	{
		model.put("name", getLoggedinUsername());
		return "welcome";
	}	

	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 return authentication.getName();
		
	}
}
