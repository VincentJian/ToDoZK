<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="">
	<meta name="author" content="">
</head>
<body>
	<div class="navbar">
		<a class="brand" href="" onclick="window.location.reload();">ToDoZK</a>
<!-- 		<ul class="nav"> -->
<!-- 			<li class="active"><a href="#">Home</a></li> -->
<!-- 			<li><a href="#about">About</a></li> -->
<!-- 			<li><a href="#contact">Contact</a></li> -->
<!-- 		</ul> -->
		<div class="user_status">
			<jsp:include page="../zul/userStatus.zul" />
		</div>
	</div>
</body>
</html>