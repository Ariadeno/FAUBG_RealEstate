<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#banner {
	height: 650px;
}

.cb-slideshow, .cb-slideshow:after {
	height: 650px;
}
</style>
<div style="left: 50%; width: 350px; position: absolute;">
<div class="onImageContainer">
	<c:if test="${LoginTitle == 'My Account'}">
		<h1>My Account</h1>
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
