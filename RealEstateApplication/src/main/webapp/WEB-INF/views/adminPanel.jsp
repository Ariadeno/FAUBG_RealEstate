<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mainContainer">
	<c:if test="${isAdmin eq true}">
		<h1>Admin Panel</h1>
		This is the Admin Panel. Here you can control all the real estate
		properties.
		<hr />
		<div style="text-align: left;">
			<h2>New Property</h2>
			<form action="adminPanel/addProperty" method="POST"
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
		<hr />
		<div style="text-align: left;">
			<c:if test="${not empty properties}">
				<c:forEach var="listValue" items="${properties}">
					<form action="adminPanel/editProperty" method="get">
						${listValue} <input type="submit" name="commit" value="Edit">
					</form>
				</c:forEach>
			</c:if>
		</div>
	</c:if>
	<c:if test="${isAdmin eq false}">
		<div class="containerBox">You have no access to this page!</div>
	</c:if>
</div>