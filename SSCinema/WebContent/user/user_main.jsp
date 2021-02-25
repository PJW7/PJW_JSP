<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SSCinema</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/user.css">
</head>
<body>
	
	<jsp:include page="../include/user_login_include.jsp" />
	<div class="container" align="center">
		
		<div class="header-user">
			<h1 class="header-title">SSCinema</h1>
		</div>
		<jsp:include page="../include/user_top.jsp" />
			
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
  			<ol class="carousel-indicators">
    			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    			<li data-target="#myCarousel" data-slide-to="1"></li>
   				<li data-target="#myCarousel" data-slide-to="2"></li>
  			</ol>
  			<div class="carousel-inner" role="listbox">
    			<div class="item active">
  					<img src="images/user_main_wonderwoman.jpg" alt="main" width="100%" height="500px">
				</div>
    			<div class="item">
  					<img src="images/user_main_josee.jpg" alt="main" width="100%" height="500px">
				</div>
				<div class="item">
  					<img src="images/user_main_interstellar.jpg" alt="main" width="100%" height="500px">
				</div>
  			</div>

  			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    			<span class="sr-only">Previous</span>
  			</a>
  			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    			<span class="sr-only">Next</span>
 			</a>
		</div>			
		<jsp:include page="../include/user_bottom.jsp" />
	</div>


</body>
</html>