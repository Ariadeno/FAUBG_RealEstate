<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="mainContainer">
	<c:if test="${fn:contains(property,'Rental: No')}">
		<h1>You just bought</h1>
	</c:if>
	<c:if test="${fn:contains(property,'Rental: Yes')}">
		<h1>You just rented</h1>
	</c:if>
	<hr />
	
	<div class="box">${property}</div>
	<c:if test="${not empty propertyImages}">
		<c:forEach var="listValue" items="${propertyImages}">
			<br /><img src="${listValue}">
		</c:forEach>
	</c:if>
	<form action="makeOffer" method="get" enctype="multipart/form-data">
	<input type="text" name="offer" value="" placeholder="your offer in euro" required>
	<div style="display: none;">${listValue}</div>
	
	<p class="submit">
	<input type="submit" name="commit" value="Make offer">
	</form>
</div>
