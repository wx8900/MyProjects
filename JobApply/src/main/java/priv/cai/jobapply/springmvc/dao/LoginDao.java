package priv.cai.jobapply.springmvc.dao;

import priv.cai.jobapply.springmvc.model.Users;

public interface LoginDao {

	Users findByUsernameAndPassword(String username, String password);

	int userRegister(Users user);
	
}
