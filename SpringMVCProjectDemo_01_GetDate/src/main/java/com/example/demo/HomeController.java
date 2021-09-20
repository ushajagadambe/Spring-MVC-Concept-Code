package com.example.demo;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
	@Autowired
	private ServletConfig config;

		@GetMapping("/")
		public String getHomePgae()
		{
		
			System.out.print(config.getClass());
			return "home";
		}
     @GetMapping("/getDate")
     public String getDate(BindingAwareModelMap map)
     {
    	 System.out.print(map.getClass());
    	 Date date=new Date();
    	 map.put("date",date);
    	 return "date";
     }

}
