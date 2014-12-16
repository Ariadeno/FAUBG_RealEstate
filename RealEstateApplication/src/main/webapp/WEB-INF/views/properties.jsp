<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="mainContainer">
	<h1>Properties</h1>
	<hr />
	<c:if test="${not empty properties}">
	<form action="${pageType}0" method="GET">
			<p>
				<input type="checkbox" name="orderBy" id="terms"><a>Narrow your search</a>
				<input type="text" name="from" id="priceFrom" placeholder="Price from">
				<input type="text" name="to" id="priceTo" placeholder="Price to">
				<input type="submit" value="Submit">
			</p>
		</form>
		<c:forEach var="listValue" items="${properties}">
			<div class="box">
					${listValue}
					<c:if test="${isAdmin eq true}">
						<form action="/account/editProperty" method="get">
							<input type="hidden" name="id" value="${listValue.id}">
							<input type="submit" name="commit" value="Edit">
							
						</form>
						<form action="/account/deleteProperty" method="get">
							<input type="hidden" name="id" value="${listValue.id}">
							<input type="submit" name="commit" value="Delete">
						</form>
					</c:if>

				
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

<div class="mainContainer">

	<button id="btn_previous" type="button" onclick="goPrevious()">Previous</button>
	<script>
	function previousCheck() {
		var x = ${pageNumber};
		if(x == 0) {
		document.getElementById('btn_previous').style.visibility='hidden';
		}
		else {document.getElementById('btn_previous').style.visibility='visible';}
	}
	previousCheck();
	function goPrevious() {
		var x = "${pageNumber}";
		var y =1;
		var pre = x-y;
		var link = "${pageType}";
		var link1 = link + pre.toString()
		window.location.href=link1;
	}
	</script>	
	<p id= pageNumber></p>
	<script>
			function correctPageNumber() {
				var x = ${pageNumber};
				var y = 1;
				return (x + y);
			}
		document.getElementById("pageNumber").innerHTML = correctPageNumber() + "/" + ${pages};
	</script>	
	<button id="btn_next" type="button" onclick="goNext()">Next</button>
	<script>
	function nextCheck() {
		var x = ${pages};
		var x2 = x - 1;
		var y = "${pageNumber}";
		if(y < x2) {
		document.getElementById('btn_next').style.visibility='visible';
		}
		else {document.getElementById('btn_next').style.visibility='hidden';}
	}
	nextCheck();
	function goNext() {
		var x = ${pageNumber};
		var y =1;
		var pre = x+y;
		var link = "${pageType}";
		var link1 = link + pre.toString() 
		window.location.href=link1;
	}	
	</script>	
	
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