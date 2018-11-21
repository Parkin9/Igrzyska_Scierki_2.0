<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl_PL">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<title>Igrzyska Ścierki - Rozpocznij nową rozgrywkę</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>Nowa Rozgrywka</h1>
    </div>
    <div id="menu">
        <a href="<c:url value='/panel'/>"><button>Wróć do Panelu Głównego</button></a>
    </div>
    <c:url value="/newGame" var="addGameUrl"/>
    <form:form method="POST" action="${addGameUrl}" modelAttribute="game">
        <table>
            <tr>
                <td>
                    <label for="end">Do kiedy ma trwać gra: </label>
                </td>
                <td>
                    <form:input type="date" path="end"/>
                </td>
                <%-- <td>
                    <form:errors path="end"/><br>
                </td> --%>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">START</button>
    </form:form>
</div>
</body>
</html>