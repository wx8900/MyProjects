package priv.cai.jobapply.dataload.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;

import priv.cai.jobapply.constant.Constants;

public class LoadProperties {
	
	public static String initProperties() throws FileNotFoundException {
		String result = "";
		InputStream input = null;
		try {
			input = new FileInputStream(Constants.PATH_PROPERTIES);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("property file in the path '" + Constants.PATH_PROPERTIES + "' not found!");
		}

		try {
			Properties prop = new Properties();
			prop.load(input);

			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				// System.out.println("Key : " + key + ", Value : " + value);
				result += value + ",";
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return "";
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// e.printStackTrace();
					input = null;
				}
			}
		}
		return result.substring(0, (result.length() - 1));
	}

	public static void main(String[] args) throws FileNotFoundException {
		//System.out.println(initProperties());
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
        for (BeanDefinition beanDefinition : scanner.findCandidateComponents("com.springmvc.controller")){
            System.out.println(beanDefinition.getBeanClassName());
        }
	}

}
