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
	<section class="containerHeader">
		<div class="containerBox">
			<h1>Sign Up</h1>

			<form method="post" action="adminRegisterRequest">
				<p>
					<input type="text" name="username" value="" placeholder="Username">
				</p>
				<p>
					<input type="password" name="password" value=""
						placeholder="Password">
				</p>
				<p>
					<input type="password" name="password2" value=""
						placeholder="Confirm Password">
				</p>
				<p class="submit">
					<input type="submit" name="commit2" value="Submit">
				</p>
			</form>
		</div>
	</section>


</body>
</html>