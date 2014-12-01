<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#banner {
	height: 650px;
}

.cb-slideshow, .cb-slideshow:after {
	height: 650px;
}

.mainContainer {
	border-radius: 5px;
	padding:20px;
	left: -50%;
	width: 400px;
	position: absolute;
	top: -550px;
	background-color: rgba(255, 255, 255, 0.9);
}
</style>
<div style="left: 50%; width: 400px; position: absolute;">
<div class="mainContainer">
	<c:if test="${LoginTitle == 'My Account'}">
		<h1>Account details</h1>
		<hr />
		<div style="text-align: left;">
			<p>First Name:${User.firstName}</p>
			<p>Last Name:${User.lastName}</p>
			<p>Username:${User.username }</p>
			<p>Email:${User.email }</p>
			<p>Phone:${User.phone }</p>
			<p>Address:${User.address }</p>
			<p>City:${User.city }</p>
			<p>Zip:${User.zip }</p>
		</div>
	</c:if>
	<c:if test="${LoginTitle == 'Login'}">
		You have no access to this page!
	</c:if>
</div>
</div>
