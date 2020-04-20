package io.toolbox.alu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

@SpringBootApplication
@Configuration
public class AcceptCurlFilesApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(AcceptCurlFilesApplication.class, args);
	}

	
	@Bean public CommonsMultipartResolver multipartResolver() 
	{ 
		System.out.println("Creating bean multiipart resolver");
		CommonsMultipartResolver multipart = new CommonsMultipartResolver(); 
		return multipart;
	} 
	
	@Bean 
	@Order(0) 
	public MultipartFilter multipartFilter() 
	{ 
		MultipartFilter multipartFilter = new MultipartFilter(); 
		multipartFilter.setMultipartResolverBeanName("multipartReso‌​lver"); 
		return multipartFilter; 
     }	
	
	@Bean
	public FilterRegistrationBean registration(HiddenHttpMethodFilter filter) 
	{
	    FilterRegistrationBean registration = new FilterRegistrationBean(filter);
	    registration.setEnabled(false);
	    return registration;
	}	
}
