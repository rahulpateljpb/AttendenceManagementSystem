<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body id="home" class="home">
	<marquee class="marquee" behavior="alternate" direction="left"
		scrollamount="5" width="85%">
		<c:set var="demoId" value="${demo.demoId}" />
		<c:choose>
			<c:when test="${demoId!=0}">
				<span style="color: purple;">Notice: </span>Last Demo Was on: <b>${demo.demoName }</b> Conducted On "<b>
					${demo.demoDate }"</b>
			</c:when>
			<c:otherwise>
				<h4 class="warning">Notice: No Demo conducted</h4>
			</c:otherwise>

		</c:choose>
	</marquee>
	<c:set var="demoList" value="${nextDemo}" />
	<c:choose>
		<c:when test="${demoList=='empty'}">
			<center>
				<span class="warning"
					style="color: #A52A2A; font-size: 12px; text-align: center;"><b>No
						Upcoming Demos Available......!!!</b></span>
			</center>
		</c:when>
		<c:otherwise>
			<center>
				<table class="nextDemo">
					<caption class="caption">
						<b>Upcoming Demos</b>
					</caption>
					<thead class="nextDemoHead">
						<tr>
							<th>Demo Name</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Start Time</th>
							<th>End Time</th>
						</tr>
					</thead>
					<tbody class="scrollNextDemo">
						<c:forEach items="${nextDemo}" var="nextDemo">
							<tr>
								<td><c:out value="${nextDemo.demoName}" /></td>
								<td><c:out value="${nextDemo.demoDate}" /></td>
								<td><c:out value="${nextDemo.demoEndDate}" /></td>
								<td><c:out value="${nextDemo.startDateTime}" /></td>
								<td><c:out value="${nextDemo.endDateTime}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</center>
		</c:otherwise>
	</c:choose>

</body>
</html>