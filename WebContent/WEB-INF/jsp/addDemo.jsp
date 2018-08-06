<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="common.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Demo</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/validation/validateDemo.js"></script>
</head>
<body id="demo">
	<center>
		<c:set var="string" value="${success}" />
		<div class="error" id="error"
			style="position: relative; top: 8px; height: 13px;">
			<span class="success"><c:if
					test="${success=='Demo Added successfully.'}">${success}</c:if></span>
		</div>
		<form:form id="demo" commandName="DemoDetailModel" method="POST"
			action="addDetail" onsubmit="return validateDemo()">
			<table>
				<tr>
					<td><form:label path="demoName">Demo Name<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="demoName" type="text" path="demoName"
							onkeypress="return onlyAlphabets(event,this);"
							onblur="this.value=this.value.toUpperCase()" /></td>
					<td></td>
				</tr>
				<tr>
					<td><form:label path="startDate">Start Date<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="startDate" type="date" path="startDate" /></td>
					<td><form:label path="startTime">Start time<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:select path="startTime" id="startTime"
							style="width: 162px; height:20px;">
							<div class="dropmenudiv">
								<form:options items="${timeList}" />
							</div>
						</form:select></td>
				</tr>
				<tr>
					<td><form:label path="endDate">End Date<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:input id="endDate" type="date" path="endDate" /></td>
					<td><form:label path="endTime">End Time<span
								class="text-danger">*</span>
						</form:label></td>
					<td><form:select path="endTime" id="endTime"
							style="width: 162px; height:20px;">
							<form:options items="${timeList}" />
						</form:select></td>
				</tr>
			</table>
			<br>
			<input class="button" type="submit" value="Add Demo" />
		</form:form>
	</center>
	<center>
		<!-- for display a table below on addAssociate.jsp -->
		<c:set var="demoList" value="${demoList}" />
		<c:choose>
			<c:when test="${demoList=='No demo in database'}">
				<br>
				<h4 class="warning">${demoList}</h4>
			</c:when>
			<c:otherwise>
				<table class="addDemo">
					<thead class="addDemoHead">
						<tr>
							<th>Demo Id</th>
							<th>Demo Name</th>
							<th>Start Date</th>
							<th>End Date</th>
						</tr>
					</thead>
					<tbody class="scrollAddDemo">
						<c:forEach items="${demoList}" var="demo">
							<tr>
								<td><c:out value="${demo.getDemoId()}" /></td>
								<td><c:out value="${demo.getDemoName()}" /></td>
								<td><c:out value="${demo.getStringStartDateAndTime()}" /></td>
								<td><c:out value="${demo.getStringEndDateAndTime()}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</center>
</body>
</html>