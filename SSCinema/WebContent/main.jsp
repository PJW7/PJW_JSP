<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		
		<div>
			<hr width="50%" color="red">
			<h3>ssc 메인</h3>
			<hr width="50%" color="red">
		</div>
		<br/>
		<hr>
		<a href="<%=request.getContextPath() %>/ssc_user_main.do">회원페이지</a>
		<a href="<%=request.getContextPath() %>/admin_login.do">관리자페이지</a>
	</div>

</body>
</html>