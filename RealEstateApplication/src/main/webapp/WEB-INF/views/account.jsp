<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Account</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<div class="container">
	<section class="containerHeader">
			<div class="containerBoxWide">
				<h1>Account details</h1>
				<p>First Name:${User.firstName}</p>
				<p>Last Name:${User.lastName}</p>
				<p>Username:${User.username }</p>
				<p>Email:${User.email }</p>
				<p>Phone:${User.phone }</p>
				<p>Address:${User.address }</p>
				<p>City:${User.city }</p>
				<p>Zip:${User.zip }</p>
				<c:if test = "${User.isAdmin eq true}">
					<br/>
					<h1>Admin panel</h1>
					<p>Hello World</p>
					
				</c:if>
			</div>
	</section>
	</div>
</body>
</html>