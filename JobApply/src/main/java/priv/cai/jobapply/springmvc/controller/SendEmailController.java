package priv.cai.jobapply.springmvc.controller;
 
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import priv.cai.jobapply.constant.Constants;
import priv.cai.jobapply.springmvc.model.Positions;
import priv.cai.jobapply.springmvc.service.EmailService;
import priv.cai.jobapply.springmvc.service.PositionsService;
 
@Controller
public class SendEmailController {
	
	final static Logger logger = Logger.getLogger(SendEmailController.class);
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	PositionsService positionsService;
	
	/*@Autowired
	MessageSource messageSource;*/

	
	@RequestMapping(value = { "/apply" }, method = RequestMethod.GET)
	public String listPositions(@RequestParam("applyId") int applyId, Model model) {
		
		if(logger.isInfoEnabled()){
			logger.info("apply =========> sendEmail =========> id : "+applyId);
		}
		
		// to do
		// query the email address of this position by position.id
		/*String[] emailaddresses = service.findEmailAddressesByIds();
		for (int i = 0; i < emailaddresses.length; i++ ) {
			String emailAddressTo = emailaddresses[i];
			service.sendEmail(emailAddressTo);
		}*/
		
		Positions po = positionsService.findById(applyId);
		
		if (po != null) {
		    DateTimeFormatter dtf = DateTimeFormat.forPattern(Constants.DATAFORMAT_YMD_HMS);
		    String date = dtf.print(new DateTime());
			po.setJobapplydate(date);
			po.setJoblastchecked(date.substring(0, 10));
			po.setLikeit("1");
			positionsService.saveOruUpdatePositions(po);
			
			try {
				emailService.sendEmail(null, null, null, po.getApplyemail());
			} catch (Exception e) {
				logger.error("This is error : " + e);
			}
			model.addAttribute("sendEmail", "success!");
		}
		
		return "redirect:/listAll";
	}
	
	public static void main(String[] args) {
	    DateTime dateTime = new DateTime();
	    DateTimeFormatter dtf = DateTimeFormat.forPattern(Constants.DATAFORMAT_YMD_HMS);
	    System.out.println(dtf.print(dateTime));
	 
	  }
	

}