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
		<jsp:include page="../include/user_top.jsp"/>
		
		<table class="table table-bordered table-hover">
			<thead>
			<tr>
				<th>글번호</th> <th>글제목 </th> <th>조회수</th> <th>작성일자</th>
			</tr>
			</thead>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td> ${dto.getQ_no() }</td>
						<td>
							<c:forEach begin="1" end="${dto.getQ_indent() }">
								 └
							</c:forEach>
							<a href="user_faq_cont.do?no=${dto.getQ_no() }">${dto.getQ_title() }</a>
						</td>
						<td> ${dto.getQ_hit() }</td>
						<td> ${dto.getQ_date().substring(0,10) }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 레코드가 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td align="right" colspan="4">
					<input type="button" value="글쓰기" class="btn btn-default" onclick="location.href='user_faq_write.do'">
				</td>
			</tr>
		</table>
		<div>
			<ul class="pagination">
				<c:if test="${page > block }">
					<li class="page-item"><a class="page-link" href="user_faq_list.do?page=1">◀◀</a></li>
					<li class="page-item"><a class="page-link" href="user_faq_list.do?page=${startBlock - 1 }">◀</a></li>
				</c:if>
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${i == page }">
						<li class="active"><a class="page-link"  href="user_faq_list.do?page=${i }">${i }</a></li>			
					</c:if>
					<c:if test="${i != page }">
						<li class="page-item"><a class="page-link" href="user_faq_list.do?page=${i }">${i }</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${endBlock < allPage }">
					<li class="page-item"><a class="page-link" href="user_faq_list.do?page=${endBlock + 1 }">▶</a></li>
					<li class="page-item"><a class="page-link" href="user_faq_list.do?page=${allPage }">▶▶</a></li>
				</c:if>
			</ul>
		</div>
		<jsp:include page="../include/user_bottom.jsp"/>
	</div>

</body>
</html>