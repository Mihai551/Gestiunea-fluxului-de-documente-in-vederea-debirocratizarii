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

	<form:form action="processForm-employee" modelAttribute="user">
		
			Legal Entity: <form:input path="legalEntityEmailAddress" />
			<form:errors path="legalEntityEmailAddress" cssClass="error" />
			<br><br>
			
			First name: <form:input path="firstName" />
			<form:errors path="firstName" cssClass="error" />
			<br><br>
		
			Last name: <form:input path="lastName" />
			<form:errors path="lastName" cssClass="error" />
			<br><br>
			
			Email Address: <form:input path="emailAddress" />
			<form:errors path="emailAddress" cssClass="error" />
			<br><br>
		
			PIN: <form:input path="pin" />
			<form:errors path="pin" cssClass="error" />
			<br><br>
			
			Password: <form:input path="password" />
			<form:errors path="password" cssClass="error" />
			<br><br>
			
			Invite Code (from Legal Entity) :<form:input path="inviteCode" />
			<form:errors path="inviteCode" cssClass="error" />
			<br><br>
			
			
			Confirm password: (for future implementation)
				
		<br><br>
		
		<input type="submit" value="Submit" />	
		
		<br><br>
		
	</form:form>		
			

<br><br>
</body>
</html>