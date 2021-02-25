<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title>SSCinema Membership Info</title>
<!-- <link rel="stylesheet" href="css/userInfo.css"> -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.js"></script>
<style type="text/css">
#user_info_tab{
	width: 90%;
	font-size: 1.5rem;
	background-color: white;
}
#user_info_tab th, #user_info_tab td{
	text-align: center;
	vertical-align: middle;
	padding: 1.5rem 0.5rem;
}
#user_info_tab .hyphen{
	width: 3%;
	text-align: center;
}
#inp, #user_info_tab div input, #user_info_tab select{
	font-size: 1.5rem;
}
#user_info_tab div{
	padding: 0;
}
#tel{
	width: 120%;
}
#tel2, #tel3{
	display: inline-block;
}
#pwd_tab td{
	padding: 0.5vw 0;
}
#pwd_tab tr td:first-child{
	padding-right: 0.5rem;
}
#tel select, #tel option{
	text-align-last: center;
	padding: 0 0.5vw;
}
@media all and (min-width: 768px) { /* 태블릿 */
	#user_info_tab {
		width: 60%;
		font-size: 1.5rem;
	}
	#user_info_tab th {
		padding: 1.5rem 0.5rem;
	}
	#inp, #user_info_tab div input, #user_info_tab select{
		font-size: 1.5rem;
	}
}

@media all and (min-width: 960px) { /* PC */
	#user_info_tab {
		width: 450px;
		font-size: 1.5rem;
	}
	#user_info_tab th {
		padding: 3% 1%;
	}
	#inp, #user_info_tab div input, #user_info_tab select{
		font-size: 1.5rem;
	}
}
</style>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div class="container" align="center">
		<div class="header-user">

			<h1 class="header-title">My Page</h1>

		</div>
	<jsp:include page="../include/user_top.jsp" />
		<form method="post" action="<%=request.getContextPath()%>/user_info_edit_ok.do" >
		<c:set var="dto" value="${editDTO}"/>
		<input type="hidden" name="num" value="${dto.getU_num()}">
			<table id="user_info_tab" class="table table-bordered">
				<tr>
					<th class="active col-xs-3">이름</th>
					<td><div class="col-xs-6">
					<input class="form-control input-sm" type="text" value="${dto.getU_name()}" readonly></div></td>
				</tr>
				<tr>
					<th class="active">아이디</th>
					<td>
						<div class="col-xs-6"><input type="text" name="id" value="${dto.getU_id()}" readonly class="form-control input-sm" id="inp"></div>
					</td>
				</tr>
				<tr>
					<th class="active">연락처</th>
					<td><div id="tel" class="col-xs-12 col-sm-10">
						<div class="col-xs-3"><select name="tel1" class="form-control input-sm">
							<option value="011"<c:if test="${dto.getU_phone().substring(0,3) == '011'}">selected</c:if>>011</option>
							<option value="017"<c:if test="${dto.getU_phone().substring(0,3) == '017'}">selected</c:if>>017</option>
							<option value="018"<c:if test="${dto.getU_phone().substring(0,3) == '018'}">selected</c:if>>018</option>
							<option value="010"<c:if test="${dto.getU_phone().substring(0,3) == '010'}">selected</c:if>>010</option>
						</select></div>
						<div class="col-xs-1 hyphen">-</div>
						<div class="col-xs-3">
							<input type="text" name="tel2" size="4" value="${dto.getU_phone().substring(4,8)}" class="form-control input-sm">
						</div>
						<div class="col-xs-1 hyphen">-</div>
						<div class="col-xs-3">
							<input name="tel3" size="4" value="${dto.getU_phone().substring(9,13)}" class="form-control input-sm">
						</div>
						</div>
					</td>
				</tr>
				<tr>
					<th class="active">비밀번호<br>변경</th>
					<td>
						<table id="pwd_tab">
							<tr>
								<td>현재 비밀번호</td>
								<td><input type="password"name="pwd" class="form-control input-sm"></td>
							</tr>
							<tr>
								<td>새 비밀번호</td>
								<td><input type="password"name="pwdNew" class="form-control input-sm"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="submit" value="수정" class="btn btn-default">
		</form>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>
</body>
</html>