<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>SSCinema My Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
.mpBtn{
	padding: 30px 0;
}
</style>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div class="container" align="center">
		<div class="header-user">
			<h1 class="header-title">My Page</h1>
		</div>
	<jsp:include page="../include/user_top.jsp" />
	<div id="mpBtns">
		<div class="mpBtn"><button onclick="location='<%=request.getContextPath()%>/user_reserve_receipt.do'" 
			class="btn btn-primary">영화 예매 내역</button></div>
		<div class="mpBtn"><button onclick="location='<%=request.getContextPath()%>/user_food_receipt.do'" 
			class="btn btn-primary">음식 구매 내역</button></div>
		<div class="mpBtn"><button onclick="location='<%=request.getContextPath()%>/user_info.do'" 
			class="btn btn-primary">회원정보</button></div>
	</div>
	<jsp:include page="../include/user_bottom.jsp" />
</div>

</body>
</html>