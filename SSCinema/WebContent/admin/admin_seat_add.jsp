<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<script type="text/javascript" src="js/admin.js"></script>
<style type="text/css">
#seatAdd {
	width: 95%;
}
#seatAdd table th, #seatAdd table td{
	width: 50%;
	text-align: center;
	vertical-align: middle;
	font-size: 1.5rem;
	padding: 1.5rem 0.5rem;
}
@media all and (min-width: 768px) { /* 태블릿 */
	#seatAdd {
		width: 70%;
	}
}

@media all and (min-width: 960px) { /* PC */
	#seatAdd {
		width: 500px;
	}
}
</style>
</head>
<body>

	<div class="container" align="center">
		<div class="header-admin">
	   		<h1 class="header-title">좌석 등록</h1>
	   </div>
		<jsp:include page="../include/admin_top.jsp" />
		<br>
		<div id="seatAdd">
			<form action="<%=request.getContextPath()%>/admin_seat_add_ok.do"
				method="post">
				<c:set var="tList" value="${theaterList}" />
				<c:set var="rList" value="${roomList}" />
				<table class="table table-bordered">
					<tr>
						<th class="active">극장 이름</th>
						<td><div class="col-xs-10"><c:if test="${!empty tList}">
								<select name="theater" id="tList" class="form-control" 
									onchange="tChange(this.value)">
									<option value="">::목록::</option>
									<c:forEach var="dto" items="${tList}">
										<option value="${dto.getT_num()}">${dto.getT_name()}(${dto.getT_num()})</option>
									</c:forEach>
								</select>
							</c:if> <c:if test="${empty tList}">
								<select name="theater" class="form-control">
									<option value="">::목록 없음::</option>
								</select>
							</c:if></div></td>
					</tr>
					<tr>
						<th class="active">상영관</th>
						<td><div class="col-xs-10"><c:if test="${!empty rList}">
								<select name="room" id="rList" class="form-control">
									<option value=''>::목록 없음::</option>
								</select>
							</c:if> <c:if test="${empty rList}">
								<select name="room" class="form-control">
									<option value="">::목록 없음::</option>
								</select>
							</c:if></div></td>
					</tr>
					<tr>
						<th class="active">추가 열 수(A~Z)</th>
						<td><div class="col-xs-6">
							<input type="number" min="1" max="26" name="row" class="form-control input-sm">
						</div></td>
					</tr>
					<tr>
						<th class="active">추가 좌석 수</th>
						<td><div class="col-xs-6">
							<input type="number" min="1" max="20" name="qty" class="form-control input-sm">
						</div></td>
					</tr>
				</table>
				<input type="submit" value="추가하기" class="btn btn-primary">
			</form>
		</div>
		<jsp:include page="../include/admin_bottom.jsp" />
	</div>

</body>
</html>