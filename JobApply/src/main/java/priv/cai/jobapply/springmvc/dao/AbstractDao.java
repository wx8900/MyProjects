package priv.cai.jobapply.springmvc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import priv.cai.jobapply.springmvc.model.Positions;
import priv.cai.jobapply.utils.HibernateUtil;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	private Transaction tx;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
	
	public void saveList(List<Positions> list) {
		System.out.print("This is AbstractDao>>>saveList method!!!!");
		System.out.println("saving Positions instance!!!");
		Session session = getSession();
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			if (list != null) {
				int j = list.size();
				for (int i = 0; i < j; i++) {
					if (list.get(i) != null) {
						session.persist(list.get(i));
					}
				}
				transaction.commit();
				session.flush();
				System.out.println("save List<Positions> successful !!!!!!");
			}
		} catch (RuntimeException re) {
			transaction.rollback();
			re.printStackTrace();
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (Exception e) {
					session = null;
				}
			}
		}
	}
	
	/*
	 * ======================================================
	 */
	/*public <T> T save(final T o) {
		return (T) getSession().save(o);
	}*/
	
	public <T> T merge(final T o)   {
    	return (T) getSession().merge(o);
    }

	public <T> void saveOrUpdate(final T o) {
    	getSession().saveOrUpdate(o);
    }
	
	public <T> T get(final Class<T> type, final Long id) {
		return (T) getSession().get(type, id);
	}
  
	@SuppressWarnings("unchecked")  
    public T findById(PK id, boolean lock) {  
        T entity;  
        if (lock)  
            entity = (T) getSession().load(persistentClass, id, LockMode.UPGRADE);  
        else  
            entity = (T) getSession().load(persistentClass, id);  
  
        return entity;  
    } 

	@SuppressWarnings("unchecked")  
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {  
        Criteria crit = getSession().createCriteria(persistentClass);  
        Example example =  Example.create(exampleInstance);  
        for (String exclude : excludeProperty) {  
            example.excludeProperty(exclude);  
        }  
        crit.add(example);  
        return crit.list();  
    }  

    public <T> List<T> getAll(final Class<T> type) {
      final Criteria crit = getSession().createCriteria(type);
      return crit.list();
    }
    
    public void flush() {  
        getSession().flush();  
    }  
  
    public void clear() {  
        getSession().clear();  
    }
    
    protected void handleException(HibernateException e) throws DataAccessLayerException {
    	//persistentClass.rollback(tx);
        throw new DataAccessLayerException(e);
    }
    
    /** 
     * Use this inside subclasses as a convenience method. 
     */  
    @SuppressWarnings("unchecked")  
    protected List<T> findByCriteria(Criterion... criterion) {  
        Criteria crit = getSession().createCriteria(persistentClass);  
        for (Criterion c : criterion) {  
            crit.add(c);  
        }  
        return crit.list();  
   }

}
