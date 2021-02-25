<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema FAQ</title>
<link rel="stylesheet" href="css/user.css">
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
		<table class="table table-striped">
			<c:set var="dto" value="${cont }" />
			<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td>${dto.getQ_writer() }</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>${dto.getQ_title() }</td>	
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="20" cols="100" readonly>${dto.getQ_cont() }</textarea></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getQ_hit() }</td>
				</tr>
				<tr>
					<th>작성일자</th>
					<td>${dto.getQ_date() }</td>
				</tr>
			</c:if>
			
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>검색된 레코드가 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" class="btn btn-default" onclick="location.href='user_faq_edit.do?no=${dto.getQ_no() }'" value="글수정">
					 &nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-default" onclick="location.href='user_faq_delete.do?no=${dto.getQ_no() }'" value="글삭제">
					 &nbsp;&nbsp;&nbsp;
					<input type="button" class="btn btn-default" onclick="location.href='user_faq_list.do'" value="전체목록">
				</td>
			</tr>
			
		</table>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>

</body>
</html>