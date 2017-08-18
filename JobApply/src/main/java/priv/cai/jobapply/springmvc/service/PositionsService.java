package priv.cai.jobapply.springmvc.service;

import java.util.List;

import priv.cai.jobapply.springmvc.model.Positions;

public interface PositionsService {

	Positions findById(int id);
	
	void savePositions(Positions employee);
	
	void saveList(List<Positions> poList);
	
	void deletePositionsBySsn(String ssn);

	List<Positions> findAllPositions(Integer firstResult, Integer pageSize); 
	
	List<Positions> findPositionsByConditions(String keywords, String location);

	List<Positions> findPositionsByConditions(String location);
	
	public List<Positions> findPositionsByCondition(String columnName, String logicRelationship);

	boolean isPositionssnUnique(Integer id, String ssn);
	
	void saveOruUpdatePositions(Positions employee);

	List<Positions> findByParamValue(String propertyName, String propertyValue, String matchType);
	
	List<Positions> findByParamValue(String propertyName, String propertyValue);

	List<Positions> findPositionsByConditionsById(String id);

	//List<Positions> findAllAppliedPositions(String userId);
	
}
