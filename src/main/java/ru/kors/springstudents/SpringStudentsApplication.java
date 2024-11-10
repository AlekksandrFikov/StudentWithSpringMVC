package ru.kors.springstudents;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@SpringBootApplication
public class SpringStudentsApplication {
	

	
	public static void main(String[] args) {
	
		SpringApplication.run(SpringStudentsApplication.class, args);
//		System.out.println("1");
//	
//		assertFalse(true);
//
//		System.out.println("2");
	} 

	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	void d () {
		
	}
}
