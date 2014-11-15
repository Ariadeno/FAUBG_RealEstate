<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page session="false" %>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Upload Form</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

</head>
<body>
	<div class="container">
		<section class="containerHeader">
			<div class="containerBox">
				<form method="POST" action="uploadFile" enctype="multipart/form-data">
					File to upload: <input type="file" name="file"><br />
					Name: <input type="text" name="name"><br /> <br /> <input
						type="submit" value="Upload"> Press here to upload the
					file!
				</form>
			</div>
		</section>
	</div>
</body>
</html>