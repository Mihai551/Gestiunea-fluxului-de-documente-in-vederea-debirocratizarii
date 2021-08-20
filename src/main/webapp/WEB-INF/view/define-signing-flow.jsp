<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title>Gestiunea fluxului de documente in vederea
	debirocratizarii</title>
</head>
<body>
	<h2>Define the signing flow</h2>
	<hr>


	<br>
	<br>

	<form:form action="add-step" modelAttribute="signingFlow">



		<form:select path="user">
			<c:forEach items="${users}" var="user">

				<option value="${user}">${user}</option>

			</c:forEach>
		</form:select>
		<br>
		
		<form:input type = "text" path = "step"/> Example(1, 2, 3 ...)
		<br>
		
		<form:input type="hidden" path="ownerEmailAddress"
			value="${myPackage.ownerEmailAddress}" />
			
		<form:input type="hidden" path="packageName"
			value="${myPackage.packageName}" />	
			

		<input type="submit" value="Add step" />

	</form:form>