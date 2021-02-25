<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema FAQ</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div align="center" class="container">
		<div class="header-user">

			<h1 class="header-title">F A Q</h1>

		</div>
		<jsp:include page="../include/user_top.jsp" />
		<c:set var="dto" value="${edit }" />
		<div class="table-responsive">
		<form method="post" action="<%=request.getContextPath() %>/user_faq_edit_ok.do">
			<input type="hidden" name="no" value="${dto.getQ_no() }">
			<table  class="table table-striped">
			<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td><input name="writer" value="${dto.getQ_writer() }" readonly></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input name="title" value="${dto.getQ_title() }"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="20" cols="100" name="content">${dto.getQ_cont() }</textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input class="btn btn-default" type="submit" value="글수정">
						 &nbsp;&nbsp;&nbsp;
						<input class="btn btn-default" type="reset" value="취소">
					</td>
				</tr>
				</c:if>
				<c:if test="${empty dto }">
					<tr>
						<td colspan="2">
							검색된 레코드가 없습니다.
						</td>
					</tr>
				</c:if>
			</table>
		</form>
		</div>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>

</body>
</html>