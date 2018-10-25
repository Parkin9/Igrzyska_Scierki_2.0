<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="css/style.css"/>">
	<title>Igrzyska Ścierki - Zarządzanie Graczami</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>Zarządzanie Graczami</h1>
    </div>
    <div id="menu">
        <a href="<c:url value="/panel"/>"><button>Wróć do Panelu Głównego</button></a>
    </div>
        <form:form modelAttribute="player">
        <table>
            <tr>
                <td>
                    <label for="name">Nazwa Gracza: </label>
                </td>
                <td>
                    <form:input path="name" id="name"/>
                </td>
                <td>
                    <form:errors path="name"/><br>
                </td>
            </tr>
        </table>
            <button type="submit">Dodaj</button>
        </form:form>
    <hr>
    <div id="currentScore">
        <table>
            <tr>
                <th>Gracz</th>
                <th></th>
            </tr>
            <c:forEach items="${playerList}" var="player">
                <tr>
                    <td><c:out value="${player.name}"/></td>
                    <td><a href="<c:url value="/deletePlayer/${player.id}"/>"><button>Usuń</button></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>