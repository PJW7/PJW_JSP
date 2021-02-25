<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>SSCinema Join</title>
<!-- <link rel="stylesheet" href="css/userInfo.css"> -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<script type="text/javascript" src="js/user.js"></script>
<style type="text/css">
#user_join_tab {
	width: 95%;
	font-size: 1.5rem;
}
#user_join_tab div{
	padding: 0;
}
#user_join_tab th{
	text-align: center;
	vertical-align: middle;
	width: 25%;
	padding: 1.5rem 0.5rem;
}
#user_join_tab td{
	padding: 1.5rem 0.5rem;
}
#user_join_tab .hyphen{
	width: 3%;
	text-align: center;
}
#tel2, #tel3{
	display: inline-block;
}
#tel{
	width: 120%;
}
#pwd_tab{
	width: 80%;
}
#pwd_tab td{
	padding: 0.5vw 0;
}
#pwd_tab tr td:first-child{
	padding-right: 0.5rem;
	width: 33%;
}
#pwd_tab tr:last-child{
	display: none;
}
@media all and (min-width: 768px) {/* 태블릿 */
	#user_join_tab {
		width: 68%;
	}
	#tel{
		width: 80%;
	}
}

@media all and (min-width: 960px) {/* PC */
	#user_join_tab {
		width: 550px;
	}
	#tel{
		width: 80%;
	}
}
</style>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div class="container" align="center">
		<div class="header-user">
			<h1 class="header-title">Join</h1>

		</div>
		<jsp:include page="../include/user_top.jsp" />
		<form method="post"
			action="<%=request.getContextPath()%>/user_join_ok.do">
			<table id="user_join_tab" class="table table-bordered">
				<tr>
					<th class="active">이름</th>
					<td><div class="col-xs-6"><input type="text" name="name" class="form-control input-sm"></div></td>
				</tr>
				<tr>
					<th class="active">아이디</th>
					<td><div>
							<div class="col-xs-6"><input type="text" name="id" size="7" id="id" class="form-control input-sm"></div> 
							<input type="button" value="중복확인" id="idCheck" class="btn btn-default btn-sm">
						</div> <span id="IDwarning"></span></td>
				</tr>
				<tr>
					<th class="active">연락처</th>
					<td><div id="tel">
							<div class="col-xs-3">
								<select name="tel1" class="form-control input-sm">
									<option value="011"
										<c:if test="${dto.getU_phone().substring(0,3) == '011'}">selected</c:if>>010</option>
									<option value="017"
										<c:if test="${dto.getU_phone().substring(0,3) == '017'}">selected</c:if>>017</option>
									<option value="018"
										<c:if test="${dto.getU_phone().substring(0,3) == '018'}">selected</c:if>>018</option>
									<option value="010"
										<c:if test="${dto.getU_phone().substring(0,3) == '010'}">selected</c:if>>010</option>
								</select>
							</div>
							<div class="col-xs-1 hyphen">-</div>
							<div class="col-xs-3">
								<input type="text" name="tel2" size="4"
									value="${dto.getU_phone().substring(4,8)}"
									class="form-control input-sm">
							</div>
							<div class="col-xs-1 hyphen">-</div>
							<div class="col-xs-3">
								<input name="tel3" size="4"
									value="${dto.getU_phone().substring(9,13)}"
									class="form-control input-sm">
							</div>
						</div></td>
				</tr>
				<tr>
					<th class="active">비밀번호</th>
					<td>
						<table id="pwd_tab">
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="pwd" id="pwd" class="form-control input-sm"></td>
							</tr>
							<tr>
								<td>비밀번호 확인</td>
								<td><input type="password" name="pwdConfirm"
									id="pwdConfirm" class="form-control input-sm"></td>
							</tr>
							<tr>
								<td></td>
								<td><span id="PWDwarning"></span></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="submit" value="가입하기" class="btn btn-default">
		</form>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>
</body>
</html>