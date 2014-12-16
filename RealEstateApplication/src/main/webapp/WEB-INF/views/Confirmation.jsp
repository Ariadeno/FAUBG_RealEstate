<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="mainContainer">
	<p>${user.firstName} ${user.lastName}, you have made an offer of ${offer} euro for property:  ${property.address}
	<hr/>
	<form action="/" >
	
	<input type="submit" name="commit" value="Ok">
	</form>
	
	
</div>
