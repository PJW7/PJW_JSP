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
	   		<h1 class="header-title">영화 목록</h1>
	   </div>
		
		<jsp:include page="../include/admin_top.jsp"/>
		<h5>
			<c:if test="${search_field.equals('m_num')}">
				영화 번호 검색 / 검색어 : ${search_name } 
			</c:if>
			<c:if test="${search_field.equals('m_name')}">
				영화 제목 검색 / 검색어 : ${search_name } 
			</c:if>
			<c:if test="${search_field.equals('m_director')}">
				영화 감독 검색 / 검색어 : ${search_name } 
			</c:if>
		</h5>
		<table class="table table-striped">
			<thead>
				<tr>
					<th style="width: 15%;">번호</th>
					<th style="width: 30%;">제목</th>
					<th style="width: 15%;">감독</th>
					<th style="width: 20%;">개봉일</th>
					<th style="width: 20%;">러닝타임</th>
				</tr>
			</thead>
			<c:set var="m_list" value="${m_list }" />
			<c:if test="${!empty m_list }">
				<c:forEach items="${m_list }" var="dto">
					<tr>
						<td>${dto.getM_num() }</td>
						<td><a href="admin_movie_cont.do?no=${dto.getM_num() }">${dto.getM_name() }</a></td>
						<td>${dto.getM_director() }</td>
						<td>${dto.getM_playdate().substring(0,10) }</td>
						<td>${dto.getM_time() }</td>
						<%-- <td><img src="<%=request.getContextPath() %>${dto.getM_image() }" width="120" height="120"></td> --%>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty m_list }">
				<tr>
					<td colspan="5" align="center">
						<h3>영화가 존재하지 않습니다</h3>
					</td>
				</tr>
			</c:if>
		</table>
		
		<div align="right">
			<input type="button" class="btn btn-primary" value="영화 등록" onclick="location.href='admin_movie_add.do'">
		</div>
		
		<div align="center">
			<c:if test="${!empty r_list }">
				<ul class="pagination">
					<c:if test="${page > blockNum }">
						<li class="page-item"><a class="page-link" href="admin_search.do?page=1&search_field=${search_field }&search_name=${search_name}&table=movie">◀◀</a></li>
						<li class="page-item"><a class="page-link" href="admin_search.do?page=${page - 1 }&search_field=${search_field }&search_name=${search_name}&table=movie">◀</a></li>
					</c:if>
				
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${i == page }">
							<li class="active"><a class="page-link" href="<%=request.getContextPath() %>/admin_search.do?page=${i }&search_field=${search_field }&search_name=${search_name}&table=movie">${i }</a></li>
						</c:if>
						<c:if test="${i != page }">
							<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/admin_search.do?page=${i }&search_field=${search_field }&search_name=${search_name}&table=movie">${i }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${page != allPage }">
						<li class="page-item"><a class="page-link" href="admin_search.do?page=${page + 1 }&search_field=${search_field }&search_name=${search_name}&table=movie">▶</a></li>
						<li class="page-item"><a class="page-link" href="admin_search.do?page=${allPage }&search_field=${search_field }&search_name=${search_name}&table=movie">▶▶</a></li>
					</c:if>
				</ul>
			</c:if>
		</div>
		
		<br>
		<hr width="50%" color="blue">
		<br>
		
		<c:if test="${!empty m_list }">
			<form method="post" action="<%=request.getContextPath() %>/admin_search.do?table=movie">
				<select name="search_field" style="height: 27px">
					<option value="m_num">영화 번호</option>
					<option value="m_name">영화 제목</option>
					<option value="m_director">영화 감독</option>
				</select>
				<input type="text" name="search_name">
				<input type="submit" class="btn-sm btn-primary" value="검색">
			</form>
		</c:if>
		
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>