<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<jsp:include page="../include/user_top.jsp"/>
		<c:set var="p" value="${p_num }"/>
		<c:set var="dto" value="${info }"/>
		<form method="post" action="<%=request.getContextPath() %>/user_movie_payment.do">
			<input type="hidden" name="p_num" value="${p }">
			<input type="hidden" name="s_name" value="${dto.getS_name() }">
			<table class="table table-striped">
				<caption>내역 확인</caption>
				<tr>
					<td>
						제목
					</td>
					<td>
						${dto.getM_name() }
					</td>
				</tr>
				<tr>
					<td>
						극장
					</td>
					<td>
						${dto.getT_name() }
					</td>
				</tr>
				<tr>
					<td>
						상영관
					</td>
					<td>
						${dto.getR_name() }
					</td>
				</tr>
				<tr>
					<td>
						상영일
					</td>
					<td>
						${dto.getP_date().substring(0,16) }
					</td>
				</tr>
				<tr>
					<td>
						좌석번호
					</td>
					<td>
						${dto.getS_name() }
					</td>
				</tr>
				<tr>
					<td>
						금액
					</td>
					<td>
						10,000원
					</td>
				</tr>
			</table>
			<input class="reservation" type="submit" value="예매하기">
		</form>
		<jsp:include page="../include/user_bottom.jsp"/>
	</div>
</body>
</html>