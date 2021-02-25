<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema Ticket</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/user.css">
<script type="text/javascript" >
	
	$(function(){
		$(".seatbt").css("border","1px solid black");
		var s = "";
		$(".reservation").css("background-color","grey");
		$(".seatbt").click(function (){
			$(".seatbt").css("border","1px solid black");
			s = $(this).val();
			$(this).css("border","1px solid red");
			$("#seat").attr("disabled",true);
			$(".reservation").attr("disabled",false);
			$(".reservation").css("background-color","red");
			$("#s_name").attr("value",s);
		});
		
	});

</script>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div align="center" class="container">
		<div class="header-user">

			<h1 class="header-title">Ticket</h1>

		</div>
		<jsp:include page="../include/user_top.jsp" />
	
		<div width="80%" class="table-responsive">
			<c:set var="p_num" value="${p_num }" />
			<c:set var="t_num" value="${t_num }" />
			<c:set var="m_num" value="${m_num }" />
			<c:set var="scount" value="${scount }" />
			
			
			<table style="background-color: grey" class="table choice-seat">
			<c:set var="temp" value="${temp }" />
				<caption>좌석배치도</caption>
				<tr>
					<th colspan="${scount }">screen</th>
				</tr>
				<tr>
					<c:forEach items="${temp }" var="dto">
						<c:set var="cnt" value="${cnt + 1 }" />
						<c:if test="${dto.getStatus().equals('X') }">
							<td align="center">
								<button class="seatbt" value="${dto.getS_name() }" disabled="disabled">&nbsp;${dto.getStatus() }&nbsp;</button>
							</td>
						</c:if>
						<c:if test="${dto.getStatus().equals('O') }">
							<td align="center">
								<button class="seatbt" value="${dto.getS_name() }">${dto.getS_name() }</button>
							</td>
						</c:if>
						<c:if test="${(cnt % scount) == 0 }">
							</tr>
							<tr>
						</c:if>
					</c:forEach>
				</tr>
			</table>
			<br/>
			<form method="post" action="<%=request.getContextPath() %>/user_seat_choice.do">
				<table>
					<tr>
						<td>
							<input type="hidden" name="p_num" value="${p_num }">
							<input type="hidden" name="t_num" value="${t_num }">
							<input type="hidden" name="m_num" value="${m_num }">
							<input type="hidden" name="s_name" id="s_name">
							<button type="submit" class="reservation" disabled="disabled">좌석예매</button>
						</td>
					</tr>
				</table>
			</form>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>
</body>
</html>