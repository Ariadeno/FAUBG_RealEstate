<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
			<a>HELLO</a>
				<c:forEach var="rentalProperty" items="rentalProperties">
					<h1>${rentalProperty.address}</h1>
					<p>Price: ${rentalProperty.price }</p>
					<p>Area: ${rentalProperty.area }</p>
					<p>Description: ${rentalProperty.description}</p>
					<c:forEach var="propertyImage" items="images">
						 <img src=${propertyImage.location } alt="image"> 
					</c:forEach>
					<br/>
				</c:forEach>
			</div>
	</section>
	</div>
</body>
</html>