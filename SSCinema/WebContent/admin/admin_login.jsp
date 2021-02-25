<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/user.css">
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp"/>
	<div class="container" align="center">
		<div class="header-user">
	   		<h1 class="header-title">관리자 로그인</h1>
	   </div>
		<jsp:include page="../include/user_top.jsp"/>
		<form action="<%=request.getContextPath()%>/admin_login_ok.do" method="post">
			<table class="table table-bordered login-table">
				<tr>
					<th class="active">ID</th>
					<td><input name="id"></td>
				</tr>
				<tr>
					<th class="active">PWD</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn btn-primary" type="submit" value="Login"></td>
				</tr>
			</table>
		</form>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>

</body>
</html>