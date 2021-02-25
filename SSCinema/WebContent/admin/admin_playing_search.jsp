<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/admin_style.css?after">
</head>
<body>

	<div class="container" align="center">
		<div class="header-admin">
	   		<h1 class="header-title">상영일정 목록</h1>
	   </div>
		
		<jsp:include page="../include/admin_top.jsp"/>
		<h5>
			<c:if test="${search_field.equals('p_num')}">
				상영영화 번호 검색 / 검색어 : ${search_name } 
			</c:if>
			<c:if test="${search_field.equals('p_m_num')}">
				영화 번호 검색 / 검색어 : ${search_name } 
			</c:if>
			<c:if test="${search_field.equals('p_t_num')}">
				극장 번호 검색 / 검색어 : ${search_name } 
			</c:if>
			<c:if test="${search_field.equals('p_r_num')}">
				상영관 번호 검색 / 검색어 : ${search_name } 
			</c:if>
		</h5>
		<table class="table table-striped">
			<thead>
				<tr>
					<th style="width: 20%;">상영영화 번호</th>
					<th style="width: 16%;">영화 번호</th>
					<th style="width: 16%;">극장 번호</th>
					<th style="width: 20%;">상영관 번호</th>
					<th style="width: 18%;">상영일자</th>
					<th style="width: 10%;">삭제</th>
				</tr>
			</thead>
			<c:set var="p_list" value="${p_list }" />
			<c:if test="${!empty p_list }">
				<c:forEach items="${p_list }" var="dto">
					<tr>
						<td>${dto.getP_num() }</td>
						<td>${dto.getM_num_fk() }</td>
						<td>${dto.getT_num_fk() }</td>
						<td>${dto.getR_num_fk() }</td>
						<td>${dto.getP_date().substring(0,16) }</td>
						<td>
							<input type="button" class="btn btn-danger" value="삭제" onclick="location.href='admin_delete.do?num=${dto.getP_num() }&name=p'">
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty p_list }">
				<tr>
					<td colspan="6" align="center">
					</td>
				</tr>
			</c:if>
		</table>
		
		<div align="right">
			<input type="button" class="btn btn-primary" value="일정 등록" onclick="location.href='admin_playing_add.do'">
		</div>
		
		<div align="center">
			<c:if test="${!empty r_list }">
				<ul class="pagination">
					<c:if test="${page > blockNum }">
						<li class="page-item"><a class="page-link" href="admin_search.do?page=1&search_field=${search_field }&search_name=${search_name}&table=playing">◀◀</a></li>
						<li class="page-item"><a class="page-link" href="admin_search.do?page=${page - 1 }&search_field=${search_field }&search_name=${search_name}&table=playing">◀</a></li>
					</c:if>
				
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${i == page }">
							<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/admin_search.do?page=${i }&search_field=${search_field }&search_name=${search_name}&table=playing">${i }</a><li>
						</c:if>
						<c:if test="${i != page }">
							<li class="page-item"><a class="page-link" href="<%=request.getContextPath() %>/admin_search.do?page=${i }&search_field=${search_field }&search_name=${search_name}&table=playing">${i }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${page != allPage }">
						<li class="page-item"><a class="page-link" href="admin_search.do?page=${page + 1 }&search_field=${search_field }&search_name=${search_name}&table=playing">▶</a></li>
						<li class="page-item"><a class="page-link" href="admin_search.do?page=${allPage }&search_field=${search_field }&search_name=${search_name}&table=playing">▶▶</a></li>
					</c:if>
				</ul>
			</c:if>
		</div>
		
		<br>
		<hr width="50%" color="blue">
		<br>
		
		<c:if test="${!empty p_list }">
			<form method="post" action="<%=request.getContextPath() %>/admin_search.do?table=playing">
				<select name="search_field" style="height: 27px">
					<option value="p_num">상영영화 번호</option>
					<option value="p_m_num">영화 번호</option>
					<option value="p_t_num">극장 번호</option>
					<option value="p_r_num">상영관 번호</option>
				</select>
				<input type="text" name="search_name">
				<input type="submit" class="btn-sm btn-primary" value="검색">
			</form>
		</c:if>
		
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
	
</body>
</html>