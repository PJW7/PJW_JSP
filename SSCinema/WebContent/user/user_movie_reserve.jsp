<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>SSCinema My Tickets</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>

<!-- <link rel="stylesheet" href="css/userReceipt.css"> -->
<style type="text/css">
#movieReserList {
	width: 95%;
}
#movieReserList img {
	width: 100%;
	height: auto;
	float: left;
}
#movieReserList .movieTab tr:first-child td{
	width: 120px;
}
#page li {
	margin: 0;
}
#movieReserList .movieTab th, #movieReserList .movieTab td{
	vertical-align: middle;
}
@media all and (min-width: 768px) { /* 태블릿 */
	#movieReserList {
		width: 70%;
	}
	#movieReserList .movieTab tr:first-child td{
		width: 180px;
	}
}

@media all and (min-width: 960px) { /* PC */
	#movieReserList {
		width: 650px;
	}
	#movieReserList .movieTab tr:first-child td{
		width: 180px;
	}
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

		<div id="movieReserList">
			<c:set var="reList" value="${pageList}" />
			<c:if test="${!empty reList}">
				<c:forEach var="dto" items="${reList}">
					<div class="panel panel-default">
					<table class="table movieTab">
						<tr>
							<td rowspan="4"><a href="<%=request.getContextPath()%>/user_movie_cont.do?no=${dto.getM_num()}">
								<img src=".${dto.getM_image()}"></a></td>
							<th>영화 이름: ${dto.getM_name()}</th>
							<th>예매 번호: ${dto.getRe_num()}</th>
						</tr>
						<tr>
							<td>관람 일시: ${dto.getP_date().substring(0,10)}</td>
							<td>상영관:${dto.getR_name()}</td>
						</tr>
						<tr>
							<td>극장: ${dto.getT_name()}</td>
							<td>좌석: ${dto.getS_name()}번</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
							<input type="button" value="예매 취소" class="btn btn-default"
								onclick="location.href='user_reser_cansel.do?renum=${dto.getRe_num()}'">
							</td>
						</tr>
					</table>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty reList}">
				<h3>예매 내역이 없습니다.</h3>
			</c:if>
		</div>

		<ul class="pagination" id="page">
			<c:if test="${page > block}">
				<li><a href="user_reserve_receipt.do?page=${startBlock-1}">◀</a></li>
			</c:if>
			<c:forEach begin="${startBlock}" end="${endBlock}" var="i">
				<c:if test="${i == page}">
					<li><a href="user_reserve_receipt.do?page=${i}"><b>${i}</b></a></li>
				</c:if>
				<c:if test="${i != page}">
					<li><a href="user_reserve_receipt.do?page=${i}">${i}</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${endBlock < allPage}">
				<a href="user_reserve_receipt.do?page=${endBlock+1}">▶</a>
			</c:if>
		</ul>

		<jsp:include page="../include/user_bottom.jsp" />
	</div>

</body>
</html>