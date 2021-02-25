<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	   		<h1 class="header-title">영화 등록</h1>
	   </div>
		<jsp:include page="../include/admin_top.jsp"/>
		<br>
		
		<div class="movie_add" align="center">
			<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/admin_movie_add_ok.do">
				<table class="table table-bordered" style="width: 100%">
					<tr>
						<th>이름</th>
						<td><input name="m_name"></td>
					</tr>
					<tr>
						<th>감독</th>
						<td><input name="m_director"></td>
					</tr>
					<tr>
						<th>배우</th>
						<td><input name="m_actor"></td>
					</tr>
					<tr>
						<th>장르</th>
						<td><input name="m_genre"></td>
					</tr>
					<tr>
						<th>개봉일</th>
						<td><input type="date" name="m_playdate"></td>
					</tr>
					<tr>
						<th>러닝타임</th>
						<td><input type="number" name="m_time"></td>
					</tr>
					<tr>
						<th>영화 소개</th>
						<td><textarea rows="7" cols="30" name="m_cont"></textarea></td>
					</tr>
					<tr>
						<th>영화 이미지</th>
						<td><input type="file" name="m_image"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" class="btn btn-primary" value="등록">&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>