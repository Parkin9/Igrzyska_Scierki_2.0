<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl_PL">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<title>Igrzyska Ścierki - Zarządzanie Graczami</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>Zarządzanie Graczami</h1>
    </div>
    <div id="menu">
        <a href="<c:url value='/panel'/>"><button>Wróć do Panelu Głównego</button></a>
    </div>
    	<c:url value="/addPlayer" var="addPlayerUrl"/>
        <form:form method="POST" action="${addPlayerUrl}" modelAttribute="player">
        <table>
            <tr>
                <td>
                    <label for="playerName">Nazwa Gracza: </label>
                </td>
                <td>
                    <form:input path="playerName"/>
                </td>
                <td>
                    <form:errors path="playerName"/><br>
                </td>
            </tr>
        </table>
            <button type="submit">Dodaj</button>
            <span class="errorDiv"><c:out value="${errorAddPlayer}"/></span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form:form>
    <hr>
    <div id="currentScore">
        <table>
            <tr>
                <th>Gracz</th>
                <th></th>
            </tr>
            <c:forEach items="${players}" var="player">
                <tr>
                    <td><c:out value="${player.playerName}"/></td>
                    <td><a href="<c:url value='/deletePlayer/${player.id}'/>"><button>Usuń</button></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>