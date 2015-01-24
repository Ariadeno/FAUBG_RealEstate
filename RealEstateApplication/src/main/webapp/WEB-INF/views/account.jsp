<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
@media screen and (min-width: 750px) {
	#banner {
		height: 650px;
	}
	.cb-slideshow, .cb-slideshow:after {
		height: 650px;
	}
	input[type="text"] {
		margin: 0px;
	}
}
</style>
<c:if test="${LoginTitle == 'My Account'}">
	<div style="left: 50%; width: 350px; position: absolute;">
		<div class="onImageContainer" style="left: -105%;">
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
				<p>${test}</p>
				
			</div>
			
		</div>
		<c:if test="${isAdmin eq true}">
			<div class="onImageContainer" style="left: 5%">
				<div style="text-align: left;">
					<h1>New Property</h1>
					<hr />
					<form method="POST" action="/account/addProperty"
						enctype="multipart/form-data">
						<input type="text" name="address" value="" placeholder="Address"><br />
						<input type="text" name="price" value="" placeholder="Price"><br />
						<input type="text" name="area" value="" placeholder="Area"><br />
						<input type="text" name="description" value="" placeholder="Description"><br />
							<input type="file" name="images"><br />
							<input type="file" name="images"><br />
							<input type="file" name="images"><br />
						<input type="checkbox" name="rental" id="terms">Rental
						property<a class="terms" href=#></a> <input type="submit"
							name="commit" value="Submit">
					</form>
				
				</div>
				<form action="/account/viewOffers" method="GET">
				<input type="submit" name="commit" value="View all offers">
				</form>
				<div>
				
				</div>
			</div>
		</c:if>
	</div>
	<div class="mainContainer afterImageContainer">
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
		<c:if test="${isAdmin eq true}">
			<div>
				<div style="text-align: left;">
					<h1>New Property</h1>
					<hr />
					<form action="/account/addProperty" method="POST"
						enctype="multipart/form-data">
						<input type="text" name="address" value="" placeholder="Address"><br />
						<input type="text" name="price" value="" placeholder="Price"><br />
						<input type="text" name="area" value="" placeholder="Area"><br />
						<input type="text" name="description" value=""
							placeholder="Description"><br />
						<div id="initRow">
							<input type="file" name="files">
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
			</div>
		</c:if>
	</div>
	<c:if test="${isAdmin eq true}">
	<c:if test="${viewProperties eq true}">
		<form action="/account/" method="GET">
			<p>
				<input type="checkbox" name="occupied" id="terms">Show only occupied properties<a class="terms" href=#></a>
			</p>
			<p>
				<input type="submit" value="Submit">
			</p>
		</form>
		<jsp:include page="properties.jsp" />
	</c:if>
	<c:if test="${viewProperties eq false}">
		<jsp:include page="viewOffers.jsp" />
	</c:if>
	</c:if>
</c:if>

<c:if test="${not empty userOffers}">
		<c:forEach var="listValue" items="${userOffers}">
			<div class="box">
					${listValue}
					</div>
		</c:forEach>
	</c:if>
<div class="mainContainer">
	<c:if test="${LoginTitle == 'Login'}">You have no access to this page!</c:if>
</div>
