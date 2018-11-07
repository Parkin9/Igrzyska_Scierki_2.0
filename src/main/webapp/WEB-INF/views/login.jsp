<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<title>Igrzyska Ścierki</title>
</head>
<body>
<div id="container">
    <div id="gameName">
        <h1>Igrzyska Ścierki</h1>
    </div>
    <div style="text-align: center">
    	<c:url value="/login" var="loginProcessingUrl"/>
        <form method="POST" action="${loginProcessingUrl}">
        <div>
            <label for="username">Login: </label>
            <input type="text" id="username" name="username"/>
            <form:errors path="username"/>
            <br/>
            <label for="password">Hasło: </label>
            <input type="password" id="password" name="password"/>
            <form:errors path="password"/>
        </div>
        <span class="logButton">
            <button type="submit">Zaloguj</button>
        </span>
        </form>
        <span class="logButton">
            <a href="<c:url value='/registration'/>"><button>Zarejestruj się</button></a>
        </span>
        <div class="errorDiv">
            ${validErrorPass}
        </div>
    </div>
</div>
</body>
</html>