<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/user.css">
</head>
<body>
	<% 
		if(request.getParameter("id") != null){//넘어온 아이디가 있으면~
			session.setAttribute("id", request.getParameter("id"));
		}/* else if(session.getAttribute("id") != null){//로그인한 상태라면~
			
		}else{// 로그인 페이지를 거치지 않았을 시 testID로 로그인된 상태로~
			session.setAttribute("id", "testID");
		} */
	%>
	<!-- <div class="container"> -->
	<div id="login_include" class="container">

		<ul>
			<c:if test="${id == null }">
			<li><a href="<%=request.getContextPath() %>/user_join.do">회원가입</a></li>
			<li><a href="<%=request.getContextPath() %>/user_login.do">로그인</a></li>
			<li><a href="<%=request.getContextPath() %>/ssc_user_main.do">홈</a></li>
			</c:if>
			<c:if test="${id != null }">
			<li>${id }님 환영합니다</li>
			<li><a href="<%=request.getContextPath() %>/user_logout.do">로그아웃</a></li>
			<li><a href="<%=request.getContextPath() %>/ssc_user_main.do">홈</a></li>
			</c:if>
		</ul>
	</div>
<!-- 	</div> -->
</body>
</html>