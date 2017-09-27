package priv.cai.jobapply.springmvc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import priv.cai.jobapply.constant.Constants;
import priv.cai.jobapply.springmvc.model.Positions;

@Transactional
@Repository("positionsDao")
public class PositionsDaoImpl extends AbstractDao<Integer, Positions> implements PositionsDao {
	
	final static Logger logger = Logger.getLogger(PositionsDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Positions findById(int id) {
		return getByKey(id);
	}

	@Override
	public void savePositions(Positions positions) {
		if(logger.isInfoEnabled()){
			logger.info("This is PositionsDaoImpl>>>savePositions method!!!! : " + positions.toString());
		}
		persist(positions);
	}
	
	@Override
	public void saveList(List<Positions> poList) {
		if(logger.isInfoEnabled()){
			logger.info("This is PositionsDaoImpl>>>saveList method!!!! : " + poList.size());
		}
		super.saveList(poList);
	}

	public void deleteEmployeeBySsn(String id) {
		Query query = getSession().createSQLQuery("delete from Positions where id = :id");
		query.setString("id", id);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Positions> findAllPositions() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.desc("jobinsertdate"));
		return (List<Positions>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Positions> findAllPositions(Integer firstResult, Integer pageSize) {
		Criteria criteria = createEntityCriteria();
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		//criteria.addOrder(Order.desc("jobinsertdate"));
		return criteria.list();
	}
	
	public Positions findPositionsBySsn(String ssn) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssn", ssn));
		return (Positions) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Positions> findPositionsByConditions(String keywords, String loca) {
		// criteria.setFirstResult(20);
		// criteria.setMaxResults(10);
		Criteria criteria = createEntityCriteria();
		Criterion title = Restrictions.like("title", keywords, MatchMode.ANYWHERE);
		Criterion company = Restrictions.like("company", keywords, MatchMode.ANYWHERE);
		Criterion description = Restrictions.like("description", keywords, MatchMode.ANYWHERE);
		Criterion location = Restrictions.like("location", loca, MatchMode.ANYWHERE);
		criteria.add(Restrictions.or(title, company, description));
		criteria.add(location);
		// To get records having salary more than 2000
		//criteria.add(Restrictions.gt("salary", 2000));
		criteria.addOrder(Order.desc("jobinsertdate"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Positions> findPositionsByConditions(String loca) {
		Criteria criteria = createEntityCriteria().add(Restrictions.like("location", loca, MatchMode.ANYWHERE));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Positions> findPositionsByCondition(String columnName, String logicRelationship) {
		Criteria criteria = createEntityCriteria();
		
		if(StringUtils.isNotEmpty(columnName) && StringUtils.isNotEmpty(logicRelationship)) {
			if("isNotNull".equals(logicRelationship)) {
				criteria.add(Restrictions.isNotNull(columnName));
				if("likeit".equals(columnName)) {
					criteria.add(Restrictions.isNull("jobapplydate"));
				} 
			} else if("isNull".equals(logicRelationship)) {
				criteria.add(Restrictions.isNull(columnName));
			}
		}
		
		criteria.addOrder(Order.desc("jobinsertdate"));
		return (List<Positions>) criteria.list();
	}

	@Override
	public void saveOrUpdatePositions(Positions positions) {
		try {
			Session session = getSession();
			session.saveOrUpdate(positions);
			session.flush();
			session.clear();
			logger.info("\n\n update or add success!!! \n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Positions> findByParamValue(String propertyName, String propertyValue) {
		Criteria criteria = createEntityCriteria().add(Restrictions.eq(propertyName, propertyValue));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Positions> findByParamValue(String propertyName, String propertyValue, String matchType) {
		Criteria criteria = createEntityCriteria();
		if (Constants.LIKE.equals(matchType)) {
			criteria.add(Restrictions.like(propertyName, propertyValue, MatchMode.ANYWHERE));
		} else if (Constants.EQUALS.equals(matchType)) {
			criteria.add(Restrictions.eq(propertyName, propertyValue));
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Positions> findByParamMap(Map<String, String> map) {
		if (map == null) {
			return new ArrayList<Positions>(0);
		}
		Criteria criteria = createEntityCriteria();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			criteria.add(Restrictions.like(entry.getKey(), entry.getValue()));
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Positions> findPositionsByConditionsById(String id) {
		Criteria criteria = createEntityCriteria().add(Restrictions.eq("id", id));
		return criteria.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
