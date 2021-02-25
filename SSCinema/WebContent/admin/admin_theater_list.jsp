<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/admin_style.css?after">
</head>
<body>

	<div class="container" align="center">
		<div class="header-admin">
	   		<h1 class="header-title">극장 목록</h1>
	   </div>
		<jsp:include page="../include/admin_top.jsp"/>
		<br>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th style="width: 45%;">극장 번호</th>
					<th style="width: 45%;">극장 이름</th>
					<th style="width: 10%;">삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="t_list" value="${t_list }" />
				<c:if test="${!empty t_list }">
					<c:forEach items="${t_list }" var="dto">
						<tr>
							<td>${dto.getT_num() }</td>
							<td>${dto.getT_name() }</td>
							<td>
								<input type="button" class="btn btn-danger" value="삭제" onclick="location.href='admin_delete.do?num=${dto.getT_num() }&name=t'">
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty t_list }">
					<tr>
						<td colspan="3" align="center">
							<h3>극장이 존재하지 않습니다</h3>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<div align="right">
			<input type="button" class="btn btn-primary" value="극장 등록" onclick="location.href='admin_theater_add.do'">
		</div>
		
		<div align="center">
			<c:if test="${!empty t_list }">
				<ul class="pagination">
					<c:if test="${page > blockNum }">
						<li class="page-item"><a class="page-link" href="admin_theater_list.do?page=1">◀◀</a></li>
						<li class="page-item"><a class="page-link" href="admin_theater_list.do?page=${page - 1 }">◀</a></li>
					</c:if>
				
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${i == page }">
							<li class="active"><a class="page-link" href="<%=request.getContextPath() %>/admin_theater_list.do?page=${i }">${i }</a></li>
						</c:if>
						<c:if test="${i != page }">
							<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/admin_theater_list.do?page=${i }">${i }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${page != allPage }">
						<li class="page-item"><a class="page-link" href="admin_theater_list.do?page=${page + 1 }">▶</a></li>
						<li class="page-item"><a class="page-link" href="admin_theater_list.do?page=${allPage }">▶▶</a></li>
					</c:if>
				</ul>
			</c:if>
		</div>
		
		<br>
		<hr width="50%" color="blue">
		<br>
		
		<c:if test="${!empty t_list }">
			<form method="post" action="<%=request.getContextPath() %>/admin_search.do?table=theater">
				<select name="search_field" style="height: 27px">
					<option value="t_num">극장 번호</option>
					<option value="t_name">극장 이름</option>
				</select>
				<input type="text" name="search_name">
				<input type="submit" class="btn-sm btn-primary" value="검색">
			</form>
		</c:if>
		
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>