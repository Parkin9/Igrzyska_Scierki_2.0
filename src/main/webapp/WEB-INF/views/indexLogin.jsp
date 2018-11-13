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
        <h1>Igrzyska Ścierki</h1>
    </div>
    <div style="text-align: center">
    	<a href="<c:url value='/registration'/>"><button>Zarejestruj się</button></a>
    	<c:url value='/welcome' var="loginUrl"/>
        <form method="POST" action="${loginUrl}">
	       	<table style="margin: 0px auto">
	       		<tr>
	       			<td>
	           			<label for="accountName">Login: </label>
	           		</td>
	           		<td>
	           			<input type="text" name="accountName"/>
	           		</td>
	           	</tr>
	           	<tr>
	           		<td>
	           			<label for="password">Hasło: </label>
	           		</td>
	           		<td>
	           			<input type="password" name="password"/>
	           		</td>
	           	</tr>
	        	<tr class="logButton">
	        		<td style="margin: auto">
	            		<button type="submit">Zaloguj</button>
	            	</td>
	            </tr>
	        </table>
	    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <c:if test="${param.error != null}">
        	<div class="errorDiv">Niepoprawny Login i/lub Hasło.</div>
        </c:if>
        <c:if test="${param.logout != null}">
        	<div>Poprawne wylogowanie. Do zobaczenia!</div>
        </c:if>
    </div>
</div>
</body>
</html>