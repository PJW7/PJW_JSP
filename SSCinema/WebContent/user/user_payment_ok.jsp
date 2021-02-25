<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema Ticket</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div align="center" class="container">
		<div class="header-user">

			<h1 class="header-title">Ticket</h1>

		</div>
		<jsp:include page="../include/user_top.jsp" />
		<h3>예매 완료 되었습니다.</h3>
		<button class="reservation" onclick="location.href='<%=request.getContextPath()%>/user_reserve_receipt.do'">예매내역 확인</button>
	<jsp:include page="../include/user_bottom.jsp" />
	</div>
</body>
</html>