<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>SSCinema Snack</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/userFoodList.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/user.js"></script>
<style type="text/css">
.fViewTable{
	width: 90%;
	overflow: hidden;
	text-align: center;
	font-size: 3.5vw;
}
.fViewTable tr:first-child td:first-child{
	 width: 55%;
}
.fViewTable tr:last-child td:last-child{
	 padding-bottom:30px;
}
.fViewTable img{
	width: 100%;
	height: auto;
}
@media all and (min-width: 768px) {/* 태블릿 */
	.fViewTable{
		width: 65%;
		font-size: 2vw;
	}
}

@media all and (min-width: 960px) {/* PC */
	.fViewTable{
		width: 650px;
		font-size: 1.5rem;
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
		<form action="<%=request.getContextPath()%>/user_food_buy.do"
			method="post">
			<input type="hidden" name="num" value="${food.getF_num()}">
			<div class="fViewTable panel panel-default">
				<table>
					<tr>
						<td>
							<img src=".${food.getF_image()}" alt="음식 이미지" width="360" height="360">
						</td>
						<td>
							<p>${food.getF_name()}- ${food.getF_price()}원</p>
							<p>수량: <input type="number" min="1" max="10" name="qty" id="qty" value="1"
								onclick="qtyClick();">개 <input type="hidden" name="price"
								id="price" value="${food.getF_price()}"></p>
							<p>총액:<span id="total_price"><fmt:formatNumber
								value="${food.getF_price()}" />원</span></p>
						</td>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="구매" class="btn btn-default">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>

</body>
</html>