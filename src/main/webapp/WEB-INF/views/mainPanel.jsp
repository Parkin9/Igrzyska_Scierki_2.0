<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="css/style.css"/>">
	<title>Igrzyska Ścierki - Panel Główny</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>TODO: User.username</h1>
    </div>
    <div id="menu">
        <a href="<c:url value="/game"/>"><button><b>Rozgrywka</b></button></a>
        <a href="<c:url value="/addTask"/>"><button>Zarządzaj Zadaniami</button></a>
        <a href="<c:url value="/addPlayer"/>"><button>Zarządzaj Graczami</button></a>
        <a href="<c:url value="/logout"/>"><button>Wyloguj</button></a>
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