<%@page import="java.util.Date"%>
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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	
	$(function() {

		var t_select = "<option>:::: 극장 선택 ::::</option>";
		var r_select = "<option>:::: 상영관 선택 ::::</option>";
		
		$("#t_num").change(function() {
			if ($("#t_num").val() == "") {
				$("#r_num").find("option").remove().end().append(t_select);
			} else {
				itemChange($(this).val());
			}
		});
		
		function itemChange() {
			$.ajax({
				type: "post",
				url: "<%=request.getContextPath()%>/admin_get_sub.do",
				dataType: "xml",
				data: $("#item_form").serialize(),
				success: function(data) {
					$("#r_num").find("option").remove().end().append(r_select);
					$(data).find('option').each(function(index){
						$("#r_num").append("<option value='" + $(this).find("r_num").text() + "'>" + $(this).find("r_name").text() + "</option>");
					});
				},
				error: function(x, o, e) {
					var msg = "페이지 호출 중 에러 발생 \n" + x.status + " : " + o + " : " + e;
					alert(msg);
				}
			});
		}
		
		$("#m_num").change(function() {
			if ($("#m_num").val() == "") {
				$("#m_image").find("img").remove();
			} else {
				imageChange($(this).val());
			}
		});
		
		function imageChange() {
			$.ajax({
				type: "post",
				url: "<%=request.getContextPath()%>/admin_get_image.do",
				dataType: "xml",
				data: $("#item_form").serialize(),
				success: function(data) {
					$("#m_image").find("img").remove();
					$(data).find('m_info').each(function(index){
						$("#m_image").append("<img src='<%=request.getContextPath()%>" + $(this).find('m_image').text() + "' width='200' height='200'>");
					});
				},
				error: function(x, o, e) {
					var msg = "페이지 호출 중 에러 발생 \n" + x.status + " : " + o + " : " + e;
					alert(msg);
				}
			});
		}

		var date_select = "<option>-</option>";
		
		$("#date_y").change(function() {
			if ($("#date_y").val() == "") {
				$("#date_m").find("option").remove().end().append(date_select);
				$("#date_d").find("option").remove().end().append(date_select);
				$("#date_h").find("option").remove().end().append(date_select);
				$("#date_min").find("option").remove().end().append(date_select);
			} else {
				setMonthBox($(this).val());
				if ($("#date_m").val() != "") {
					setDateBox($(this).val());
				}
			}
		});
		
		function setMonthBox() {
			for (var m = 1; m <= 12; m++) {
				$("#date_m").append("<option value='" + m + "'>" + m + "</option>");
			}
		}
		
		$("#date_m").change(function() {
			if ($("#date_m").val() == "") {
				$("#date_d").find("option").remove().end().append(date_select);
				$("#date_h").find("option").remove().end().append(date_select);
				$("#date_min").find("option").remove().end().append(date_select);
			} else {
				setDateBox($(this).val());
			}
		});
		
		function setDateBox() {
			var year = $("#date_y").val();
			var month = $("#date_m").val();
			
			switch (month) {
			case "1":
			case "3":
			case "5":
			case "7":
			case "8":
			case "10":
			case "12":
				for (var d = 1; d <= 31; d++) {
					$("#date_d").append("<option value='" + d + "'>" + d + "</option>");
				}
				break;
			case "4":
			case "6":
			case "9":
			case "11":
				for (var d = 1; d <= 30; d++) {
					$("#date_d").append("<option value='" + d + "'>" + d + "</option>");
				}
				break;
			case "2":
				if (year % 4 == 0) {
					if (year % 100 == 0 && year % 400 != 0) {
						for (var d = 1; d <= 28; d++) {
							$("#date_d").append("<option value='" + d + "'>" + d + "</option>");
						}
						break;
					} else {
						for (var d = 1; d <= 29; d++) {
							$("#date_d").append("<option value='" + d + "'>" + d + "</option>");
						}
						break;
					}
				} else {
					for (var d = 1; d <= 28; d++) {
						$("#date_d").append("<option value='" + d + "'>" + d + "</option>");
					}
					break;
				}
			}
		} // setDataBox() end
		
		$("#date_d").change(function() {
			if ($("#date_d").val() == "") {
				$("#date_h").find("option").remove().end().append(date_select);
				$("#date_min").find("option").remove().end().append(date_select);
			} else {
				setTimeBox($(this).val());
			}
		});
		
		function setTimeBox() {
			for (var h = 1; h <= 23; h++) {
				$("#date_h").append("<option value='" + h + "'>" + h + "</option>");
			}
			for (var min = 0; min <= 60; min += 10) {
				$("#date_min").append("<option value='" + min + "'>" + min + "</option>");
			}
		}
		
	});
	
</script>
<link rel="stylesheet" href="css/admin_style.css?after5">
</head>
<body>
	
	<div class="container" align="center">
		<div class="header-admin">
	   		<h1 class="header-title">상영일정 등록</h1>
	   </div>
		
		<jsp:include page="../include/admin_top.jsp"/>
		<br>
		
		<c:set var="m_list" value="${m_list }" />
		<c:set var="t_list" value="${t_list }" />
		<form method="post" id="item_form" action="<%=request.getContextPath() %>/admin_playing_add_ok.do">
			<table class="table table-bordered">
				<c:if test="${!empty m_list && !empty t_list}">
					<tr>
						<th>극장 지점 선택</th>
						<td>
							<select name="t_num" id="t_num">
								<option value="">:::: 극장 선택 ::::</option>
								<c:forEach items="${t_list }" var="t_dto">
									<option value="${t_dto.getT_num() }">${t_dto.getT_name() }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>상영관 선택</th>
						<td>
							<select name="r_num" id="r_num">
								<option>:: 극장 지점을 선택해주세요 ::</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>영화 선택</th>
						<td>
							<select name="m_num" id="m_num">
								<option value="">:::: 영화 선택 ::::</option>
								<c:forEach items="${m_list }" var="m_dto">
									<option value="${m_dto.getM_num() }">${m_dto.getM_name() }</option>
								</c:forEach>
							</select>
							<br>
							<span id="m_image"></span>
						</td>
					</tr>
					<tr>
						<th>날짜 선택</th>
						<td>
							<select name="date_y" id="date_y">
								<option value="">-</option>
								<c:set var="now" value="<%=new Date() %>" />
								<c:forEach begin="${now.getYear() + 1900 }" end="${now.getYear() + 1901 }" var="y">
									<option value="${y }">${y }</option>
								</c:forEach>
							</select>년&nbsp;
							<select name="date_m" id="date_m">
								<option value="">-</option>
							</select>월&nbsp;
							<select name="date_d" id="date_d">
								<option value="">-</option>
							</select>일&nbsp;
							<select name="date_h" id="date_h">
								<option value="">-</option>
							</select>시&nbsp;
							<select name="date_min" id="date_min">
								<option value="">-</option>
							</select>분
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="btn btn-primary" type="submit" value="등록">&nbsp;&nbsp;&nbsp;
							<input class="btn btn-primary" type="reset" value="취소">
						</td>
					</tr>
				</c:if>
				<c:if test="${empty m_list || empty t_list}">
					<tr>
						<td colspan="2" align="center">
							<h3>존재하지 않는 레코드가 있습니다</h3>
						</td>
					</tr>
				</c:if>
			</table>
		</form>
		<jsp:include page="../include/admin_bottom.jsp"/>
	</div>
	
</body>
</html>