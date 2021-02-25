<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/admin_style.css">
</head>
<body>

	<div align="center">
		<div class="header-admin">
	   		<h1 class="header-title">현재 등록 리스트</h1>
	   </div>

		<jsp:include page="../include/admin_top.jsp"/>
		<h4>현재 등록된 극장 및 상영관</h4>
		<table border="1" cellspacing="0" width="50%">
			<tr>
				<th>극장</th>
				<th>상영관</th>
			</tr>
			<tr>
				<td>
				
				</td>
			</tr>
		</table>
		
		
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>

</body>
</html>