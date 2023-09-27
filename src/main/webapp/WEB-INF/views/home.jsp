<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:set value="${pageContext.request.contextPath }" var="cp" scope="session"/>
    <%@ include file="/WEB-INF/views/scriptFunction.jsp"%>
<html>
<head>
	<title>Home</title>
</head>
<style>
*{
    padding: 0;
    margin: 0;
    border: none;
}
body{
    font-size: 14px;
    font-family: 'Roboto', sans-serif;
}
.login-wrapper{
    width: 400px;
    margin : 200px auto;
    height: 350px;
    padding: 40px;
    box-sizing: border-box;
}

.login-wrapper > h2{
    font-size: 24px;
    color: #6A24FE;
    margin-bottom: 20px;
}
#login-form > input{
    width: 100%;
    height: 48px;
    padding: 0 10px;
    box-sizing: border-box;
    margin-bottom: 16px;
    border-radius: 6px;
    background-color: #F8F8F8;
}
#login-form > input::placeholder{
    color: #D2D2D2;
}
#login-form > input[type="submit"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
    margin-top: 20px;
}

#login-form > input[type="button"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
    margin-top: 20px;
}


#login-form > input[type="checkbox"]{
    display: none;
}
#login-form > label{
    color: #999999;
}
#login-form input[type="checkbox"] + label{
    cursor: pointer;
    padding-left: 26px;
    background-image: url("checkbox.png");
    background-repeat: no-repeat;
    background-size: contain;
}
#login-form input[type="checkbox"]:checked + label{
    background-image: url("checkbox-active.png");
    background-repeat: no-repeat;
    background-size: contain;
}
</style>
	<c:if test="${joinok eq 't' }">
	<script type="text/javascript">
		alert('회원가입이 완료되었습니다')
	</script>
</c:if>
<body>
    <div class="login-wrapper">
        <h2>로스트아크 레이드 공대 관리 시스템</h2>
        <form method="post" action="${cp }/user/login" id="login-form">
            <input type="text" name="userid" placeholder="Email">
            <input type="password" name="userpw" placeholder="Password">
            <label for="remember-check">
                <input type="checkbox" id="remember-check">아이디 저장하기
            </label>
            <input type="submit" value="Login">
            <input type="button" value="Join" onclick="goPage('user','join')">
        </form>
    </div>
</body>
</html>
