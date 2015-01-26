<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script src="<c:url value="/resources/js/pgwslider.min.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/pgwslider.min.css" />">

<<script type="text/javascript">
$(document).ready(function() {
    $('.pgwSlider').pgwSlider();
});
</script>

<div class="mainContainer">
	<c:if test="${fn:contains(property,'Rental: No')}">
		<h1>This property is for sale.</h1>
	</c:if>
	<c:if test="${fn:contains(property,'Rental: Yes')}">
		<h1>This property is for rent.</h1>
	</c:if>
	<hr />
	
	<h3>${property2[0]}</h3>
	

	
	<div>	
	<ul class="pgwSlider">
		<c:if test="${not empty propertyImages}">
			<c:forEach var="listValue" items="${propertyImages}">
				<li><img src="${listValue}"/></li>
			</c:forEach>
		</c:if>
	</ul>
	</div>
	
	<table style="width:100%">
	  <tr>
	    <td>
				<table>
				  <tr>
				    <td>Adress:</td>
					<td>${property2[0]}</td>
				  </tr>
				  <tr>
				    <td>Discription:</td>	
				    <td>${property2[1]}</td>		
				  </tr>
				  <tr>
				    <td>Area:</td>	
				    <th>${property2[2]}</th>		
				  </tr>
				  <tr>
				    <td>Rental:</td>	
				    <td>${property2[3]}</td>		
				  </tr>
				  <tr>
				    <td>Price:</td>	
				    <td>${property2[4]}</td>		
				  </tr>
				</table>

		</td>
		    <td>
			    <iframe height="100%" width="100%" src="${iframe}" frameborder="0" scrolling="no" marginheight="0" marginwidth="0">
	 		 		<p>Your browser does not support iframes.</p>
				</iframe>
		    </td>
	  </tr>
	</table>
	
	<c:if test="${not empty offers}">
		<c:forEach var="listValue" items="${offers}">
			<div class="box">
					${listValue}
					</div>
		</c:forEach>
	</c:if>
		
	
	
	<form action="makeOffer" method="get" enctype="multipart/form-data">
	<input type="text" name="offerAmount" value="" placeholder="your offer in euro" required/>
	<input type="hidden" name="id" value="${id}" />
	<p class="submit">
	<input type="submit" name="commit" value="Make offer"/>
	</p>
	</form>
</div>
