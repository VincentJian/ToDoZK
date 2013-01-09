<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
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
		<jsp:include page="innerpage/jsp/header.jsp" />
	</div>
	<div id="container">
		<div class="sidebar">
			<jsp:include page="innerpage/zul/sidebar.zul" />
		</div>
		<div id="content" class="content"></div>
	</div>
	<div id="footer">
		<jsp:include page="innerpage/jsp/footer.jsp" />
	</div>
</body>
</html>