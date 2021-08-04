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
	Package: ${thePackage.packageName}

	<br>
	<br>

	<form:form action="document" modelAttribute="documents">

		<form:select path="documentName">
			<c:forEach items="${documents.list}" var="doc">

				<option value="${doc.documentName}">${doc.documentName}</option>



			</c:forEach>
		</form:select>
		<br>
		<br>	
			
			Action: 
		<br>
		<br>
		<form:input TYPE="radio" name="View" path="action" value="View" />View
			<form:input TYPE="radio" name="Sign" path="action" value="Sign" />Sign

		<br>
		<br>
		<form:input type="hidden" path="ownerEmailAddress"
			value="${thePackage.ownerEmailAddress}" />

		<form:input type="hidden" path="packageName"
			value="${thePackage.packageName}" />
			
		<form:input type="hidden" path="permission"
			value="${thePackage.permission}" />
			
		<form:input type="hidden" path="permissionEmailAddress"
			value="${thePackage.permissionEmailAddress}" />

		<input type="submit" value="Submit" />
	</form:form>