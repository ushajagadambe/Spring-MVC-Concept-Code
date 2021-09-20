<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1 style="align=center">Employee Reg</h1>
<form:form action="/getFormData" method="post" modelAttribute="emp">
 Name:<form:input path="empName"/><br>
 Address:<form:input path="empAddress"/><br>
 Salary:<form:input path="salary"/><br>
 <input type="submit" value="registater">
</form:form>