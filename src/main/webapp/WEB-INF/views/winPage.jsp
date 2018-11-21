<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl_PL">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<title>Igrzyska Ścierki</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>Gratulację!</h1>
    </div>
    <div id="menu">
        <a href="<c:url value='/panel'/>"><button>Wróć do Panelu Głównego</button></a>
    </div>
    <div style="text-align: center">
    	<c:out value="Rozgrywka dobiegła końca"/><br>
        <c:out value="Zwycięzcą zostaje ${winner}!"/><br>
        <c:out value="Osiągnięty wynik to ${winnerScore} punktów."/>
    </div>

</div>
</body>
</html>