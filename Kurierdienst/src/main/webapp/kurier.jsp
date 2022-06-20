<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table, tr, td, th {
		border: 1px solid black;
		border-collapse: collapse;
	}
</style>
</head>
<body>

	<h2>Sendung abholen</h2>
	<table>
		<tr>
			<th>Referenznummer</th>
			<th>Abmessungen</th>
			<th>Gewicht</th>
			<th>Sperrgut</th>
			<th>Abgeholt?</th>
		</tr>
		
		<c:forEach items="${listAbh}" var="sen">
		
			<tr>
				<td><c:out value="${sen.referenznummer}" /></td>
				<td><c:out value="${sen.abmessungen}"/></td>
				<td><c:out value="${sen.gewicht}"/></td>
				<td><c:out value="${sen.sperrgut}"/></td>
				<td><form action="senAbholen"><input type="submit" value="${sen.referenznummer}" name="abgeholt" /></form></td>
			</tr>
		
		</c:forEach>
	</table>
	
	<h2>Sendung sortieren</h2>
	<table>
		<tr>
			<th>Referenznummer</th>
			<th>Land</th>
			<th>Postleitzahl</th>
			<th>Wohnort</th>
			<th>In Zustellung?</th>
		</tr>
		
		<c:forEach items="${listEnt}" var="sen">
		
			<tr>
				<td><c:out value="${sen.referenznummer}" /></td>
				<td><c:out value="${sen.emp.land}"/></td>
				<td><c:out value="${sen.emp.postleitzahl}"/></td>
				<td><c:out value="${sen.emp.wohnort}"/></td>
				<td><form action="senAbholen"><input type="submit" value="${sen.referenznummer}" name="abgeholt" /></form></td>
			</tr>
		
		</c:forEach>
	</table>
	
	<h2>Sendung in Zustellung</h2>
	<table>
		<tr>
			<th>Referenznummer</th>
			<th>Vorname</th>
			<th>Nachname</th>
			<th>Adresse</th>
			<th>Zugestellt?</th>
		</tr>
		
		<c:forEach items="${listZus}" var="sen">
		
			<tr>
				<td><c:out value="${sen.referenznummer}" /></td>
				<td><c:out value="${sen.emp.vorname}"/></td>
				<td><c:out value="${sen.emp.nachname}"/></td>
				<td><c:out value="${sen.emp.adresse}"/></td>
				<td><form action="senAbholen"><input type="submit" value="${sen.referenznummer}" name="abgeholt" /></form></td>
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