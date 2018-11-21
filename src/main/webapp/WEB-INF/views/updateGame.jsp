<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl_PL">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<title>Igrzyska Ścierki - Rozgrywka</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>Aktualna Rozgrywka</h1>
    </div>
    <div id="menu">
        <a href="<c:url value='/panel'/>"><button>Wróć do Panelu Głównego</button></a>
    </div>
    <div id="currentScore">
        <c:url value='/updateGame' var="gameUrl"/>
        <form method="POST" action="${gameUrl}">
            <div style="float: left">
                <table>
                    <tr>
                        <th></th>
                        <th>Gracze</th>
                    </tr>
                    <c:forEach items="${players}" var="player">
                    <tr>
                        <td><input type="radio" name="whichPlayerDo" value="${player.id}"></td>
                        <td><c:out value=" ${player.playerName}"/></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div style="float: left">
                <table>
                    <tr>
                        <th></th>
                        <th>Zadania</th>
                    </tr>
                    <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td><input type="checkbox" name="whichTasksIsDone" value="${task.id}"></td>
                        <td><c:out value="${task.taskName}"/></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div style="clear: both">
                <button type="submit">Wykonane</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>