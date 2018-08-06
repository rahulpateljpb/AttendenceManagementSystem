<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<jsp:include page="report.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-2.0.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/Print.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
</head>
<body>
	<c:set var="string" value="${reportList}" />
	<!-- reportList is used to check availability of data -->
	<c:choose>
		<c:when test="${string=='LISTEMPTY'}">
			<h4 style="color: #A52A2A; text-align: center;">Sorry no record
				found for the input data......!!!</h4>
		</c:when>
		<c:otherwise>
			<center>
				<img src="${pageContext.request.contextPath}/images/tata_h.png"
					style="display: none"> <img
					src="${pageContext.request.contextPath}/images/tata_f.png"
					style="display: none">

				<table id="printTable">
					<caption>
						<h5 align="left">${reportHead }</h1>
							<!-- reportHead is used to display the title of table -->
					</caption>
					<thead class="reportOneHead">
						<tr>
							<th>Associate ID</th>
							<th style="width: 150px;">Associate Name</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Start Time</th>
							<th>End Time</th>

						</tr>
					</thead>
					<tbody class="scrollReportOne">
						<c:forEach items="${reportList}" var="report">
							<!-- reportList contain the List of objects of Reort.java -->
							<tr>
								<td><c:out value="${report.associateId }" /></td>
								<td style="width: 150px; padding-left: 75px;"><c:out
										value="${report.associateName}" /></td>
								<td><c:out value="${report.startDate}" /></td>
								<td style="padding-left: 65px;"><c:out
										value="${report.endDate}" /></td>
								<td style="padding-left: 65px;"><c:out
										value="${report.startTime}" /></td>
								<td style="padding-left: 105px;"><c:out
										value="${report.endTime}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<button class="button" id="button">Print</button>
				<!-- Used t print the above table  -->
			</center>
		</c:otherwise>
	</c:choose>
</body>
</html>
