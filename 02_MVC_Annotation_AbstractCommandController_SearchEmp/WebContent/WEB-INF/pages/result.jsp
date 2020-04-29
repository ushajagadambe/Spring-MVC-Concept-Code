<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
     <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>  
     <c:choose>
     <c:when test="${! empty listrdto }">
      <table align="center" border="1" bgcolor="cyan">
      <tr bordercolor="red">
         <th>EmpNo</th> <th>EmpName </th> <th>esalary </th>  <th>deptno </th> 
      </tr>
      <c:forEach var="emp" items="${listrdto}">
        <tr bgcolor="blue">
           <td>${emp.empnumber}</td>
          <td>${emp.empname}</td>
           <td>${emp.sal}</td>
           <td>${emp.deptno}</td>
           
        </tr>
      </c:forEach>
    
    </table>
     
     </c:when>
     <c:otherwise>
     <h1 style="Color:red">Record not found</h1>
     <a href="search_form.jsp">Home</a>
     </c:otherwise>
     </c:choose>
   
