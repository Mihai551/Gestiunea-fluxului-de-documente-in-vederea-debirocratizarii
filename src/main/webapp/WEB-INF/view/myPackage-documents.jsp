<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title>Gestiunea fluxului de documente in vederea
	debirocratizarii</title>
	
	<script type="text/javascript">
	if ("${alert}") {

		alert("${alert}");
	}
</script>
	
</head>
<body>
	<h2>Gestiunea fluxului de documente in vederea debirocratizarii</h2>

	<hr>
	Package: ${myPackage.packageName}

	<br>
	<br>

	<form:form action="my-document" modelAttribute="documents">

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
				<form:input TYPE="radio" name="Sign" path="action"
			value="Signatures" />Signatures

		<br>
		<br>
		<form:input type="hidden" path="ownerEmailAddress"
			value="${myPackage.ownerEmailAddress}" />

		<form:input type="hidden" path="packageName"
			value="${myPackage.packageName}" />

		<input type="submit" value="Submit" />
	</form:form>

	<br>
	<br>

	<form:form action="enable-disalbe-signingFlow"
		modelAttribute="myPackage">

		<form:input type="hidden" path="ownerEmailAddress"
			value="${myPackage.ownerEmailAddress}" />

		<form:input type="hidden" path="packageName"
			value="${myPackage.packageName}" />

		<form:input type="hidden" path="signingFlowEnable"
			value="${myPackage.signingFlowEnable}" />

		<input type="submit" value="${enable_disable}" />
	</form:form>

	<br>
	<br>
	<br>
	<br>

	<form action="upload" method="post" enctype="multipart/form-data">
		Add new document: <br> <br> <input type="text"
			name="documentName" value=documentName /> <input type="file"
			name="file" /> <br> <br> <input type="hidden"
			name="ownerEmailAddress" value="${myPackage.ownerEmailAddress}" /> <input
			type="hidden" name="packageName" value="${myPackage.packageName}" />
		<input type="submit" />

	</form>

	<form action="add-permission" method="post">

		<br> <br> Permission: <input type="text"
			name="permissionEmailAddress" /> <br> <br> <input
			TYPE="radio" name="permission" value="View" />View <input
			TYPE="radio" name="permission" value="Sign" />Sign <br> <br>

		<input type="hidden" name="ownerEmailAddress"
			value="${myPackage.ownerEmailAddress}" /> <input type="hidden"
			name="packageName" value="${myPackage.packageName}" /> <input
			type="submit" value="Submit" />

	</form>

	<br>
	<br>

	<form:form action="define-signing-flow" modelAttribute="myPackage">


		<br>
		<br>
		<form:input type="hidden" path="ownerEmailAddress"
			value="${myPackage.ownerEmailAddress}" />

		<form:input type="hidden" path="packageName"
			value="${myPackage.packageName}" />

		<input type="submit" value="Define signing flow" />
	</form:form>

	<br>
	<br>


	<c:forEach items="${signatures}" var="signature">
		<td>Signed by: ${signature}<br> <br>
		<td>
	</c:forEach>