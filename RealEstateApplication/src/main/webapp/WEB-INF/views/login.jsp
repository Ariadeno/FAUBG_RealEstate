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
<div style="left: 50%; width: 400px; position: absolute;">
	<div class="mainContainer">
		<div class="containerBoxSmall">
			<h1>Member Login</h1>
			<form action="loginRequest" method="post">
				<p>
					<input type="text" name="username" value="" placeholder="Username" required>
				</p>
				<p>
					<input type="password" name="password" value=""
						placeholder="Password" required>
				</p>
				<p class="remember_me">
					<label class="first"> <input type="checkbox"
						name="remember_me" id="remember_me"> Remember me on this
						computer
					</label>
				</p>

				<p class="submit">
					<input type="submit" name="commit" value="Login" required>
			</form>
		</div>

		<div class="login-help">
			<p>
				Forgot your password? <a href="index.html">Click here to reset
					it</a>.
			</p>
		</div>

		<div id="loginFooter">
			<b>Don't have an account?&nbsp;&nbsp;</b> <a
				href="${ROOT_DIR}register"><button type="button"
					style="width: 100px;">Sign-Up</button></a>
		</div>
	</div>
</div>