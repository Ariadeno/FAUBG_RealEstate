<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<link href="<c:url value="/resources/css/main_menu.css" />"
	rel="stylesheet">
<jsp:include page="/WEB-INF/variables.jsp" /></head>
<div class="header">
	<header>
		<section id="banner"></section>
		<nav>
			<ul>
				<li><a href="${ROOT_DIR}">Home</a></li>
				<li><a href="buy"> Buy </a></li>
				<li><a href="rent"> Rent </a></li>
				<li id="loginknop"><a href=${AccountUrl }>${LoginTitle}</a>
					<ul>
						<c:if test="${isAdmin == true}">
							<li><a href="adminPanel">Admin Panel</a></li>
						</c:if>
						<c:if test="${LoginTitle == 'My Account'}">
							<li><a href="logout">Logout</a></li>
						</c:if>
					</ul></li>
			</ul>
		</nav>
	</header>
</div>