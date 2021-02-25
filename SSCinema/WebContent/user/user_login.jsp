<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema Login</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/user.css">
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div align="center" class="container">
		<div class="header-user">

			<h1 class="header-title">Login</h1>

		</div>
		<jsp:include page="../include/user_top.jsp" />
		<form action="<%=request.getContextPath()%>/user_login_ok.do" method="post">
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