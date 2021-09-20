package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
  @Autowired
  private EmployeeService employeeService;
	@GetMapping("/getregistrationForm")
	public String getregistrationForm(@ModelAttribute("emp") Employee emp)
	{
		return "employee";
	}
	@PostMapping("/getFormData")
	public String getFormData(Map<String,Object> map,@ModelAttribute("emp") Employee emp)
	{
		Map<String,Object> result=employeeService.getFormData(emp);
		map.put("name", result.get("name"));
		map.put("address", result.get("address"));
		map.put("salary", result.get("salary"));
		return "result";
	}
	
}
