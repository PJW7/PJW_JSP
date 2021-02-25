<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/admin_style.css?after">
</head>
<body>
	<div class="container" align="center">
		<div class="header-admin">
			<h1 class="header-title">Snack</h1>
		</div>
		<jsp:include page="../include/admin_top.jsp" />
		<div id="foodList">
			<c:set var="list" value="${foodList}" />
			<c:set var="i" value="${0}" />
			<c:forEach var="dto" items="${list}">
				<c:if test="${!empty list}">
					<c:set var="i" value="${i+1}" />
					<div class="panel panel-default foodTable">
							<div class="food">
								<input type="hidden" name="num" value="${dto.getF_num()}">
								<img src=".${dto.getF_image()}" alt="음식 이미지">
								<div>
									<p>${dto.getF_name()}</p>
									<p><fmt:formatNumber value="${dto.getF_price()}" />원</p>
									<p><input type="button" value="삭제" class="btn btn-default"
									 onclick="location.href='admin_food_delete.do?no=${dto.getF_num() }'"></p>
								</div>
							</div>
					</div>
					</c:if>
				<c:if test="${empty list}">
					<div class="panel panel-default">
						<h3>목록 불러오기 실패</h3>
					</div>
				</c:if>
			</c:forEach>
			<br />
			<input type="button" value="추가" class="btn btn-primary"
									 onclick="location.href='admin_food_add.do'">
	
		</div>
		<jsp:include page="../include/admin_bottom.jsp" />
	</div>
</body>
</html>