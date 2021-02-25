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

<style type="text/css">

	table {
		background-color: white;}
		th {
    background-color: gray;
  }	
</style>
</head>
<body>
	<div class="container" align="center">
	   <div class="header-admin">
	   		<h1 class="header-title">게시판 게시글 상세 내역</h1>
	   </div>

	   <jsp:include page="../include/admin_top.jsp" />
<%-- 	   <%=request.getAttribute("number")%>
	   	   <%=request.getAttribute("cont")%> --%>
	   <table class="table table-striped">
	      <c:set var="dto" value="${cont }" />
	      <c:if test="${!empty dto }">
	         <tr>
	            <th>작성자</th>
	            <td> ${dto.getQ_writer() } </td>
	         </tr>
	         <tr>
	            <th>글제목</th>
	            <td> ${dto.getQ_title() } </td>
	         </tr>
	         <tr>
	            <th>글내용</th>
	            <td>
	               <textarea rows="20" cols="100" readonly>${dto.getQ_cont() }</textarea>
	            </td>
	         </tr>
	         <tr>
	            <th>조회수</th>
	            <td> ${dto.getQ_hit() } </td>
	         </tr>
	         <tr>
	            <th>작성일자</th>
	            <td> ${dto.getQ_date() } </td>
	         </tr>
	      </c:if>
	      
	      <c:if test="${empty dto }">
	         <tr>
	            <td colspan="2" align="center">
	               <h3>검색된 레코드가 없습니다.</h3>
	         	</td>
	         </tr>
	      </c:if>
	      
	      <tr>
	         <td colspan="2" align="center">
                <input class="btn btn-primary" type="button" value="글수정"	      
	              onclick="location.href='admin_faq_edit.do?no=${dto.getQ_no() }'">
	            <input class="btn btn-primary" type="button" value="글삭제"	      
	              onclick="location.href='admin_faq_delete.do?no=${dto.getQ_no() }'">
	            <input class="btn btn-primary" type="button" value="글답변"	      
	              onclick="location.href='admin_faq_reply.do?no=${dto.getQ_no() }'">
	            <input class="btn btn-primary" type="button" value="전체목록"	      
	              onclick="location.href='admin_faq_list.do'">
	         </td>
	      </tr>
	   </table>
	   <jsp:include page="../include/admin_bottom.jsp" />
	</div>

</body>
</html>