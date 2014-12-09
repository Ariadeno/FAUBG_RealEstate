<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/base.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/input.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/responsive-nav.css" />">
<link rel="stylesheet"
	href="<c:url value="/resources/css/slide-show.css" />">
<script src="<c:url value="/resources/js/modernizr.custom.86080.js" />"></script>
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/responsive-nav.js" />"></script>
<meta name="theme-color" content="#1B5098">
</head>
<div class="header">
	<ul class="cb-slideshow">
		<li><span>Image 01</span></li>
		<li><span>Image 02</span></li>
		<li><span>Image 03</span></li>
		<li><span>Image 04</span></li>
		<li><span>Image 05</span></li>
	</ul>
	<div id="logo">
		<a href="/"></a>
	</div>
	<div class="nav-collapse">
		<div class="nav-collapse-container">
			<nav>
				<ul style="width: 100%; background-color: #ffffff;">
					<li><a href="/">Home</a></li>
					<li><a href="/buy">Buy</a></li>
					<li><a href="/rent">Rent</a></li>
				</ul>
			</nav>
			<div class="account-menu">
				<ul style="right:0;">
					<c:if test="${LoginTitle == 'My Account'}">
						<li style="float: right;"><a href="/logout">Logout</a></li>
					</c:if>
					<li style="float: right;"><a href="${AccountUrl}">${LoginTitle}</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="banner"></div>
</div>

<script>
	var navigation = responsiveNav(".nav-collapse", {
		animate : true,
		transition : 284,
		label : "Menu",
		insert : "after",
		customToggle : "",
		closeOnNavClick : false,
		openPos : "relative",
		navClass : "nav-collapse",
		navActiveClass : "js-nav-active",
		jsClass : "js",
		init : function() {
		},
		open : function() {
		},
		close : function() {
		}
	});
</script>