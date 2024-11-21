package de.apicall.utils;

import org.springframework.stereotype.Component;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


@Component
public class SpringContextHelper implements ApplicationContextAware {

	private static ApplicationContext context; 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		context = applicationContext; 
	}
	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass); 
	}
	 

}
