<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	table, tr, td, th {
		border: 1px solid black;
		border-collapse: collapse;
	}
</style>
</head>
<body>

	<h2>Sendungen Status</h2>
	<table>
		<tr>
			<th>Referenznummer</th>
			<th>Status</th>
		</tr>
		
		<c:forEach items="${listAbh}" var="sen">
		
			<tr>
				<td><c:out value="${sen.referenznummer}" /></td>
				<td><c:out value="${sen.status}"/></td>
			</tr>
		
		</c:forEach>
		
		<c:forEach items="${listEnt}" var="sen">
		
			<tr>
				<td><c:out value="${sen.referenznummer}" /></td>
				<td><c:out value="${sen.status}"/></td>
			</tr>
		
		</c:forEach>
		
		<c:forEach items="${listZus}" var="sen">
		
			<tr>
				<td><c:out value="${sen.referenznummer}" /></td>
				<td><c:out value="${sen.status}"/></td>
			</tr>
		
		</c:forEach>
		
		<c:forEach items="${listZug}" var="sen">
		
			<tr>
				<td><c:out value="${sen.referenznummer}" /></td>
				<td><c:out value="${sen.status}"/></td>
			</tr>
		
		</c:forEach>
	</table>
	
	<div style="margin: 10px">
	<form action="index">
		<input type="submit" value="Startseite"/>
	</form>
	</div>

</body>
</html>