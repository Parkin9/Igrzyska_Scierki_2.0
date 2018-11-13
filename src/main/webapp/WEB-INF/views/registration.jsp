<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl_PL">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<title>Igrzyska Ścierki - Rejestracja konta</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>Igrzyska Ścierki</h1>
    </div>
    <div id="menu">
        <a href="<c:url value='/welcome'/>"><button>Logowanie</button></a>
    </div>
    <div>
    	<c:url value='/registration' var="registrationUrl"/>
        <form:form method="POST" action="${registrationUrl}" modelAttribute="usersAccount">
        <div>
            <table>
                <tr>
                    <td>
                        <label for="accountName">Nazwa konta: </label>
                    </td>
                    <td>
                        <form:input path="accountName"/>
                        <form:errors path="accountName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">Hasło: </label>
                    </td>
                    <td>
                        <form:password path="password"/>
                        <form:errors path="password"/>
                    </td>
                </tr>
                <tr>
                	<td>
                		<label for="passwordConfirm">Potwierdź hasło:</label>
                	</td>
                	<td>
                		<form:password path="passwordConfirm"/>
                	</td>
                </tr>
            </table>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <span class="logButton">
            <button type="submit">Zarejestruj</button>
            <span class="errorDiv"><c:out value="${errorRegistration}"/></span>
        </span>
        </form:form>
    </div>
</div>
</body>
</html>