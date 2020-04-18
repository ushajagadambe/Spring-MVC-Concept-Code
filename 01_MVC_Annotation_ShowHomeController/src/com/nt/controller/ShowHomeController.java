package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowHomeController {
    @RequestMapping("showhome.htm")
	public String Handle()
{
	return "result";
}
}
