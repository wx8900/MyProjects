package priv.cai.jobapply.springmvc.controller;
 
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import priv.cai.jobapply.springmvc.model.Users;
import priv.cai.jobapply.springmvc.service.LoginService;

@Controller
@SessionAttributes("userId")
public class LoginController {
	
	final static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/")
    public String hello(Model model) {
        
        return "login";  //test
        //return "index"; //test HTML5 pages
    }
	
	@RequestMapping("/goToRegisterPage")
	public String goToRegisterPage(Model model) {

		return "register";
	}
	
	@RequestMapping("/register")
	public String register(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("state") String state, @RequestParam("city") String city,
			@RequestParam("street") String street, @RequestParam("zipcode") String zipcode, Model model) {
		
		if(username == null || ("").equals(username.trim())) {
			return "";
		}
		if(password == null || ("").equals(password.trim())) {
			return "";
		}

		Users user = new Users();
		user.setUsername(username.trim());
		user.setPassword(password.trim());
		user.setEmail(email);
		user.setState(state);
		user.setCity(city);
		user.setStreet(street);
		user.setZipcode(zipcode);
		user.setValid("1");

		try {
			loginService.userRegister(user);
			logger.info( "userRegister - call register method ..." );
		} catch (Exception e) {
			logger.error("This is error", e);
		}

		return "login";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(@RequestParam("u") String username,
								@RequestParam("p") String password, Model model) {
		
		Users user = null;
		String targetpage = "";
		if(username == null || ("").equals(username.trim())) {
			return "";
		}
		if(password == null || ("").equals(password.trim())) {
			return "";
		}
		
		try {
			user = loginService.findByUsernameAndPassword(username.trim(), password.trim());
		} catch (Exception e) {
			logger.error("This is error : " + e);
		}
		
		if(user == null || (user != null && user.getId() == 0)){
			targetpage = "login";
			model.addAttribute("error", "Your username or password is error!");
		} else {
			targetpage = "searchjobs";
			model.addAttribute("greeting", "Hello , "+username);
			model.addAttribute("username", username);
			model.addAttribute("userId", user.getId());
		}
		
		return targetpage;
	}
 
}