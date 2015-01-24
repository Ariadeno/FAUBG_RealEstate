<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#banner {
	height: 650px;
}

.cb-slideshow, .cb-slideshow:after {
	height: 650px;
}

.mainContainer {
	border-radius: 5px;
	left: -50%;
	width: 400px;
	position: absolute;
	top: -550px;
	background-color: rgba(255, 255, 255, 0.8);
	margin-left: 0px;
}
</style>
<div class="blueInputContainer">
	<div class="blueInput-wrapper">
		<!-- blueInput -->
		<section class="blueInput">
			<header class="blueInput-hdr">
				<h1 class="blueInput-hdr-1 trapezoid trapezoid_br content_collapsed">Verify</h1>
			</header>
			<div class="g g_gutterless">
				<div class="lg-1 md-1 sm-1 blueInput-body-container">
					<div class="blueInput-body" style="text-align: center;">
						<form id="formblueInputBar" name="formblueInputBar" action="/verifyRequest" role="blueInput" class="blueInput-form" method="post">
							<input type="hidden" name="username" value="${Username}"/>
							<input type="hidden" name="hash" value="${Hash}"/>
							<div class="blueInputbox blueInputbox_lg yui3-skin-sam" id="yui_3_15_0_1_1417199162936_106">
								<c:if test="${not empty Username}">
									Do you want to verify ${Username}?
									<br />
									<button type="submit" id="loginButton" tabindex="3">Verify</button>
								</c:if>
								<c:if test="${empty Username}">
									Error verifying.
								</c:if>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>