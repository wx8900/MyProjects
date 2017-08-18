package priv.cai.jobapply.springmvc.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
 
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
 
    // Static Resource Config 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/resources/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/resources/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/h5css/**").addResourceLocations("/WEB-INF/html5/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/h5scripts/**").addResourceLocations("/WEB-INF/html5/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/h5images/**").addResourceLocations("/WEB-INF/html5/images/").setCachePeriod(31556926);
    }
 
     
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
  
}
