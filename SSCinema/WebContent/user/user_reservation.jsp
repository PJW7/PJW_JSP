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
		var m = "";
		var t = "";
		var p = "";
		$("#seat").css("background-color","grey");
		$(".movie").click(function (){
			$(".movie").css("border","1px solid black");
			m = $(this).val();
			$(this).css("border","2px solid red");
			$(".playing_info option").remove();
			$(".playing_info").append("<option>극장을 선택해 주세요</option>");
			$("#seat").attr("disabled",true);
			$("#seat").css("background-color","grey");
			$.ajax({
				ContentType:'application/x-www-form-urlencoded;charset=UTF-8',
				url: "/SSCinema/user_theater_list.do",		
				type: "post",
				data: {m_num: $(this).val()},	
				success: function(data){	
					$(".theater option").remove();
					$(".theater").append("<option>:::::::극장 선택:::::::</option>");
					$(".theater").append(data);
				},
				error: function() {	
					alert("에러가 발생했습니다.");
				},
				complete: function() {	
					//alert("작업을 완료했습니다.");
				}
			});
		});
		$(".theater").change(function (){
			$("#seat").attr("disabled",true);
			$("#seat").css("background-color","grey");
			t = $(".theater option:selected").attr("value");
			$.ajax({
				ContentType:'application/x-www-form-urlencoded;charset=UTF-8',
				url: "/SSCinema/user_playing_list.do",		
				type: "post",
				data: {t_num: $(".theater option:selected").attr("value"),
						m_num: m},		
				success: function(data){		
					$(".playing_info option").remove();
					$(".playing_info").append("<option align='center'>:::::::일정 선택:::::::</option>");
					$(".playing_info").append(data);
				},
				error: function() {			
					alert("에러가 발생했습니다.");
				},
				complete: function() {		
					//alert("작업을 완료했습니다.");
				}
			});
		});
		$(".playing_info").change(function (){
			$("#seat").attr("disabled",false);
			$("#seat").css("background-color","red");
			p = $(".playing_info option:selected").attr("value");
			
		});
		$("#seat").click(function(){
			location.href="/SSCinema/user_seat_list.do?p_num="+p+"&t_num="+t+"&m_num="+m;
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
		<c:set var="movielist" value="${movielist }" />
		<div class="row">
			<div class="moviechoice col-sm-4">
				<h3>영화 선택</h3>
				<table>
					<tr>
						<c:forEach items="${movielist }" var="dto">
							<c:set var="cnt" value="${cnt + 1 }" />
							<td>
								<button type="button" class="movie" value="${dto.getM_num() }">
									<img src="<%=request.getContextPath() %>${dto.getM_image() }" width="100" height="130" >
								</button>
							</td>
							<c:if test="${cnt%3 == 0 }" >
								</tr>
								<tr>
							</c:if>
						</c:forEach>
							</tr>
				</table>
			</div>
			
			<div class="theaterchoice col-sm-4" align="center">
				<h3>극장 선택</h3>
				<select class="theater">
					<option>영화를 선택해 주세요</option>
				</select>
			</div>
			<div class="playingchoice col-sm-4" align="center">
				<h3>일정 선택</h3>
				<select class="playing_info">
					<option>극장을 선택해 주세요</option>
				</select>
			</div>
		</div>
		<hr />
		<div>
			<button id="seat" class="reservation" disabled="disabled">좌석선택</button>
		</div>
		<jsp:include page="../include/user_bottom.jsp" />
	</div>
</body>
</html>