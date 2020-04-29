package com.nt.empDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.nt.bo.EmpBO;
import com.nt.bo.EmpBOResult;
import com.nt.dto.EmpDTO;

@Repository
public class EmpDAOImp implements EmpDAO {
@Autowired	
private DriverManagerDataSource ds;
private static final  String query="select eno,ename,esalary,deptno from emp where eno=? and ename=? and esalary>=?";
	@Override
	public List<EmpBOResult> emp_Ser() throws Exception {
		List<EmpBOResult> list=new ArrayList<EmpBOResult>();
		EmpBOResult rbo=null;
		EmpBO bo=new EmpBO();
	Connection con=	ds.getConnection();
 PreparedStatement ps=con.prepareStatement(query);
 ps.setInt(1, bo.getEmpnumber());
 ps.setString(2, bo.getEmpname());
 ps.setDouble(3, bo.getSal());
 ResultSet rs=ps.executeQuery();
 while(rs.next())
 {
	 rbo=new EmpBOResult();
	 rbo.setEmpnumber(rs.getInt(1));
	 rbo.setEmpname(rs.getString(2));
	 rbo.setSal((double) rs.getInt(3));
	 rbo.setDeptno(rs.getInt(6));
	 list.add(rbo);
 }
		return list;
		
		


	}

}
