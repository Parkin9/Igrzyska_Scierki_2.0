<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pl_PL">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://fonts.googleapis.com/css?family=Modern+Antiqua&amp;subset=latin-ext" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value='css/style.css'/>">
	<script type="text/javascript" src="<c:url value='js/jscript.js'/>"></script>
	<title>Igrzyska Ścierki - Zarządzanie Zadaniami</title>
</head>
<body>
<div id="container">
	<div id="gameName">
		<h1>Zarządzanie Zadaniami</h1>
	</div>
    <div id="menu">
        <a href="<c:url value='/panel'/>"><button>Wróć do Panelu Głównego</button></a>
    </div>
   	<c:url value="/addTask" var="addTaskUrl"/>
	<form:form method="POST" action="${addTaskUrl}" modelAttribute="task">
		<table>
			<tr>
	           	<td>
	               	<label for="taskName">Nazwa Zadania: </label>
	           	</td>
	           	<td>
	               	<form:input path="taskName"/>
	           	</td>
	           	<td>
	               	<form:errors path="taskName"/><br>
	           	</td>
	       	</tr>
	       	<tr>
	           	<td>
	               	<label for="pointsValue">Wartość punktowa: </label>
	           	</td>
	           	<td>
	               	<form:input type="number" path="pointsValue"/>
	           	</td>
	           	<td>
	               	<form:errors path="pointsValue"/><br>
	           	</td>
	       	</tr>
	   	</table>
		<button type="submit">Dodaj</button>
		<span class="errorDiv"><c:out value="${errorAddTask}"/></span>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form:form>
	<hr>
	<div id="currentScore">
		<table>
        	<tr>
                <th>Zadanie</th>
                <th>Punkty</th>
                <th></th>
            </tr>
            <c:forEach items="${tasks}" var="task">
            <tr>
                <td><c:out value="${task.taskName}"/></td>
                <td><c:out value="${task.pointsValue}"/></td>
            	<td><a href="<c:url value='/deleteTask/${task.id}'/>"><button>Usuń</button></a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>