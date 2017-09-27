package priv.cai.jobapply.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.cai.jobapply.springmvc.dao.PositionsDao;
import priv.cai.jobapply.springmvc.model.Positions;

@Service("positionsService")
@Transactional
public class PositionsServiceImpl implements PositionsService {

	@Autowired
	private PositionsDao dao;
	
	public Positions findById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void savePositions(Positions positions) {
		dao.savePositions(positions);
	}
	
	@Override
	public void saveList(List<Positions> poList) {
		System.out.print("This is PositionsServiceImpl>>>saveList method!!!!");
		dao.saveList(poList);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updatePositions(Positions positions) {
		dao.savePositions(positions);
	}

	@Override
	public void deletePositionsBySsn(String ssn) {
		dao.deleteEmployeeBySsn(ssn);
	}
	
	@Override
	public List<Positions> findAllPositions() {
		return dao.findAllPositions();
	}
	
	@Override
	public List<Positions> findAllPositions(Integer firstResult, Integer pageSize) {
		return dao.findAllPositions(firstResult, pageSize);
	}

	@Override
	public boolean isPositionssnUnique(Integer id, String ssn) {
		Positions employee = null;
				//findPositionsBySsn(ssn);
		return ( employee == null || ((id != null) && (employee.getId() == id)));
	}
	
	@Override
	public void saveOruUpdatePositions(Positions positions) {
		dao.saveOrUpdatePositions(positions);
	}

	@Override
	public List<Positions> findPositionsByConditions(String what, String location) {
		return dao.findPositionsByConditions(what, location);
	}

	@Override
	public List<Positions> findPositionsByCondition(String columnName, String logicRelationship) {
		return dao.findPositionsByCondition(columnName, logicRelationship);
	}
	
	@Override
	public List<Positions> findPositionsByConditions(String location) {
		return dao.findPositionsByConditions(location);
	}

	@Override
	public List<Positions> findByParamValue(String propertyName, String propertyValue, String matchType) {
		return dao.findByParamValue(propertyName, propertyValue, matchType);
	}

	@Override
	public List<Positions> findByParamValue(String propertyName, String propertyValue) {
		return dao.findByParamValue(propertyName, propertyValue);
	}

	@Override
	public List<Positions> findPositionsByConditionsById(String id) {
		return dao.findPositionsByConditionsById(id);
	}

}
