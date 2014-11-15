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
<title>Buy</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<div class="container">
		<section class="containerHeader">
			<div class="containerBox">
				<c:if test="${not empty buyProperties}">
						<c:forEach var="listValue" items="${buyProperties}">
							${listValue}
							<c:if test="${LoginTitle == 'My Account'}">
								<input type="submit" name="commit" value="Buy me!">
							</c:if>
						</c:forEach>
				</c:if>
			</div>
		</section>
	</div>
</body>
</html>