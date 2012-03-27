package foo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Chuj implements ApplicationContextAware {
	
	ApplicationContext context;
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
		System.out.println(context.getDisplayName());
		String[] beans = context.getBeanDefinitionNames();
		for(String b : beans){
			System.out.println(b);
		}
		System.out.println(context.getParent());
	}
	
}
