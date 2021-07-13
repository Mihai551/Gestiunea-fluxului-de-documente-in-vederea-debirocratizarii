<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
<title>Gestiunea fluxului de documente in vederea debirocratizarii</title>
</head>
<body>
<h2>Gestiunea fluxului de documente in vederea debirocratizarii</h2>
<hr>

<br><br>
<form:form action="register" modelAttribute="accountType">


Sign up as:
	
	<form:select path="accountType">
		
		<form:option value="Individual" />
		<form:option value="Legal entity" />
		<form:option value="Employee" />
			
		<br><br>
		
		<input type="submit" value="Sign up" />	
		
		<br><br>
		
	</form:select>
	</form:form>
<br><br>
</body>
</html>