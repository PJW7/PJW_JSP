<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema Movie</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/user.css">
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div align="center" class="container">
		<div class="header-user">

			<h1 class="header-title">Movie</h1>

		</div>
		<jsp:include page="../include/user_top.jsp" />
	
		<c:set var="list" value="${movielist }" />
		<div class="row">
			<c:forEach items="${list }" var="dto">
				<c:set var="cnt" value="${cnt + 1 }" />
					<div class="col-sm-4">
						<div class="movie_list_cont">
						<a href="<%=request.getContextPath() %>/user_movie_cont.do?no=${dto.getM_num() }">
							<img class="movie_list_img" src="<%=request.getContextPath() %>${dto.getM_image() }">
							<br />
							${dto.getM_name() }
						</a>
						<hr/>
						</div>
					</div>
				<c:if test="${cnt%3 == 0 }">
					</div>
					<div class="row">
				</c:if>
			</c:forEach>
			</tr>
		</div>
		
	
		<jsp:include page="../include/user_bottom.jsp" />
	</div>
</body>
</html>