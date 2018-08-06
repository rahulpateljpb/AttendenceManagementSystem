<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="common.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/validation/addAssociateValidation.js">
	
</script>
<title>Add Associate</title>
</head>
<body id="associate">
	<center>
		<form:form id="associate_form" commandName="ModelAssociate"
			method="POST" action="add" onsubmit="return emailValidate()">
			<div style="position: relative; top: 8px; height: 13px;">
				<c:set var="success" value="${success}" />
				<span class="success"><c:if
						test="${success=='Associate added successfully.'}">${success}</c:if></span>
				<span class="warning"><c:if
						test="${success=='Associate Id already exists.'}">${success}</c:if></span>
			</div>
			<table>
				<tr>
					<td><form:label path="associateId">Associate Id<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input path="associateId" placeholder="e.g.(123456)"
							maxlength="7" onkeypress="return isNumber(event)" /></td>
					<td><form:label path="associateName">Associate Name<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="text" path="associateName"
							placeholder="Associate name" maxlength="30"
							onkeypress="return onlyAlphabets(event,this);"
							onblur="this.value=this.value.toUpperCase()"
							onchange="return trim(this)" /></td>
				</tr>
				<tr>
					<td></td>
					<td><div style="position: relative; top: -10px; height: 10px;">
							<form:errors path="associateId" cssClass="error" />
						</div></td>
					<td></td>
					<td><div style="position: relative; top: -10px; height: 10px;">
							<form:errors path="associateName" cssClass="error" />
						</div></td>
				</tr>
				<tr>
					<td><form:label path="contactNumber">Mobile Number<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input path="contactNumber" maxlength="10"
							placeholder="Contact number" onkeypress="return isNumber(event)" /></td>
					<td><form:label path="emailId">Email<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="myEmail" path="emailId" name="myEmail"
							maxlength="40" placeholder="eg:(abc@tcs.com)"
							onblur="this.value=this.value.toUpperCase()"
							onchange="return trim(this)" /></td>
				</tr>
				<tr>
					<td></td>
					<td><div style="position: relative; top: -10px; height: 10px;">
							<form:errors path="contactNumber" cssClass="error" />
						</div>
					<td></td>
					<td>
						<div style="position: relative; top: -10px; height: 10px;">
							<form:errors path="emailId" cssClass="error" />
						</div>
					</td>
				</tr>
			</table>
			<br>
			<input class="button" type="submit" value="Add Associate" />
		</form:form>
	</center>
	<center>
		<!-- for display a table below on addAssociate.jsp -->
		<c:set var="string" value="${allAssociates}" />
		<c:set var="check" value="${message}" />
		<c:choose>
			<c:when test="${string=='No Associate present in database.'}">
				<br>
				<h4 class="warning">${allAssociates}</h4>
			</c:when>
			<c:otherwise>
				<table class="addAssociate">
					<thead class="addAssociateHead">
						<tr>
							<th>Associate Id</th>
							<th>Associate Name</th>
							<th>Contact</th>
							<th>Email</th>
						</tr>
					</thead>
					<tbody class="scrollAddAssociate">
						<c:forEach items="${allAssociates}" var="associate">
							<tr>
								<td><c:out value="${associate.getAssociateId()}" /></td>
								<td><c:out value="${associate.getAssociateName()}" /></td>
								<td><c:out value="${associate.getContactNumber()}" /></td>
								<td><c:out value="${associate.getEmailId()}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</center>
</body>
</html>

<!-- for js purpose replace this with code between line 17 to line 83
	<center>
		<form:form id="associate_form" commandName="ModelAssociate"
			method="POST" action="add" onsubmit="return validate()">
			<div class="error" id="error"
				style="position: relative; top: 8px; height: 13px;">
				<c:set var="success" value="${success}" />
				<span class="success"><c:if
						test="${success=='Associate added successfully.'}">${success}</c:if></span>
				<span class="warning"><c:if
						test="${success=='Associate Id already exists.'}">${success}</c:if></span>
				<form:errors path="associateId" cssClass="error" />
				<form:errors path="associateName" cssClass="error" />
				<form:errors path="contactNumber" cssClass="error" />
				<form:errors path="emailId" cssClass="error" />
			</div>
			<table>
				<tr>
					<td><form:label path="associateId">Associate Id<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="associateId" path="associateId"
							placeholder="e.g.(123456)" maxlength="7"
							onkeypress="return isNumber(event)" /></td>
					<td><form:label path="associateName">Associate Name<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="text" path="associateName"
							placeholder="Associate name" maxlength="30"
							onkeypress="return onlyAlphabets(event,this);" onchange="return trim(this)" /></td>
				</tr>
				<tr>
					<td><form:label path="contactNumber">Mobile Number<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="contactNumber" path="contactNumber"
							maxlength="10" placeholder="Contact number"
							onkeypress="return isNumber(event)" /></td>
					<td><form:label path="emailId">Email<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="myEmail" path="emailId"
							placeholder="e.g.(abc@abc.domain)" maxlength="40"  onchange="return trim(this)" /></td>
				</tr>
			</table>
			<br>
			<input class="button" type="submit" value="Add Associate" />
		</form:form>
	</center>
 -->