package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class EmployeeService {

	public  Map<String,Object> getFormData(Employee emp) {
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("name", emp.getEmpName());
		map.put("address",emp.getEmpAddress());
		map.put("salary", emp.getSalary());
		return map;
		
	}

}
