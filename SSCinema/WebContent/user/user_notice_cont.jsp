<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema Notice</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/admin_style.css">
<!-- <script
  src="http://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script> -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">

	table {
		background-color: white;}
		th {
    background-color: gray;
  }	
</style>
</head>
<body>
	<jsp:include page="../include/user_login_include.jsp" />
	<div class="container" align="center">
	   <div class="header-user">

			<h1 class="header-title">Notice</h1>

		</div>

	   <jsp:include page="../include/user_top.jsp" />
	   <table class="table table-striped" >
	      <c:set var="dto" value="${cont }" />
	      <c:if test="${!empty dto }">
	         
	         <tr>
	            <th>글제목</th>
	            <td> ${dto.getN_title() } </td>
	         </tr>
	         <tr>
	            <th>글내용</th>
	            <td>
	               <textarea rows="20" cols="100" readonly>${dto.getN_cont() }</textarea>
	            </td>
	         </tr>
	         <tr>
	            <th>조회수</th>
	            <td> ${dto.getN_hit() } </td>
	         </tr>
	         <tr>
	            <th>작성일자</th>
	            <td> ${dto.getN_date() } </td>
	         </tr>
	         <tr>
	            <td colspan="2" align="center">
	            	<button class="btn btn-default listbt" onclick="location.href='user_notice_list.do'">목록으로</button>
	            </td>
	         </tr>
	      </c:if>
	      
	      <c:if test="${empty dto }">
	         <tr>
	            <td colspan="2" align="center">
	               <h3>검색된 레코드가 없습니다.</h3>
	         	</td>
	         </tr>
	      </c:if>
	      
	     
	   </table>
	   <jsp:include page="../include/user_bottom.jsp" />
	</div>
	
</body>
</html>