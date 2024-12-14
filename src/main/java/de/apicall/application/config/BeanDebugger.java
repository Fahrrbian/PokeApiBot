package de.apicall.application.config;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class BeanDebugger {

	private final ApplicationContext context; 
	
	public BeanDebugger(ApplicationContext context) {
		this.context = context; 
	}
	  @PostConstruct
	    public void printBeans() {
	        System.out.println("///////////////////////////////Registrierte Beans:");
	        //for (String beanName : context.getBeanDefinitionNames()) {
	          //  System.out.println(beanName);
	        //}
	    }
}
