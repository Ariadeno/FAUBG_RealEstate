<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
td {
    padding: 5px;
}
</style>
<div class="mainContainer">
	<h1>Edit Property</h1>
	<hr />
	<div style="text-align: left;">
	<div class="box">${property}</div>
	<c:if test="${not empty propertyImages}">
		<c:forEach var="listValue" items="${propertyImages}">
			<br /><img src="${listValue}">
		</c:forEach>
	</c:if>
	</div>
</div>
