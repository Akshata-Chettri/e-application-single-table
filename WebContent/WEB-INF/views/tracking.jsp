<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Application System</title>
</head>
<body>
	<div align="right">
		<form:form action="logout" method="GET">
			<input type="submit" value="Logout" />
		</form:form>
	</div>
	<div align="left">
		<form:form action="/spring-hibernate-e-application-single-table/backToStudentPortal" method="GET">
			<input type="submit" value="Back" />
		</form:form>
	</div>

	<h3>Student Portal</h3>
	Unique Id: ${information.uniqueid}
	<br> Name: ${information.name}
	<br>
	<br>

	<div align="center">
		<h4>Student Request Tracking</h4>
		<form:form action="/spring-hibernate-e-application-single-table/trackBonafideStatus/${information.uniqueid}"
			method="GET">
			<input type="submit" value="Track Bonafide Request" />
		</form:form>
		<br>
		<form:form action="/spring-hibernate-e-application-single-table/trackTransferStatus/${information.uniqueid}"
			method="GET">
			<input type="submit" value="Track Transfer Request" />
		</form:form>
	</div>
</body>
</html>