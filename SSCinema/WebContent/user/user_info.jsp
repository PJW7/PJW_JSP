<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>SSCinema Membership Info</title>
<!-- <link rel="stylesheet" href="css/userInfo.css"> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<style type="text/css">
#user_info_tab {
	width: 90%;
	font-size: 1.5rem;
	background-color: white;
}

#user_info_tab th {
	text-align: center;
	padding: 1.5rem 0.5rem;
	vertical-align: middle;
}

#user_info_tab td {
	vertical-align: middle;
}

@media all and (min-width: 768px) { /* 태블릿 */
	#user_info_tab {
		width: 60%;
		font-size: 1.5rem;
	}
	#user_info_tab th {
		padding: 1.5rem 0.5rem;
	}
}

@media all and (min-width: 960px) { /* PC */
	#user_info_tab {
		width: 550px;
		font-size: 1.5rem;
	}
	#user_info_tab th {
		padding: 3% 1%;
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
		<form method="post"
			action="<%=request.getContextPath()%>/user_info_edit.do">
			<c:set var="dto" value="${userInfo}" />
			<input type="hidden" name="num" value="${dto.getU_num()}">
			<div >
				<table class="table table-bordered" id="user_info_tab">
					<tr>
						<th class="active">이름</th>
						<td>${dto.getU_name()}</td>
					</tr>
					<tr>
						<th class="active">아이디</th>
						<td>${dto.getU_id()}</td>
					</tr>
					<tr>
						<th class="active">연락처</th>
						<td>${dto.getU_phone()}</td>
					</tr>
					<tr>
						<th class="active">마일리지</th>
						<td><fmt:formatNumber value="${dto.getU_mile()}"/>점</td>
					</tr>
				</table>
				<input type="submit" value="정보 수정" class="btn btn-default">
			</div>
		</form>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>

</body>
</html>