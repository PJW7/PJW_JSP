<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link rel="stylesheet" href="css/admin_style.css?after2">
</head>
<body>
	
	<div class="container" align="center">
		<div class="header-admin">
	   		<h1 class="header-title">극장 등록</h1>
	   </div>
		<jsp:include page="../include/admin_top.jsp"/>
		<br>
		
		<div class="theater_add" align="center">
			<form method="post" action="<%=request.getContextPath() %>/admin_theater_add_ok.do">
				<table class="table-bordered" style="width: 100%">
					<tr>
						<td class="td1">극장 이름</td>
						<td class="td1"><input class="input1" name="t_name"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" class="btn btn-primary" value="등록">&nbsp;&nbsp;&nbsp;
							<input type="reset" class="btn" value="취소">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>