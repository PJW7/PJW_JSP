<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.js"></script>
<link rel="stylesheet" href="css/user.css">
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="<%=request.getContextPath() %>/user_movie_info.do">Movie</a></li>
		<li><a href="<%=request.getContextPath() %>/user_reserv.do">Ticket</a></li>
		<li><a href="<%=request.getContextPath() %>/user_food_list.do">Snack</a></li>
		<li><a href="<%=request.getContextPath() %>/user_my_page.do">My Page</a></li>
		<li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Help Desk<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
			<li><a href="<%=request.getContextPath() %>/user_faq_list.do">F A Q</a></li>
			<li><a href="<%=request.getContextPath() %>/user_notice_list.do">Notice</a></li>
          </ul>
        </li>
      </ul>
      
      
    </div>
  </div>
</nav>
	
</body>
</html>