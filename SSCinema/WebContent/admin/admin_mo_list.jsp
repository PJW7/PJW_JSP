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
<body>
<div class="container" align="center">
	   <div class="header-admin">
	   		<h1 class="header-title">예매 목록</h1>
	   </div>

	   <jsp:include page="../include/admin_top.jsp" />
	   <table class="table table-striped">
	   	<thead>
	      <tr>
	         <th>영화구매자 이름</th> <th>영화관</th> <th>영화제목 <th>상영관</th>
	      </tr>
	      </thead>
	      <c:set var="list" value="${List }" />
	      <c:if test="${!empty list }">
	         <c:forEach items="${list }" var="dto">
	            <tr>
	               <td> ${dto.getRe_user_name() } </td>
	           	   <td> ${dto.getRe_theater_name() } </td>
	               <td> ${dto.getRe_movie_name() } </td>
	               <td> ${dto.getRe_room_name() } </td>
	          
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
	      
	   </table>
	   
	   <ul class="pagination">
	   <c:if test="${page > block }">
	     <li class="page-item"><a class="page-link" href="admin_re_mo_list.do?page=1">◀◀</a></li>
	     <li class="page-item"><a class="page-link" href="admin_re_mo_list.do?page=${startBlock - 1 }">◀</a></li>
	   </c:if>
	   
	   <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
	      <c:if test="${i == page }">
	      	<li class="active"><a class="page-link" href="admin_re_mo_list.do?page=${i }">${i }</a></li>
	      </c:if>
	      
	      <c:if test="${i != page }">
	         <li class="page-item"><a class="page-link" href="admin_re_mo_list.do?page=${i }">${i }</a></li>
	      </c:if>
	   </c:forEach>
	   
	   <c:if test="${endBlock < allPage }">
	     <li class="page-item"><a class="page-link" href="admin_re_mo_list.do?page=${page + 1 }">▶</a></li>
	     <li class="page-item"><a class="page-link" href="admin_re_mo_list.do?page=${allPage }">▶▶</a></li>
	   </c:if>
	   </ul>
	   <jsp:include page="../include/admin_bottom.jsp" />
	</div>
</body>
<script>
$(".delete_one_btn").on("click",function(){
	var user_no = $(this).data("user_no");
	location.href='admin_user_delete.do?user_no='+user_no;
	
})
</script>
</html>