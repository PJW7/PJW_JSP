<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>SSCinema My Page</title>
<!-- <link rel="stylesheet" href="css/userReceipt.css"> -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>

<style type="text/css">
#foodBuyList {
	width: 95%;
}
.foodRecTable {
	display: inline-block;
	width: 90%;
	background-color: rgb(255, 224, 140); 
	overflow : hidden;
	margin: 3%;
	overflow: hidden;
	font-size: 3.5vw;
}

.foodRecTable img {
	height: 100%;
	width: 100%;
	float: left;
}

.foodRecTable>div {
	display: table;
}

.foodRecTable>div div {
	display: table-cell;
	vertical-align: middle;
	width: 45%;
}

.h {
	display: none;
}
#page li{
	margin: 0;
}

@media all and (min-width: 768px) {/* 태블릿 */
	#foodBuyList {
		padding: 1px;
		display: inline-block;
		width: 95%;
	}
	.h {
		visibility: hidden;
	}
	.foodRecTable {
	display: inline-block;
	width: 43%;
	margin: 3%;
	font-size: 2vw;
	}
}

@media all and (min-width: 960px) {/* PC */
	#foodBuyList {
		padding: 1px;
		display: inline-block;
		width: 800px;
	}
	.foodRecTable {
	display: inline-block;
	width: 43%;
	margin: 3%;
	overflow: hidden;
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

			<h1 class="header-title">My Page</h1>

		</div>
		<jsp:include page="../include/user_top.jsp" />
		<div>
			<div id="foodBuyList">
				<c:set var="i" value="${0}" />
				<c:set var="buyList" value="${fBuyList}" />
				<c:set var="fList" value="${foodList}" />
				<c:if test="${!empty buyList}">
					<c:forEach var="dto" items="${buyList}">
						<c:set var="i" value="${i+1}" />
						<div class="foodRecTable panel panel-default">
							<div>
								<img src=".${dto.getF_image()}" alt="음식 이미지">
								<div>
									<p>메뉴 이름: ${dto.getF_name()}</p>
									<p>갯수: ${dto.getB_count()}개</p>
									<p>가격: <fmt:formatNumber value="${dto.getB_price()}"/>원</p>
									<input type="button" value="구매 취소" class="btn btn-warning"
										onclick="location.href='user_fbuy_cancel.do?bnum=${dto.getB_num()}'">
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<c:if test="${empty buyList}">
					<h3>구매한 메뉴가 없습니다.</h3>
				</c:if>
				<c:if test="${i%2 == 1}">
					<div class="foodRecTable h">
						<div>
							<H3>비어있는 테이블</H3>
						</div>
					</div>
				</c:if>
			</div>
		</div>

		<ul class="pagination" id="page">
			<c:if test="${page > block}">
				<li><a href="user_food_receipt.do?page=${startBlock-1}">◀</a></li>
			</c:if>
			<c:forEach begin="${startBlock}" end="${endBlock}" var="i">
				<c:if test="${i == page}">
					<li><a href="user_food_receipt.do?page=${i}"><b>${i}</b></a></li>
				</c:if>
				<c:if test="${i != page}">
					<li><a href="user_food_receipt.do?page=${i}">${i}</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${endBlock < allPage}">
				<a href="user_food_receipt.do?page=${endBlock+1}">▶</a>
			</c:if>
		</ul>


		<jsp:include page="../include/user_bottom.jsp" />
	</div>

</body>
</html>