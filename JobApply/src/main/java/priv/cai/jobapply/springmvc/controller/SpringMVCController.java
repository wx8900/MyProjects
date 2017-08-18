package priv.cai.jobapply.springmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import priv.cai.jobapply.springmvc.model.Person;
import priv.cai.jobapply.springmvc.model.PersonJsonObject;

@Controller
public class SpringMVCController {

    @RequestMapping(value = "/helloWorld.web", method = 
	    RequestMethod.GET)
    public String printWelcome(@ModelAttribute("person") Person person, BindingResult result,ModelMap model, HttpServletRequest 
	    request, HttpServletResponse response) {
	
    	return "helloWorld";

    }

    @RequestMapping(value = "/springPaginationDataTables.web", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String springPaginationDataTables(HttpServletRequest  request) throws IOException {
		
    	//Fetch the page number from client
    	Integer pageNumber = 0;
    	if (null != request.getParameter("iDisplayStart"))
    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;		
    	
    	//Fetch search parameter
    	String searchParameter = request.getParameter("sSearch");
    	
    	//Fetch Page display length
    	Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
    	
    	//Create page list data
    	List<Person> personsList = createPaginationData(pageDisplayLength);
		
		//Here is server side pagination logic. Based on the page number you could make call 
		//to the data base create new list and send back to the client. For demo I am shuffling 
		//the same list to show data randomly
		if (pageNumber == 1) {
			Collections.shuffle(personsList);
		}else if (pageNumber == 2) {
			Collections.shuffle(personsList);
		}else {
			Collections.shuffle(personsList);
		}
		
		//Search functionality: Returns filtered list based on search parameter
		personsList = getListBasedOnSearchParameter(searchParameter,personsList);
		
		
		PersonJsonObject personJsonObject = new PersonJsonObject();
		//Set Total display record
		personJsonObject.setiTotalDisplayRecords(500);
		//Set Total record
		personJsonObject.setiTotalRecords(500);
		personJsonObject.setAaData(personsList);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(personJsonObject);
	
		return json2;
    }

	private List<Person> getListBasedOnSearchParameter(String searchParameter,List<Person> personsList) {
		
		if (null != searchParameter && !searchParameter.equals("")) {
			List<Person> personsListForSearch = new ArrayList<Person>();
			searchParameter = searchParameter.toUpperCase();
			for (Person person : personsList) {
				if (person.getName().toUpperCase().indexOf(searchParameter)!= -1 || person.getOffice().toUpperCase().indexOf(searchParameter)!= -1
						|| person.getPhone().toUpperCase().indexOf(searchParameter)!= -1 || person.getPosition().toUpperCase().indexOf(searchParameter)!= -1
						|| person.getSalary().toUpperCase().indexOf(searchParameter)!= -1 || person.getStart_date().toUpperCase().indexOf(searchParameter)!= -1) {
					personsListForSearch.add(person);					
				}
				
			}
			personsList = personsListForSearch;
			personsListForSearch = null;
		}
		return personsList;
	}

	private List<Person> createPaginationData(Integer pageDisplayLength) {
		List<Person> personsList = new ArrayList<Person>();
		for (int i = 0; i < 1; i++) {
		    Person person2 = new Person();
	            person2.setName("John Landy");
	            person2.setPosition("System Architect");
	            person2.setSalary("$320,800");
	            person2.setOffice("NY");
	            person2.setPhone("999999999");
	            person2.setStart_date("05/05/2010");
	            personsList.add(person2);  
	            
	            person2 = new Person();
	            person2.setName("Igor Vornovitsky");
	            person2.setPosition("Solution Architect");
	            person2.setSalary("$340,800");
	            person2.setOffice("NY");
	            person2.setPhone("987897899");
	            person2.setStart_date("05/05/2010");
	            personsList.add(person2); 
	            
	            person2 = new Person();
	            person2.setName("Java Honk");
	            person2.setPosition("Architect");
	            person2.setSalary("$380,800");
	            person2.setOffice("NY");
	            person2.setPhone("1234567890");
	            person2.setStart_date("05/05/2010");
	            personsList.add(person2); 
	            
	            person2 = new Person();
	            person2.setName("Ramesh Arrepu");
	            person2.setPosition("Sr. Architect");
	            person2.setSalary("$310,800");
	            person2.setOffice("NY");
	            person2.setPhone("4654321234");
	            person2.setStart_date("05/05/2010");
	            personsList.add(person2); 
	            
	            person2 = new Person();
	            person2.setName("Bob Sidebottom");
	            person2.setPosition("Architect");
	            person2.setSalary("$300,800");
	            person2.setOffice("NJ");
	            person2.setPhone("9876543212");
	            person2.setStart_date("05/05/2010");
	            personsList.add(person2); 
	
		}
		
		for (int i = 0; i < pageDisplayLength-5; i++) {
		    Person person2 = new Person();
	            person2.setName("Zuke Torres");
	            person2.setPosition("System Architect");
	            person2.setSalary("$320,800");
	            person2.setOffice("NY");
	            person2.setPhone("999999999");
	            person2.setStart_date("05/05/2010");
	            personsList.add(person2);  
		}
		return personsList;
	}
}