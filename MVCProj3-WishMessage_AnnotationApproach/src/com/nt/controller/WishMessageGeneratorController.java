package com.nt.controller;

import java.util.Calendar;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("wmgc")
public class WishMessageGeneratorController  {
     @RequestMapping("wish.htm")
	public String getMessage(Map<String,Object> map)
	{
		Calendar calendar=null;
		 int hour=0;
		 String msg=null;
		// get System data
		 calendar=Calendar.getInstance();
		//get current hour of the day
		 hour=calendar.get(Calendar.HOUR_OF_DAY);
		 if(hour<12)
			 msg="Good Morning";
		 else if(hour<16)
			  msg="Good AfterNoon";
		 else if(hour<20)
			 msg="Good Evening";
		 else
			 msg="Good Night";
		 map.put("resMsg", msg);
			 return "result";
		
	}
	

}
