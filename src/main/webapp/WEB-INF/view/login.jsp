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
	<form:form action="processForm-login" modelAttribute="SimpleUser">
		
			Email Address: <form:input type="text" path="emailAddress" />
		<form:errors path="emailAddress" cssClass="error" />
		<br>
		<br>
		
			
			Password: <form:input type="password" path="password" />
		<form:errors path="password" cssClass="error" />
		<br>
		<br>


		<br>

		<input type="submit" value="Submit" />
	</form:form>


	<br>
	<br>
</body>
</html>