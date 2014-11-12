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
					
					<h3>Add property</h3>
						<form method="post" action="account/addProperty">
							<p>
								<input type="text" name="address" value="" placeholder="Address">
							</p>
							<p>
								<input type="text" name="price" value="" placeholder="Price">
							</p>
							<p>
								<input type="text" name="area" value=""
									placeholder="Area">
							</p>
							<p>
								<input type="text" name="description" value="" placeholder="Description">
							</p>
							<p>
								<input type="checkbox" name = "rental" id="terms">Rental property<a class="terms" href=#></a>
							</p>
							
							<p>
								<input type="text" name="images" value="" placeholder="Image 1">
							</p>
							
							<p>
								<input type="text" name="images" value="" placeholder="Image 2">
							</p>
							
							<p>
								<input type="text" name="images" value="" placeholder="Image 3">
							</p>
							
							<p class="submit">
								<input type="submit" name="commit" value="Submit">
							</p>
					</form>
					
					<c:out value="${param.images}" default="Nope - nothing"/>
					
					
				</c:if>
			</div>
	</section>
	</div>
</body>
</html>