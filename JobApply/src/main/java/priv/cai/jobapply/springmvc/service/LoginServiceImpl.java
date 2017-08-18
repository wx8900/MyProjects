package priv.cai.jobapply.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.cai.jobapply.springmvc.dao.LoginDao;
import priv.cai.jobapply.springmvc.model.Users;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao dao;

	@Override
	public Users findByUsernameAndPassword(String username, String password) {
		return dao.findByUsernameAndPassword(username, password);
	}

	@Override
	public int userRegister(Users user) {
		return dao.userRegister(user);
	}
	
}
	
	
	
