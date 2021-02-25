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
<link rel="stylesheet" href="css/admin_style.css?after4">
</head>
<body>
	
	<div class="container" align="center">
		<div class="header-admin">
	   		<h1 class="header-title">상영관 등록</h1>
	   </div>
		<jsp:include page="../include/admin_top.jsp"/>
		<br>
		
		<c:set var="list" value="${t_list }" />
		<div class="room_add" align="center">
			<form method="post" action="<%=request.getContextPath() %>/admin_room_add_ok.do">
				<table class="table table-bordered" style="width: 100%">
					<c:if test="${!empty list }">
						<tr>
							<td class="td1">극장 지점 선택</td>
							<td class="td2">
								<select name="t_num">
									<c:forEach items="${list }" var="dto">
										<option value="${dto.getT_num() }">${dto.getT_name() }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td1">상영관 이름</td>
							<td class="td2"><input name="r_name"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" class="btn btn-primary" value="등록">&nbsp;&nbsp;&nbsp;
								<input type="reset" class="btn" value="취소">
							</td>
						</tr>
					</c:if>
					<c:if test="${empty list }">
						<tr>
							<td colspan="2" align="center">
								<h3>극장이 존재하지 않습니다</h3>
							</td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>