<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl_PL">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<title>Igrzyska Ścierki - Panel Główny</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>${name}</h1>
    </div>
    <div id="menu">
    	<table>
    		<tr>
    			<td>
		        	<a href="<c:url value='/game'/>"><button><b>Rozgrywka</b></button></a>
		        </td>
		        <td>
		        	<a href="<c:url value='/addTask'/>"><button>Zarządzaj Zadaniami</button></a>
		        </td>
		        <td>
		        	<a href="<c:url value='/addPlayer'/>"><button>Zarządzaj Graczami</button></a>
		        </td>
		        <td>
		        	<c:url value="/bye" var="logoutUrl"/>
		        	<form method="POST" action="${logoutUrl}">
		        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        		<button type="submit">Wyloguj</button>
		        	</form>
		        </td>
	        </tr>
        </table>
    </div>
    <div id="currentScore">
        <table>
            <tr>
                <th>Aktualna rozgrywka</th>
            </tr>
            <c:forEach items="${playerList}" var="player">
            <tr>
                <td>
                <c:out value="${player.name}"/>
                <c:out value=" - "/>
                <c:out value="${player.score}"/>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>