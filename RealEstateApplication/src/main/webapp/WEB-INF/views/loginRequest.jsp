<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="refresh" content="2; url=/" />
<title>Sign up Form</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>

<body>
	<section class="containerHeader">
		<div class="containerBox">
			<h1>Member login</h1>
			<P>${loginSuccess}</P>
			<form action="/" method="get" >
				<p class="submit"><input type="submit" name="commit" value="Home"/>
			</form>
		</div>
	</section>
</body>
</html>