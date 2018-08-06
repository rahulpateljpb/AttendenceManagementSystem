<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="images/Tata_logo.svg.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendance Capture Tool</title>
</head>
<body background="${pageContext.request.contextPath}/images/bk3.png"
	style="background-size: 2200px 1000px; background-repeat: inherit;">
	<header> <logo> <img
		src="${pageContext.request.contextPath}/images/tcs.png" ; alt="logo"
		align="right" height="100" width="290" /> </logo>
	<h3>Attendance Capture Tool</h3>
	</header>
	<ul id="highlight">
		<li><a href="notice" id="home"><img
				src="images/icon_home.png" height="20" width="25"></a>
		<li><a href="view" id="view">Add Associate</a></li>
		<li><a href="addDemo" id="demo">Add Demo</a></li>
		<li><a href="addAttendance" id="attd">Mark Attendance</a></li>
		<li><a href="report" id="report">View Report</a></li>
	</ul>
	<footer>Copyright © Tata Consultancy Services 2016</footer>
</body>
</html>