<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<title>SSCinema Snack</title>
<!-- <link rel="stylesheet" href="css/userFoodList.css"> -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<script type="text/javascript" src="js/user.js"></script>
<style type="text/css">
#foodList {
	width: 95%;
}
.foodTable {
	width: 80%;
	display: inline-block;
	margin: 3%;
	font-size: 3.5vw;
	over
}
.foodTable img {
	height: auto;
	width: 100%;
	float: left;
}
.food {
	display: table;
}
.food>div {
	display: table-cell;
	vertical-align: middle;
	width: 45%;
}
.h {
	display: none;
}
@media all and (min-width: 768px) { /* 태블릿 */
	#foodBuyList {
		
		width: 95%;
	}
	.foodTable img {
		height: 100%;
		width: 100%;
	}
	.foodTable {
		display: inline-block;
		width: 29%;
		margin: 1%;
		font-size: 2vw;
	}
	.h {
		visibility: hidden;
	}
	.food, .food>div{
		display: block;
	}
}

@media all and (min-width: 960px) { /* PC */
	#foodList{
		width: 960px;
	}
	.foodTable img {
		height: 100%;
		width: 100%;
	}
	.foodTable {
		display: inline-block;
		width: 210px;
		margin: 1%;
		font-size: 1.5rem;
	}
	.h {
		visibility: hidden;
	}
}
</style>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div class="container" align="center">
		<div class="header-user">
			<h1 class="header-title">Snack</h1>
		</div>
		<jsp:include page="../include/user_top.jsp" />
		<div id="foodList">
			<c:set var="list" value="${foodList}" />
			<c:set var="i" value="${0}" />
			<c:forEach var="dto" items="${list}">
				<c:if test="${!empty list}">
					<c:set var="i" value="${i+1}" />
					<div class="panel panel-default foodTable">
							<form action="<%=request.getContextPath()%>/user_food_view.do"
								method="post">
							<div class="food">
								<input type="hidden" name="num" value="${dto.getF_num()}">
								<img src=".${dto.getF_image()}" alt="음식 이미지">
								<div>
									<p>${dto.getF_name()}</p>
									<p><fmt:formatNumber value="${dto.getF_price()}" />원</p>
									<p><input type="submit" value="구매하기" class="btn btn-default"></p>
								</div>
							</div>
						</form>
					</div>
				</c:if>
				<c:if test="${empty list}">
					<div class="panel panel-default">
						<h3>목록 불러오기 실패</h3>
					</div>
				</c:if>
			</c:forEach>
			<c:if test="${i%4 != 0 || i<4 }">
				<c:forEach var="j" begin="1" end="${4 - i%4}">
					<div class="foodTable h">
						<div>
							<H3>비어있는 테이블</H3>
						</div>
					</div>
				</c:forEach>
			</c:if>
			
		</div>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>
</body>
</html>