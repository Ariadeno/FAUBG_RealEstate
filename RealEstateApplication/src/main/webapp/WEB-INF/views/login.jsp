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

#loginButton {
	background-image: url(/resources/images/login.png);
	background-repeat: no-repeat;
	background-position: center;
	top: 59px;
}

#registerButton {
	height: auto;
}
</style>
<div class="blueInputContainer">
	<div class="blueInput-wrapper">
		<!-- blueInput -->
		<section class="blueInput">
			<header class="blueInput-hdr">
				<h1 class="blueInput-hdr-1 trapezoid trapezoid_br content_collapsed">Login</h1>
			</header>
			<div class="g g_gutterless">
				<div class="lg-1 md-1 sm-1 blueInput-body-container">
					<div class="blueInput-body">
						<form id="formblueInputBar" name="formblueInputBar" action="loginRequest" role="blueInput" class="blueInput-form" method="post">
							<div class="blueInputbox blueInputbox_lg yui3-skin-sam" id="yui_3_15_0_1_1417199162936_106">
								<label class="hide-visually" for="username">Username</label><input placeholder="Username" tabindex="1"
									class="blueInput-input yui3-aclist-input" type="text" autocomplete="off" data-default-placeholder="Username" id="usernameInput"
									maxlength="150" name="username" autocorrect="off" aria-autocomplete="list" aria-expanded="false" aria-owns="yui_3_15_0_1_1417199162936_470">
								<label class="hide-visually" for="username">Password</label><input placeholder="Password" tabindex="1" c class="blueInput-input yui3-aclist-input"
									type="password" id="passwordInput" name="password">
								<button type="submit" aria-label="Submit" class="blueInput-button blueInput-submit-button button_alt" id="loginButton" tabindex="3"></button>
								<div style="text-align: right;">
									<b style="padding-right: 20px;">Don't have an account?</b> <a href="/register"><button type="button" class="blueInput-button button_alt" id="registerButton"
											tabindex="4">Sign-Up</button></a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>