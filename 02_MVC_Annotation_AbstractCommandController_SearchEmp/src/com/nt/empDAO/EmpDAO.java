package com.nt.empDAO;

import java.util.List;

import com.nt.bo.EmpBO;
import com.nt.bo.EmpBOResult;
import com.nt.dto.EmpDTO;

public interface EmpDAO {
	public List<EmpBOResult> emp_Ser() throws Exception;
}
