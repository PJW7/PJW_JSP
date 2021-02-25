<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema FAQ</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div align="center" class="container">
		<div class="header-user">

			<h1 class="header-title">F A Q</h1>

		</div>
		<jsp:include page="../include/user_top.jsp"/>
		<form method="post" action="<%=request.getContextPath() %>/user_faq_write_ok.do">
			<table  class="table table-striped">
				<tr>
					<th>작성자</th>
					<td><input name="writer"></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input name="title"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="20" cols="100" name="content"></textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="btn btn-default" type="submit" value="글쓰기">
						 &nbsp;&nbsp;&nbsp;
						<input class="btn btn-default" type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
		<jsp:include page="../include/user_bottom.jsp"/>
	</div>
</body>
</html>