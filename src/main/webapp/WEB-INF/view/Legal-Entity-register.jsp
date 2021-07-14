<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
<title>Gestiunea fluxului de documente in vederea debirocratizarii</title>

	<style>
		.error {color:red}
	</style>

</head>
<body>
<h2>Employee registration page</h2>
<hr>

<br><br>

	
	
	<form:form action="processForm-legal-entity" modelAttribute="user">
		
			Legal Entity: <form:input path="legalEntityName" />
			<form:errors path="legalEntityName" cssClass="error" />
			<br><br>
			
			Country: <form:input path="country" />
			<form:errors path="country" cssClass="error" />
			<br><br>
		
			City: <form:input path="city" />
			<form:errors path="city" cssClass="error" />
			<br><br>
			
			Email Address: <form:input path="emailAddress" />
			<form:errors path="emailAddress" cssClass="error" />
			<br><br>
		
			Address: <form:input path="address" />
			<form:errors path="address" cssClass="error" />
			<br><br>
			
			Password: <form:input path="password" />
			<form:errors path="password" cssClass="error" />
			<br><br>
			
			Confirm password: (for future implementation)
				
		<br><br>
		
		<input type="submit" value="Submit" />	
		
		<br><br>
		
	</form:form>		
			

<br><br>
</body>
</html>