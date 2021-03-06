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
	   		<h1 class="header-title">F A Q 답변</h1>
	   </div>

	   <jsp:include page="../include/admin_top.jsp" />
	   <form method="post"
	      action="<%=request.getContextPath() %>/admin_faq_reply_ok.do">
	      <c:set var="dto" value="${reply }" />
	      <input type="hidden" name="q_no" value="${dto.getQ_no() }">
	      <input type="hidden" name="q_group" value="${dto.getQ_group() }">
	      <input type="hidden" name="q_step" value="${dto.getQ_step() }">
	      <input type="hidden" name="q_indent" value="${dto.getQ_indent() }">
	      
	      <table class="table table-striped">
	         <tr>
	            <th>작성자</th>
	            <td> <input name="reply_writer"
	                     value="관리자" > </td>
	         </tr>
	         <tr>
	            <th>글제목</th>
	            <td> <input name="reply_title"
	                    value="RE:${dto.getQ_title() }" > </td>
	         </tr>
	         <tr>
	            <th>글내용</th>
	            <td>
	              <textarea rows="20" cols="100" name="reply_cont">${dto.getQ_cont() }</textarea>
	         	</td>
	         </tr>
	         <tr>
	         	<th>비밀번호</th>
	            <td> <input type="password" name="reply_pwd" > </td>
	         </tr>
	         <tr>
	            <td colspan="2" align="center">
	               <input class="btn btn-primary" type="submit" value="답변">
	                    &nbsp;&nbsp;&nbsp;
	               <input class="btn btn-primary" type="reset" value="취소">
	         	</td>
	         </tr>
	      </table>
	   </form>
	   
	   <jsp:include page="../include/admin_bottom.jsp" />
	</div>

</body>
</html>