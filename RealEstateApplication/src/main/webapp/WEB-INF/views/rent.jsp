<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mainContainer">
	<h1>Rent Property</h1>
	<hr />
	<c:if test="${not empty rentalProperties}">
		<c:forEach var="listValue" items="${rentalProperties}">
			<div class="box">
			${listValue}
				<c:if test="${LoginTitle == 'My Account'}">
					<input type="submit" name="commit" value="Rent me!">
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