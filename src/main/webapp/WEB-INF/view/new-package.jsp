<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:useBean id="command"
	class="com.Gestiunea_fluxului_de_documente_in_vederea_debirocratizarii.entities.DocumentPackage"
	scope="request"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<title>Gestiunea fluxului de documente in vederea
	debirocratizarii</title>


</head>
<body>
	<h2>New Package</h2>
	<hr>

	<br>
	<br>

	<form:form action="processForm-new-package" modelAttribute="package">
		
        
			Package name: <form:input type="text" path="packageName"
			value="${package1.packageName}" />

		<br>
		<br>
		
			Description: <form:input type="text" path="description" />

		<br>
		<br>
	
		Permission: <form:input type="text" path="permissionEmailAddress" />

		<br>
		<br>

		<form:input TYPE="radio" name="View" path="permission" value="View" />View
			<form:input TYPE="radio" name="Sign" path="permission" value="Sign" />Sign

		<br>
		<br>
		
		File name: <form:input type="text" path="documentName" />


		<br>
		<br>
		
		File Path: <form:input type="text" path="IN_FILE" />


		<br>
		<br>

		<form:input type="hidden" path="ownerEmailAddress"
			value="${SimpleUser.emailAddress}" />


		<br>
		<br>


		<input type="submit" value="Submit" />

		<br>
		<br>

	</form:form>


	<br>
	<br>
</body>
</html>