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
<link rel="stylesheet" href="css/admin_style.css?after6">
</head>
<body>

	<div class="container" align="center">
		<div class="header-admin">
	   		<h1 class="header-title">영화 수정</h1>
	   </div>
		<jsp:include page="../include/admin_top.jsp"/>
		<br>
		
		<div class="movie_edit" align="center">
			<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/admin_movie_edit_ok.do">
			<table class="table table-bordered" style="width: 100%">
				<c:set var="dto" value="${edit }" />
				<c:if test="${!empty dto }">
				<input type="hidden" name="m_old_image" value="${dto.getM_image() }">
				<input type="hidden" name="m_num" value="${dto.getM_num() }">
					<tr>
						<th>영화 이미지</th>
						<td>
							<img class="movie_edit_img" src="<%=request.getContextPath() %>${dto.getM_image() }">
							<input type="file" name="m_new_image">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input name="m_name" value="${dto.getM_name() }"></td>
					</tr>
					<tr>
						<th>감독</th>
						<td><input name="m_director" value="${dto.getM_director() }"></td>
					</tr>
					<tr>	
						<th>배우</th>
						<td><input name="m_actor" value="${dto.getM_actor() }"></td>
					</tr>
					<tr>
						<th>장르</th>
						<td><input name="m_genre" value="${dto.getM_genre() }"></td>
					</tr>
					<tr>
						<th>개봉일</th>
						<td><input type="date" name="m_playdate" value="${dto.getM_playdate() }"></td>
					</tr>
					<tr>
						<th>러닝타임</th>
						<td><input type="number" name="m_time" value="${dto.getM_time() }"></td>
					</tr>
					<tr>
						<th>영화 소개</th>
						<td>
							<textarea rows="7" cols="30" name="m_cont">${dto.getM_cont() }</textarea>						
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" class="btn btn-primary" value="확인">
							<input type="reset" class="btn" value="다시작성">
						</td>
					</tr>
				</c:if>
	
				<c:if test="${empty dto}">
					<tr>
						<td colspan="2" align="center">
							<h3>영화가 존재하지 않습니다</h3>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<button class="btn" onclick="location.href='admin_movie_list.do'">목록으로</button>
						</td>
					</tr>
				</c:if>
			</table>
			</form>
		</div>
		
		<br>
		<hr width="50%" color="blue">
		<br>
		
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>