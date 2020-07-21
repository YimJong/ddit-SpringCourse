<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css" type="text/css">
<title>회원관리 관리자</title>
<!-- 해당 페이지 공통 로직 구성 및 공통 라이브러리 선언 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/commons.js"></script>
</head>
<body>
	<div class="wrap">
		<div id="header">
			<div class="top_bn">
				<ul>
					<li><a href="#"><img src="${pageContext.request.contextPath }/image/admin_t.jpg" /></a></li>
					<li><a href="#"><img src="${pageContext.request.contextPath }/image/admin_f.jpg" /></a></li>
					<li><a href="#"><img src="${pageContext.request.contextPath }/image/admin_homelink.jpg" /></a></li>
					<li><a href="javascript:location.href='${pageContext.request.contextPath }/security/join/logout.do';"><img src="${pageContext.request.contextPath }/image/admin_logout.jpg" /></a></li>
				</ul>
			</div>
			<div class="logo">
				<img src="${pageContext.request.contextPath }/image/logo.jpg" />
			</div>
			<div class="gnb">
				<ul>
					<li><a href="#">회원관리</a></li>
					<li><a href="#">공지사항</a></li>
					<li><a href="#">게시판</a></li>
					<li><a href="#">자료실</a></li>
					<li><a href="#">Q&A</a></li>
					<li><a href="#">메일전송</a></li>
					<li><a href="#">통계 분석</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>