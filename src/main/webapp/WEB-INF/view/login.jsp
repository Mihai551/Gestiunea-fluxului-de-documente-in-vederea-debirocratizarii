<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Gestiunea fluxului de documente in vederea
	debirocratizarii</title>

<style>
.hidden {
	display: none;
	visibility: hidden;
}
</style>

</head>
<body>
	<h2>Login page</h2>
	<hr>

	<br>
	<br>
	<c:set var="accountType" value="${AccountType.accountType}" />
	<form:form action="processForm-login" modelAttribute="SimpleUser">
		
			Email Address: <form:input type="text" path="emailAddress" />
		<form:errors path="emailAddress" cssClass="error" />
		<br>
		<br>
		
			
			Password: <form:input type="password" path="password" />
		<form:errors path="password" cssClass="error" />
		<br>
		<br>

		<form:input path="accountType" value="${SimpleUser.accountType} "
			cssClass="hidden" />

		<br>

		<input type="submit" value="Submit" />
	</form:form>


	<br>
	<br>
</body>
</html>