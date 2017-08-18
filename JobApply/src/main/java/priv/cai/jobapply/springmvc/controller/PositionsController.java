package priv.cai.jobapply.springmvc.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import priv.cai.jobapply.springmvc.model.Positions;
import priv.cai.jobapply.springmvc.service.PositionsService;
 
@Controller
@Scope("prototype")
public class PositionsController {
	
	final static Logger logger = Logger.getLogger(PositionsController.class);
	
	@Autowired
	PositionsService service;
	
	/*@Autowired
	MessageSource messageSource;*/
	
	List<Positions> positionsList;

	
	@RequestMapping(value = { "/listAll" }, method = RequestMethod.GET)
	public String listPositions(Model model) {
		
		positionsList = service.findAllPositions(0, 10);
		model.addAttribute("positions", positionsList);
		
		return "allpositions";
	}
	
	/*@RequestMapping(value = { "/listAppliedPositions" }, method = RequestMethod.GET)
	public String listAppliedPositions(Model model) {
		
		Integer userId =   
		
		positionsList = service.findAllAppliedPositions(userId);
		model.addAttribute("positions", positionsList);
		
		return "allpositions";
	}*/
	
	/**
	 * Pagination
	 * @param model
	 * @return
	 * 
	 * http://javahonk.com/spring-mvc-pagination-datatables/
	 */
	@RequestMapping(value = { "/listAllPages" }, method = RequestMethod.GET)
	public String listPositions(@RequestParam("currentPageNo") int currentPageNo, Model model) {
		
		int startPage = (int) (currentPageNo - 5 > 0 ? currentPageNo - 5 : 1);
		int endPage = startPage + 10;

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("positionsPages", positionsList.subList(startPage, endPage));

		return "allpositions";
		/*<div class="pagination">
	    <ul>
	        <li><c:forEach begin="${startpage}" end="${endpage}" var="p"><a href="<c:url value="/list" ><c:param name="page" value="${p}"/>${p}</c:url>">${p}</a></c:forEach></li>
	    </ul>
	</div>
	<c:if test="${page > 1}">
       <li class="active"><a href="#">First</a></li>
    </c:if>*/
		
		
		/*<div id="pagination">

	    <c:url value="/user/list" var="prev">
	        <c:param name="page" value="${page-1}"/>
	    </c:url>
	    <c:if test="${page > 1}">
	        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
	    </c:if>

	    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
	        <c:choose>
	            <c:when test="${page == i.index}">
	                <span>${i.index}</span>
	            </c:when>
	            <c:otherwise>
	                <c:url value="/user/list" var="url">
	                    <c:param name="page" value="${i.index}"/>
	                </c:url>
	                <a href='<c:out value="${url}" />'>${i.index}</a>
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>
	    <c:url value="/user/list" var="next">
	        <c:param name="page" value="${page + 1}"/>
	    </c:url>
	    <c:if test="${page + 1 <= maxPages}">
	        <a href='<c:out value="${next}" />' class="pn next">Next</a>
	    </c:if>
	</div>*/
	}
	
	@RequestMapping(value = { "/searchjobs" }, method = RequestMethod.GET)
	public String listPositions(@RequestParam("q") String keywords,
								@RequestParam("l") String location, Model model) {

		List<Positions> listPositions = new ArrayList<Positions>(75);
		
		if((keywords == null || ("").equals(keywords)) && (location == null || ("").equals(location))) {
			listPositions = service.findAllPositions(0, 10);
		} else {
			//listPositions = service.findByParamValue("location", location);
			listPositions = service.findPositionsByConditions(keywords, location);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is keywords : " + keywords);
			logger.info("This is location : " + location);
			logger.info("This is listPositions.size : " + listPositions.size());
		}
		
		model.addAttribute("positions", listPositions);
		return "allpositions";
	}
	
	@RequestMapping(value = { "/searchjobsbyapplied" }, method = RequestMethod.GET)
	public String listPositionsApplied(@RequestParam("userId") String userId, Model model) {

		List<Positions> listPositions = new ArrayList<Positions>(75);
		
		if(userId == null || ("").equals(userId)) {
			
		} else {
			listPositions = service.findPositionsByCondition("jobapplydate", "isNotNull");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is userId : " + userId);
			logger.info("This is listPositions.size : " + listPositions.size());
		}
		
		model.addAttribute("positions", listPositions);
		return "allappliedposition";
	}
	
	@RequestMapping(value = { "/searchjobsbylikeit" }, method = RequestMethod.GET)
	public String listPositionsLikeit(@RequestParam("userId") String userId, Model model) {

		List<Positions> listPositions = new ArrayList<Positions>(75);
		
		if(userId == null || ("").equals(userId)) {
			
		} else {
			listPositions = service.findPositionsByCondition("likeit", "isNotNull");
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is userId : " + userId);
			logger.info("This is listPositions.size : " + listPositions.size());
		}
		
		model.addAttribute("positions", listPositions);
		return "alllikeposition";
	}
	
	@RequestMapping(value = { "/likeit" }, method = RequestMethod.GET)
	public String likeIt(@RequestParam("likeId") int likeId, Model model) {
		Positions po = service.findById(likeId);
		
		if (po != null) {
			po.setLikeit("1");
			service.saveOruUpdatePositions(po);
			if(logger.isInfoEnabled()){
				logger.info("This is saveOruUpdatePositions : " + po.toString());
			}
		}
		
		return "redirect:/listAll";
	}

}