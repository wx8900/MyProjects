package priv.cai.jobapply.springmvc.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import priv.cai.jobapply.springmvc.model.Users;

@Repository("loginDao")
public class LoginDaoImpl extends AbstractDao<Integer, Users> implements LoginDao {

	@Override
	public Users findByUsernameAndPassword(String username, String password) {
		Criteria criteria = createEntityCriteria();
		Criterion usernameInput = Restrictions.eq("username", username);
		Criterion passwordInput = Restrictions.eq("pwd", password);
		criteria.add(usernameInput);
		criteria.add(passwordInput);
		Users user = (Users) criteria.uniqueResult();

		return (user == null ? null : user);
	}

	@Override
	public int userRegister(Users user) {
		try {
			persist(user);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

}
