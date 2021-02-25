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
	   		<h1 class="header-title">메뉴 등록</h1>
	   </div>
		<jsp:include page="../include/admin_top.jsp"/>
		<br>
		
		<div class="movie_add" align="center">
			<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/admin_food_add_ok.do">
				<table class="table table-bordered" style="width: 100%">
					<tr>
						<th>이름</th>
						<td><input name="f_name"></td>
					</tr>
					<tr>
						<th>가격</th>
						<td><input type="number" name="f_price"></td>
					</tr>
					<tr>
						<th>영화 이미지</th>
						<td><input type="file" name="f_image"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" class="btn btn-primary" value="등록">&nbsp;&nbsp;&nbsp;
							<input type="reset" class="btn btn-default" value="취소">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>