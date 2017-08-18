package priv.cai.jobapply.springmvc.service;

import priv.cai.jobapply.springmvc.model.Users;

public interface LoginService {

	Users findByUsernameAndPassword(String username, String password);

	int userRegister(Users user);
	
}
