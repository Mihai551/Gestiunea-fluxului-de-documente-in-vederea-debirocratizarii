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

	<form:form action="package-for-me" modelAttribute="thePackage">



		<form:select path="packageName">
			<c:forEach items="${packages.list}" var="pack">

				<option value="${pack.packageName}">${pack.packageName}</option>

			</c:forEach>
		</form:select>

		<form:input type="hidden" path="ownerEmailAddress"
			value="${packages.fromUser}" />
		
		<form:input type="hidden" path="permissionEmailAddress"
			value="${packages.forUser}" />

		<input type="submit" value="Open package" />

	</form:form>