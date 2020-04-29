package com.nt.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.dto.EmpDTO;
import com.nt.dto.EmpDTOResult;
import com.nt.empService.EmpService;
import com.nt.empService.EmpServiceImp;

@Controller
public class EmpserachController {
	@Autowired
	private EmpService empser;
    @RequestMapping("welcome.htm")
	public String Handle()
{
	return "search_form";
}
    @RequestMapping("empsearch.htm")
    public String SearchEmp(Map<String,Object> model,@ModelAttribute EmpCommand command)
    {command.setEmpnumber(1);command.setEmpname("dipali");
    	List<EmpDTOResult> listrdto=null;
    	EmpDTO dto=new EmpDTO();
    	BeanUtils.copyProperties(command, dto); 
    	try {
			listrdto=empser.emp_serach();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.put("resDTO", listrdto);
		return "result";
    	
    }
}
