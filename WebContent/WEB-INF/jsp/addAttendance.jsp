<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/jquery-ui.css">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/autocomplete.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<script
	src="${pageContext.request.contextPath}/validation/addAssociateValidation.js"></script>
<title>AttendanceControllerTool</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
</head>
<body id="attd">
	<center>
		<div class="error" id="error"
			style="position: relative; top: 8px; height: 13px;"></div>
		<form:form id="attendance_form" commandName="ModelDemo"
			action="addAssociate" method="POST"
			onsubmit="return checkvalue(this)">
			<table id="attendance_table" border="0" width="700" height="150"
				align="center">
				<tr>
					<div class="search-container">
						<div class="ui-widget">
							<td><label>Demo Name</label></td>
							<td><form:input type="text" id="search1" name="search"
									path="demoName" class="search" placeholder="enter demo name"
									maxlength="30"/></td>
							<!-- <input type ="hidden" name="demoName" id="demoName"> -->

						</div>
					</div>
				</tr>
				<tr>
					<form:input type="hidden" id="demoIdV" name="demoIdV" path="demoId" />
					<td><label path="startDateTime">Start Date</label></td>
					<td><form:input type="text" id="startDate"
							path="startDateTime" disabled="true" /></td>
					<td><label path="endDateTime">End Date</label></td>
					<td><form:input type="text" id="endDate" path="endDateTime"
							disabled="true" /></td>
					<%-- <td><form:input path="demoDate" /></td> --%>
				</tr>
				<tr>
					<div class="search-container">
						<div class="ui-widget">
							<td>Search Associate</td>
							<td><form:input type="text" id="search" name="search" path=""
								class="search" placeholder="enter associate id" maxlength="7"
								onkeypress="return isNumber(event)" disabled="true"/></td>
							<td><input class="attdbutton" type="button"
								value="Add Associate" onclick="madeAjaxCall();"></td>

						</div>
					</div>
				</tr>
			</table >
			<table id="result1" class="addAttendance">
				<thead class ="addAttendanceHead">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Contact</th>
						<th>Email</th>
					</tr>
				</thead >
				<tbody class="scrollAddAttendance"></tbody>
			</table>
			<br>
			<input id="markattendance" class="button" type="submit"
				value="Mark Attendance">
			<input type="hidden" id="Demo_name" name="Demo_name" value=$search1>

		</form:form>

		<form:form id="sendingDates_form" commandName="ModelDemo"
			action="sendingDates">
			<input type="hidden" id="demoIdname" name="demoIdname"
				value="${sessionScope.demoIdname}" />
			<input type="hidden" id="FlagForajaxcall" name="FlagForajaxcall"
				value='${sessionScope.flagAjax}' />
		</form:form>
	</center>
</body>
</html>