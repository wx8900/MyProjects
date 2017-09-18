package priv.cai.jobapply.springmvc.controller;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import priv.cai.jobapply.springmvc.model.PositionJsonObject;
import priv.cai.jobapply.springmvc.model.Positions;
import priv.cai.jobapply.springmvc.service.PositionsService;
 
@Controller
@Scope("prototype")
public class PositionsPageController {
	
	@Autowired
	PositionsService service;
	
	@RequestMapping(value = "/listAllPages.web", method = 
		    RequestMethod.GET)
	    public String printWelcome(@ModelAttribute("positions") Positions positions, BindingResult result,ModelMap model, HttpServletRequest 
		    request, HttpServletResponse response) {
		
	    	return "positions";

	    }

	    @RequestMapping(value = "/positionsPaginationDataTables.web", method = RequestMethod.GET, produces = "application/json")
	    public @ResponseBody String springPaginationDataTables(HttpServletRequest  request) throws IOException {
	    	
	    	
	    	/*int totalCount=Integer.valueOf(queryCount.uniqueResult().toString());
	    	Pager pager=new Pager<T>(totalCount, pageNumber,limit);
	    	queryList.setFirstResult((pager.getPageNumber()-1)*limit); //容错处理
	    	queryList.setMaxResults(limit);
	    	pager.setList(queryList.list());
	    	return pager;*/
			
	    	//Fetch Page display length
	    	Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
	    	
	    	//Fetch the page number from client
	    	Integer pageNumber = 0;
	    	if (null != request.getParameter("iDisplayStart"))
	    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/pageDisplayLength)+1;		
	    	
	    	//Fetch search parameter
	    	String searchParameter = request.getParameter("sSearch");
	    	
	    	System.out.print("====111===="+Integer.valueOf(request.getParameter("iDisplayStart")));
	    	System.out.print("====222===="+pageDisplayLength);
	    	
	    	//Create page list data
	    	//List<Person> personsList = createPaginationData(pageDisplayLength);
	    	List<Positions> positionsList = service.findAllPositions(Integer.valueOf(request.getParameter("iDisplayStart")), pageDisplayLength);
			
			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			
	    	Collections.shuffle(positionsList);
			
			//Search functionality: Returns filtered list based on search parameter
			positionsList = getListBasedOnSearchParameter(searchParameter,positionsList);
			
			
			PositionJsonObject positionJsonObject = new PositionJsonObject();
			//Set Total display record
			positionJsonObject.setiTotalDisplayRecords(500);
			//Set Total record
			positionJsonObject.setiTotalRecords(500);
			positionJsonObject.setAaData(positionsList);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json2 = gson.toJson(positionJsonObject);
		
			return json2;
	    }

		private List<Positions> getListBasedOnSearchParameter(String searchParameter,List<Positions> personsList) {
			
			if (null != searchParameter && !searchParameter.equals("")) {
				List<Positions> personsListForSearch = new ArrayList<Positions>();
				searchParameter = searchParameter.toUpperCase();
				for (Positions position : personsList) {
					if (position.getTitle().toUpperCase().indexOf(searchParameter)!= -1 || position.getCompany().toUpperCase().indexOf(searchParameter)!= -1
							|| position.getLocation().toUpperCase().indexOf(searchParameter)!= -1 || position.getDescription().toUpperCase().indexOf(searchParameter)!= -1
							|| position.getSalary().toUpperCase().indexOf(searchParameter)!= -1 || position.getJobapplydate().toUpperCase().indexOf(searchParameter)!= -1) {
						personsListForSearch.add(position);					
					}
					
				}
				personsList = personsListForSearch;
				personsListForSearch = null;
			}
			return personsList;
		}
		
		
		
		
		
		
		
		
		
		
}