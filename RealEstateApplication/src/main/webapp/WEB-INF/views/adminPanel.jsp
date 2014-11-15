<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Admin Panel</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style_index.css" />"
	rel="stylesheet">
<!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/animations.js" />"></script>
</head>
<body>
	<c:if test="${isAdmin eq true}">
		<div class="container">
			<section class="containerHeader">
				<div class="containerBox">
					<h1>Admin Panel</h1>
					This is the Admin Panel. Here you can control all the real estate
					properties.
				</div>
				<br />
				<div class="containerBox">
					<h1>New Property</h1>
					<form action="adminPanel/addProperty" method="post">
						<input type="text" name="address" value="" placeholder="Address"><br />
						<input type="text" name="price" value="" placeholder="Price"><br />
						<input type="text" name="area" value="" placeholder="Area"><br />
						<input type="text" name="description" value=""
							placeholder="Description"><br />
						<div id="initRow">
							<input type="file" name="images">
						</div>
						<p>
							<input type="checkbox" name="rental" id="terms">Rental
							property<a class="terms" href=#></a>
						</p>
						<p>
							<input type="submit" name="commit" value="Submit">
						</p>
					</form>
				</div>
				<br />
				<div class="containerBox">
					<c:if test="${not empty properties}">
						<c:forEach var="listValue" items="${properties}">
							<form action="adminPanel/editProperty" method="get">
								${listValue} <input type="submit" name="commit" value="Edit">
							</form>
						</c:forEach>
					</c:if>
				</div>
			</section>
		</div>
	</c:if>
	<c:if test="${isAdmin eq false}">
		<div class="container">
			<section class="containerHeader">
				<div class="containerBox">You have no access to this page!</div>
			</section>
		</div>
	</c:if>
</body>
</html>