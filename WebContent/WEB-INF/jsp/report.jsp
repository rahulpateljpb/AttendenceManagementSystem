<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Report</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/validation/validate.js">
	
</script>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
</head>
<body class="reportTable" id="report">
	<center>
		<form:form method="POST" commandName="modelReport"
			action="displayReport" onsubmit="return validateReport()">
			<div class="error" id="error"
				style="position: relative; top: 8px; height: 13px;"></div>
			<table>
				<tr>
					<td>Demo</td>
					<td><form:select id="demoName" path="demoName" style="width: 162px; height:20px;text-transform: capitalize;">
					<c:forEach var="list" items="${DemoList}">
					<form:option value="${list }"></form:option>
					</c:forEach>
					</form:select>
						</td>
				</tr>
				<tr>
					<td>From Date</td>
					<td><form:input id="fromDate" type="date" path="fromDate"
							style="width: 162px; height:20px;" /></td>
					<td>To Date</td>
					<td><form:input id="toDate" type="date" path="toDate"
							style="width: 162px; height:20px;" /></td>
				</tr>
			</table>
			<br>
			<input class="button" type="submit" value="Get Report" />
		</form:form>
	</center>
</body>
</html>