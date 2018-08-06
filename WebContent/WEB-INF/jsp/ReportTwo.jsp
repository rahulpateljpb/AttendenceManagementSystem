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
					alt="logo" align="right" height="100" width="290"
					style="display: none"> <img
					src="${pageContext.request.contextPath}/images/tata_f.png"
					alt="logo" align="bottom" height="15" width="300"
					style="display: none">

				<table id="printTable">
					<caption>
						<p align="left">
							<b align="left">${reportHead }</b>
						</p>
						<!-- reportHead is used to display the title of table -->
					</caption>
					<thead class="reportTwoHead">
						<tr>
							<th style="width: 120px">Associate ID</th>
							<th style="width: 150px">Associate Name</th>
							<th>Demo Name</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Start Time</th>
							<th>End Time</th>
						</tr>
					</thead>
					<tbody class="scrollReportTwo">
						<c:forEach items="${reportList}" var="report">
							<!-- reportList contain the List of objects of Reort.java -->
							<tr>
								<td>${report.associateId }</td>
								<td style="padding-left: 20px;width: 120px ">${report.associateName}</td>
								<td style="padding-left: 30px; width:100px">${report.demoName}</td>
								<td style="padding-left: 15px;"><c:out
										value="${report.startDate}" /></td>
								<td style="padding-left: 20px;" ><c:out value="${report.endDate}" /></td>
								<td style="padding-left: 20px;"><c:out
										value="${report.startTime}" /></td>
								<td style="padding-left: 17px;"><c:out
										value="${report.endTime}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<button class="button" id="button">Print</button>
				<!-- Used t print the  above table  -->
			</center>
		</c:otherwise>
	</c:choose>
</body>
</html>
