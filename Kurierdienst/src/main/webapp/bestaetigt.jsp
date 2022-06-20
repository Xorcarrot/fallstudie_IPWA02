<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Vielen Dank für ihren Auftrag! Ihre Referenznummer lautet: ${nummer} 
	<br>
	${preis}
	<br>
	${anzahlMarken}
	
	<div style="margin: 10px">
	<form action="index">
		<input type="submit" value="Startseite"/>
	</form>
	</div>
	
</body>
</html>