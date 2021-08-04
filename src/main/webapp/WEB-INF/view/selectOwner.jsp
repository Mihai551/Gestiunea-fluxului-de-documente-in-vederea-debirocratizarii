<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title>Gestiunea fluxului de documente in vederea
	debirocratizarii</title>
</head>
<body>
	<h2>Gestiunea fluxului de documente in vederea debirocratizarii</h2>
	<hr>


	<br>
	<br>

	<form:form action="select-package" modelAttribute="packages">



		<form:select path="fromUser">
			<c:forEach items="${packages.fromUsers}" var="pack">

				<option value="${pack}">${pack}</option>

			</c:forEach>
		</form:select>

		<form:input type="hidden" path="forUser"
			value="${packages.forUser}" />
			
			<form:input type="hidden" path="permission"
			value="${packages.permission}" />

		<input type="submit" value="Go to packages" />

	</form:form>