package priv.cai.jobapply.springmvc.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@Controller
public class PathVariableExampleController {
 
 
  /**
   * @PathVariable Example:
   *
   * <pre>
   *
   *   http://o7planning.org/web/fe/default/en/document/8108/spring-mvc-for-beginners
   *  
   *   or
   *  
   *   http://localhost:8080/HelloSpringMVC/web/fe/default/en/document/8108/spring-mvc-for-beginners
   *
   * </pre>
   */
  @RequestMapping("/web/fe/{sitePrefix}/{language}/document/{id}/{naturalText}")
  public String documentView(Model model,
          @PathVariable(value = "sitePrefix") String sitePrefix,
          @PathVariable(value = "language") String language,
          @PathVariable(value = "id") Long id,
          @PathVariable(value = "naturalText") String naturalText) {
 
      model.addAttribute("sitePrefix", sitePrefix);
      model.addAttribute("language", language);
      model.addAttribute("id", id);
      model.addAttribute("naturalText", naturalText);
 
      String documentName = "Java tutorial for Beginners";
      if (id == 8108) {
          documentName = "Spring MVC for Beginners";
      }
 
      model.addAttribute("documentName", documentName);
 
      return "documentView";
  }
}
