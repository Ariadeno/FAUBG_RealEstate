<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="mainContainer">
	<h1>Properties</h1>
	<hr />
	<c:if test="${not empty properties}">
		<c:forEach var="listValue" items="${properties}">
			<div class="box">
				<form action="/account/editProperty" method="get">
					${listValue}
					<c:if test="${isAdmin eq true}">
						<input type="submit" name="commit" value="Edit">
					</c:if>
				</form>
				<c:if test="${isAdmin eq false}">
					<c:if test="${LoginTitle == 'My Account'}">
						<form action="/buyrent" method="get" enctype="multipart/form-data">
							<div style="display: none;">${listValue}</div>
							<c:if test="${fn:contains(listValue,'Rental: No')}">
								<input type="submit" name="commit" value="Buy me!">
							</c:if>
							<c:if test="${fn:contains(listValue,'Rental: Yes')}">
								<input type="submit" name="commit" value="Rent me!">
							</c:if>
						</form>
					</c:if>
				</c:if>
			</div>
		</c:forEach>
	</c:if>
</div>
<script>
	$(document)
			.ready(
					function() {
						var showChar = 150;
						var ellipsestext = "...";
						var moretext = "more";
						var lesstext = "less";
						$('.more')
								.each(
										function() {
											var content = $(this).html();

											if (content.length > showChar) {

												var c = content.substr(0,
														showChar);
												var h = content.substr(
														showChar - 1,
														content.length
																- showChar);

												var html = c
														+ '<span class="moreelipses">'
														+ ellipsestext
														+ '</span>&nbsp;<span class="morecontent"><span>'
														+ h
														+ '</span>&nbsp;&nbsp;<a href="" class="morelink">'
														+ moretext
														+ '</a></span>';

												$(this).html(html);
											}

										});

						$(".morelink").click(function() {
							if ($(this).hasClass("less")) {
								$(this).removeClass("less");
								$(this).html(moretext);
							} else {
								$(this).addClass("less");
								$(this).html(lesstext);
							}
							$(this).parent().prev().toggle();
							$(this).prev().toggle();
							return false;
						});
					});
</script>