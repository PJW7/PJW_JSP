<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/admin_style.css?after9">
</head>
<body>

	<div class="container" align="center">
		<div class="header-admin">
	   		<h1 class="header-title">영화 상세내용</h1>
	   </div>
		<jsp:include page="../include/admin_top.jsp"/>
		<br>
		
		<c:set var="dto" value="${movie }" />
		<div align="center">
			<img class="movie_cont_img_sm" src="<%=request.getContextPath() %>${dto.getM_image() }">
		</div>
		<br>
		
		<table class="table table-bordered" style="width: 100%;">
			<c:if test="${!empty dto }">
				<tr>
					<td class="movie_cont_td" rowspan="5"><img class="movie_cont_img" src="<%=request.getContextPath() %>${dto.getM_image() }"></td>
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
					<td colspan="4" align="center">
						<input type="button" class="btn btn-primary" value="수정" onclick="location.href='admin_movie_edit.do?no=${dto.getM_num() }'">
	            		<input type="button" class="btn btn-danger" value="삭제"	 onclick="location.href='admin_movie_delete.do?no=${dto.getM_num() }'">
	            		<input type="button" class="btn btn-secondary" value="전체목록" onclick="location.href='admin_movie_list.do'">
					</td>
				</tr>
			</c:if>
			<c:if test="${empty dto}">
				<tr>
					<td colspan="4" align="center">
						<h3>영화가 존재하지 않습니다</h3>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<button onclick="location.href='admin_movie_list.do'">목록으로</button>
					</td>
				</tr>
			</c:if>
		</table>
		
		<br>
		<hr width="50%" color="blue">
		<br>
		
		
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>