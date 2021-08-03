<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>
<title>User Menu</title>
<h2 User Menu />
</head>

<body>
	${SimpleUser.emailAddress}

	<br>
	<br>

	<a href="logout">Log out</a>

	<br>
	<br>
	<form:form action="new-package-of-documents"
		modelAttribute="SimpleUser">

		<form:input type="hidden" path="emailAddress"
			value="${SimplUuser.emailAddress}" />
		<form:errors path="emailAddress" />


		<form:input type="hidden" path="password"
			value="${SimpleUser.password}" />
		<form:errors path="password" />
		<br>
		<br>

		<input type="submit" value="Add new package of documents" />
	</form:form>

	<br>
	<br>
	<form:form action="my-packages"
		modelAttribute="SimpleUser">

		<form:input type="hidden" path="emailAddress"
			value="${SimplUuser.emailAddress}" />
		<form:errors path="emailAddress" />


		<form:input type="hidden" path="password"
			value="${SimpleUser.password}" />
		<form:errors path="password" />
		<br>
		<br>

		<input type="submit" value="My packages" />
	</form:form>

	<br>
	<br>





</body>

</html>










