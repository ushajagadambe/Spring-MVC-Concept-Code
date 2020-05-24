package com.nt.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutusController  {
   @RequestMapping("about_us.htm")
	 public String getAboutusCon()
	 {
		 return "show_about_us";
	 }
	

}
