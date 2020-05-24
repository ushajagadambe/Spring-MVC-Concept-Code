package com.nt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.nt.controller")
public class AppConfig {
public InternalResourceViewResolver getResolver()
{
	InternalResourceViewResolver vr=new InternalResourceViewResolver();
	vr.setPrefix("/WEB-INF/pages/");
	vr.setSuffix(".jsp");
	return null;
	
}
}
