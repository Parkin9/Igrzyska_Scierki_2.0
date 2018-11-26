<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl_PL">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<title>Igrzyska Ścierki - Archiwum</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>Archiwum Rozgrywek</h1>
    </div>
    <div id="menu">
        <a href="<c:url value='/panel'/>"><button>Wróć do Panelu Głównego</button></a>
    </div>
    <div>
    	<table>
    		<tr>
    			<th>Początek</th>
    			<th>Koniec</th>
    			<th>Zwycięzca</th>
    		</tr>
    		<c:forEach items="${archivedGames}" var="game">
    		<tr>
    			<td>
    				<c:out value="${game.start}"/>
    			</td>
    			<td>
    				<c:out value="${game.end}"/>
    			</td>
    			<td>
    				<c:out value="${game.winner}"/>
    			</td>
    		</tr>
    		</c:forEach>
    	</table>
    </div>
</div>
</body>
</html>