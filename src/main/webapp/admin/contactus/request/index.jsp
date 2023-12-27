<%@page import="java.util.ArrayList"%>
<%@page import="com.contactUs.data.DetailsOfMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message Details</title>
<link rel="stylesheet" href="../../../CssFiles/messageData.css">
</head>
<body>
	<header class="head">
		<div class="heading">Welcome to Mountblue</div>
		<div class="link">
			<form action="/contactForm/Logout" method="post">
				<input type="submit" value="logout">
			</form>
		</div>
	</header>
	<%
	response.setHeader("cache-control", "no-cache, no-store, must-revalidate");
	
	if(session.getAttribute("adminLogin")==null){
		response.sendRedirect("/contactForm/");
	}
	else{
	ArrayList<DetailsOfMessage> detailsOfMessage = (ArrayList<DetailsOfMessage>) session.getAttribute("detailsOfMessage");

	if (detailsOfMessage != null) {
	%>
	<h2>Active Messages</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Message</th>
			<th>Active</th>
		</tr>
		<c:forEach var="detail" items="${detailsOfMessage}">
			<c:if test="${detail.active}">
				<tr>
					<td>${detail.id}</td>
					<td>${detail.name}</td>
					<td>${detail.email}</td>
					<td>${detail.message}</td>
					<td>
						<form action="/contactForm/ToggleActiveServlet" method="post">
							<input type="hidden" name="id" value="${detail.id}">
							<button type="submit" value="Toggle">Archived</button>
						</form>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>

	<h2>Inactive Messages</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Message</th>
			<th>Archived</th>
		</tr>
		<c:forEach var="detail" items="${detailsOfMessage}">
			<c:if test="${!detail.active}">
				<tr>
					<td>${detail.id}</td>
					<td>${detail.name}</td>
					<td>${detail.email}</td>
					<td>${detail.message}</td>
					<td>
						<form action="/contactForm/ToggleActiveServlet" method="post">
							<input type="hidden" name="id" value="${detail.id}">
							<button type="submit" value="Toggle">Active</button>
						</form>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<%
} else {
%>
	<p>No detailsOfMessage attribute found in the request.</p>
	<%
}}
%>
</body>
</html>
