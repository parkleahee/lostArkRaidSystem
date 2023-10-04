<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="/WEB-INF/views/scriptFunction.jsp"%>
<!DOCTYPE HTML>
<!--
	Dimension by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>로스트아크 공격대 관리 시스템</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${cp}/resources/assets/css/main.css" />
		<noscript><link rel="stylesheet" href="${cp}/resources/assets/css/noscript.css" /></noscript>
		<style>
			.m_contant{
				width: 50vw;
			}
			.m_contant > input[type=text] {
				width: 80%;
			}
			.m_contant > input[type=text]+input[type=button]{
				width: 17%;
			}
		</st
		</style>
	</head>
	
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<!-- <header id="header">
						
						<div class="content">
							<div class="inner">
								<h1>그룹 만들기</h1>
							</div>
						</div>
						<nav>
							<ul>
								<li><button onclick="goPage('group','makeGroup');">그룹 생성 </button></li>
								<li><button onclick="goPage('group','magGroup');">그룹 관리 </button></li>
								<li><button onclick="goPage('group','makeRaid');">공대 생성</button></li>
								<li><button onclick="goPage('group','magRaid');">공대 관리</button></li>
								<li><a href="#elements">Elements</a></li>
							</ul>
						</nav>
					</header> -->

				<!-- Main -->
				<%-- <div id="main_div">
					<article id="intro">
								<h2 class="major">그룹 만들기</h2>
								
								<div class="m_contant">
									<form action="${cp }/group/makeGroupOk">
										<input type="text" name="groupName"><input type="button" value="중복확인"> 
										<input type="submit" value="만들기">
									</form>
								</div>
							</article>
							</div> --%>
							<article id="contact">
								<div class="m_contant">
								<h2 class="major">그룹 만들기</h2>
								<form method="post" action="${cp }/group/makeGruopOk" name="makeGroup">
									<div class="fields">
										<div class="field">
											<label for="groupName">그룹 이름</label>
											<input type="text" name="groupName" id="groupName"  style="width: 80%; display: inline;"/>
											<input type="button" onclick="checkGroupName()" value="중복확인" style="display: inline;"/>
											<br>
											<span id="result">&nbsp;</span>
										</div>
										<div class="field half" style="width: 17%; display: inline-block;">
											<label for="name">&nbsp;</label>
										</div>
									</div>
									<ul class="actions">
										<li><input type="submit" value="그룹 생성" class="primary" /></li>
										<li><input type="reset" value="Reset" /></li>
									</ul>
								</form>
								</div>
								</article>
				<!-- Footer -->
					<footer id="footer">
						<p class="copyright">&copy; Design: 도아가공룡둘리.</p>
					</footer>

			</div>

		<!-- BG -->
			<div id="bg"></div>

		<!-- Scripts -->
			<script src="${cp}/resources/assets/js/jquery.min.js"></script>
			<script src="${cp}/resources/assets/js/browser.min.js"></script>
			<script src="${cp}/resources/assets/js/breakpoints.min.js"></script>
			<script src="${cp}/resources/assets/js/util.js"></script>
			<script src="${cp}/resources/assets/js/main.js"></script>
			<script src="${cp}/resources/js/group.js"></script>

	</body>
</html>
