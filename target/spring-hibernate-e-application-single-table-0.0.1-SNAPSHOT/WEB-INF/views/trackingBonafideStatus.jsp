<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<title>E-Application System</title>
</head>
<body>
	<div align="left">
		<form:form
			action="/spring-hibernate-e-application-single-table/backToTracking"
			method="GET">
			<input type="submit" value="Back" />
		</form:form>
	</div>

	<h3>Student Portal</h3>
	Unique Id: ${information.uniqueid}
	<br> Name: ${information.name}
	<br>
	<br>
	<h3>Student Bonafide Request Tracking</h3>
	<c:if test="${not empty information }">
		<div class="table-responsive">
			<table class="table table-striped table-bordered"
				style="font-size: 13px">
				<thead>
					<tr>
						<th>Student</th>
						<th>Pending At</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${information.bonafide}</td>
						<td>${information.bonafidePendingAt}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${empty information }">
		<h4>No Request Sent</h4>
	</c:if>
</body>
</html>