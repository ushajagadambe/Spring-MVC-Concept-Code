package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactUsController {

	@RequestMapping("contact_us.htm")
	public String getContactuscon()
	{
		return "show_contact_us";
	}

}
