<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema Movie</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/user.css">
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div align="center" class="container">

		<div class="header-user">
			<h1 class="header-title">Movie</h1>
		</div>
		<c:set var="dto" value="${content }"/>
		<jsp:include page="../include/user_top.jsp" />
		<a class="go-list" href="<%=request.getContextPath() %>/user_movie_info.do">목록으로</a>
		<table class="table table-bordered">
			<tr>
				<td class="movie_cont_td" rowspan="6"><img class="movie_cont_img" src="<%=request.getContextPath() %>${dto.getM_image() }"></td>
				<th colspan="4" class="movie_title">${dto.getM_name() }</th>
			</tr>
			<tr>
				<th>감독</th><td>${dto.getM_director() }</td>
				<th>배우</th><td>${dto.getM_actor() }</td>
			</tr>
			<tr>
				<th>장르</th><td>${dto.getM_genre() }</td>
				<th>개봉일</th><td>${dto.getM_playdate().substring(0,10) }</td>
			</tr>
			<tr>
				<th>러닝타임</th><td colspan="3">${dto.getM_time() }</td>
			</tr>
			<tr>
				<td colspan="4" style="word-break: break-all;">
					${dto.getM_cont() }
				</td>
			</tr>
			<tr>
				<td align="right" colspan="4">
					<a class="reservation"href="<%=request.getContextPath()%>/user_reserv.do">예매하기</a>
				</td>
			</tr>
		</table>
	
	
		<jsp:include page="../include/user_bottom.jsp" />
	</div>
</body>
</html>