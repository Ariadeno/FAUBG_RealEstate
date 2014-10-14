<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Sign up Form</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>

<body>
	<section class="container">
		<div class="signup">
			<h1>Member login</h1>
			<P>${loginSuccess}</P>
		</div>
	</section>
</body>
</html>