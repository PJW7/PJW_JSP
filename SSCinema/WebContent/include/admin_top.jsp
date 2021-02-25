<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script src="js/bootstrap.min.js"></script>
<script
  src="http://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>	


<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/admintheme.css">
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<%=request.getContextPath() %>/admin_main.do">Home</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="<%=request.getContextPath() %>/admin_theater_list.do">극장목록 <span class="sr-only">(current)</span></a></li>
        <li><a href="<%=request.getContextPath() %>/admin_room_list.do">상영관목록</a></li>
        <li><a href="<%=request.getContextPath() %>/admin_seat_add.do">좌석등록</a></li>
        <li><a href="<%=request.getContextPath() %>/admin_movie_list.do">영화목록</a></li>
        <li><a href="<%=request.getContextPath() %>/admin_playing_list.do">상영일정목록</a></li>
        <li><a href="<%=request.getContextPath() %>/admin_user_list.do">회원 목록</a></li>
        <li><a href="<%=request.getContextPath() %>/admin_re_mo_list.do">예매 목록</a></li>
        <li><a href="<%=request.getContextPath() %>/admin_food_list.do">매점 목록</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">공지사항 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="<%=request.getContextPath() %>/admin_faq_list.do">F A Q</a></li>
            <li><a href="<%=request.getContextPath() %>/admin_notice_list.do">공지사항</a></li>
            
          </ul>
        </li>
      </ul>
      
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


</body>
</html>