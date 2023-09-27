<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
						
						<div class="content">
							<div class="inner">
								<h1>로스트아크 공격대 관리 시스템</h1>
								<p>어서오세요 ${loginUser.userCharacterVo.characterName }님</p>
							</div>
						</div>
						<nav>
							<ul>
								<li><button onclick="goPage('group','makeGroup');">그룹 생성 </button></li>
								<li><button onclick="goPage('group','magGroup');">그룹 관리 </button></li>
								<li><button onclick="goPage('group','makeRaid');">공대 생성</button></li>
								<li><button onclick="goPage('group','magRaid');">공대 관리</button></li>
								<!--<li><a href="#elements">Elements</a></li>-->
							</ul>
						</nav>
					</header>

				<!-- Main -->
					
				<!-- Footer -->
					<footer id="footer">
						<p class="copyright">&copy; Untitled. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
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

	</body>
</html>
