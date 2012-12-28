<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Bootstrap, from Twitter</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<!-- Le styles -->
	<link href="resource/main.css" rel="stylesheet">
	<link href="http://fonts.googleapis.com/css?family=Droid+Serif:400,400italic" rel="stylesheet" type="text/css">
	<link href="http://fonts.googleapis.com/css?family=Metal+Mania" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="header">
		<div class="navbar">
			<a class="brand" href="#">Project name</a>
			<ul class="nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
			</ul>
			<div class="login">
				Logged in as <a href="#">Username</a>
			</div>
		</div>
	</div>
	<div id="container">
		<div class="sidebar">
			<div class="menu">
				Menu<!-- include menu.zul here -->
			</div>
			<div class="user_status">
				UserStatus<!-- include userStatus.zul here -->
			</div>
		</div>
		<div class="content">
			<!-- include content.zul here -->
			<jsp:include page="content.zul" />
		</div>
	</div>
	<div id="footer">
		Footer
	</div>
</body>
</html>