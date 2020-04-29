package com.nt.empService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bo.EmpBO;
import com.nt.bo.EmpBOResult;
import com.nt.dto.EmpDTO;
import com.nt.dto.EmpDTOResult;
import com.nt.empDAO.EmpDAO;
import com.nt.empDAO.EmpDAOImp;

@Service
public class EmpServiceImp  implements EmpService{
	@Autowired
	private EmpDAOImp dao;
	@Override
	public List<EmpDTOResult> emp_serach() throws Exception {
		EmpDTO dto=new EmpDTO();
		EmpBO bo=new EmpBO();
		BeanUtils.copyProperties(dto, bo);
		List<EmpDTOResult> listrdto =new ArrayList<EmpDTOResult>();
		List<EmpBOResult> listbo=null;
		EmpDTOResult rdto=null;EmpBOResult rbo=null;
		listbo =dao.emp_Ser();
	Iterator<EmpBOResult> it=	listbo.iterator();
	while(it.hasNext())
	{   rdto=new EmpDTOResult();
		rbo=it.next();
		BeanUtils.copyProperties(rbo, rdto);
		listrdto.add(rdto);
	}
		return listrdto;
	}

}
