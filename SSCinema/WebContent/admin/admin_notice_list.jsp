<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<link rel="stylesheet" href="css/admin_style.css">
<script
  src="http://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<style type="text/css">

	table {
		background-color: white;}
		th {
    background-color: gray;
  }	
</style>
<body>
<div class="container" align="center">
	   <div class="header-admin">
	   		<h1 class="header-title">공지사항</h1>
	   </div>

	   <jsp:include page="../include/admin_top.jsp" />
	   <table class="table table-striped">
	      <tr>
	         <th>글번호</th> <th>글제목</th> <th>조회수 <th>작성일자</th>
	      </tr>
	      <c:set var="list" value="${List }" />
	      <c:if test="${!empty list }">
	         <c:forEach items="${list }" var="dto">
	            <tr>
	               <td> ${dto.getN_num() } </td>
	               <td> 
	              
	               	  <a href="admin_notice_cont.do?num=${dto.getN_num() }">
	               			${dto.getN_title() } </a>
	               </td>
	               <td> ${dto.getN_hit() } </td>
	               <td> ${dto.getN_date().substring(0,10) } </td>
	            </tr>
	         </c:forEach>
	      </c:if>
	      
	      <c:if test="${empty list }">
	        <tr>
	           <td colspan="7" align="center">
	              <h3>검색된 레코드가 없습니다.</h3>
	           </td>
	        </tr>
	      </c:if>
	      <tr>
	         <td colspan="7" align="right">
	            <input class="btn btn-primary" type="button" value="글쓰기"
	               onclick="location.href='admin_notice_write.do'">
	         </td>
	      </tr>
	   </table>
	   
	    <ul class="pagination">
	   <c:if test="${page > block }">
	     <li class="page-item"><a class="page-link" href="admin_notice_list.do?page=1">◀◀</a></li>
	     <li class="page-item"><a class="page-link" href="admin_notice_list.do?page=${startBlock - 1 }">◀</a></li>
	   </c:if>
	   
	   <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
	      <c:if test="${i == page }">
	      	<li class="active"><a class="page-link" href="admin_notice_list.do?page=${i }">${i }</a></li>
	      </c:if>
	      
	      <c:if test="${i != page }">
	         <li class="page-item"><a class="page-link" href="admin_notice_list.do?page=${i }">${i }</a></li>
	      </c:if>
	   </c:forEach>
	   
	   <c:if test="${endBlock < allPage }">
	     <li class="page-item"><a class="page-link" href="admin_notice_list.do?page=${endBlock + 1 }">▶</a></li>
	     <li class="page-item"><a class="page-link" href="admin_notice_list.do?page=${allPage }">▶▶</a></li>
	   </c:if>
	   </ul>
	   <jsp:include page="../include/admin_bottom.jsp" />
	</div>
</body>
</html>