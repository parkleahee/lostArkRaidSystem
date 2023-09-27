<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <%-- <img alt="" src="${character.characterImage }"> --%>
	<c:forEach items="${character }" var="ch">
		${ch.characterName }
		${ch.itemMaxLevel } 
		${ch.serverName}
		
		<br>
	</c:forEach>
</body>
</html>