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
			action="/spring-hibernate-e-application-single-table/backToPrincipalPortal"
			method="GET">
			<input type="submit" value="Back" />
		</form:form>
	</div>
	<h3>Approved Bonafide Certificate</h3>
	<c:if test="${not empty information }">
		<div class="table-responsive">
			<table class="table table-striped table-bordered"
				style="font-size: 13px">
				<thead>
					<tr>
						<th>Unique Id</th>
						<th>Name</th>
						<th>Certificate</th>
						<th>Approve Request</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="information" items="${information }">
						<tr>
							<td>${information.uniqueid}</td>
							<td>${information.name}</td>
							<td>${information.bonafide}</td>
							<td><a href="approvedBonafideRequest/${information.uniqueid}"><span
									class="glyphicon glyphicon-thumbs-up"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${empty information }">
		<h4>Empty Results</h4>
	</c:if>
</body>
</html>