package priv.cai.jobapply.springmvc.dao;

import java.util.List;
import java.util.Map;

import priv.cai.jobapply.springmvc.model.Positions;

public interface PositionsDao {

	Positions findById(int id);

	void savePositions(Positions positions);
	
	void saveList(List<Positions> positions);
	
	void deleteEmployeeBySsn(String id);
	
	List<Positions> findAllPositions(Integer firstResult, Integer pageSize);

	Positions findPositionsBySsn(String ssn);

	List<Positions> findPositionsByConditions(String keywords, String location);

	List<Positions> findPositionsByConditions(String location);
	
	public List<Positions> findPositionsByCondition(String columnName, String logicRelationship);
	
	void saveOrUpdatePositions(Positions positions);
	
	List<Positions> findByParamValue(String propertyName, String propertyValue, String matchType);
	
	List<Positions> findByParamValue(String propertyName, String propertyValue);
	
	List<Positions> findByParamMap(Map<String, String> map);

	List<Positions> findPositionsByConditionsById(String id);

}
