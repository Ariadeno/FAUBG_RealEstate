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
</div>
