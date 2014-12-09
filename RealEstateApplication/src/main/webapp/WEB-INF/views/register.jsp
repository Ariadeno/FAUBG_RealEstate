<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mainContainer">
	<div style="width: 250px; margin-left: auto; margin-right: auto;">
		<h1>Sign Up</h1>

		<form method="post" action="registerRequest">
			<p>
				<input type="text" name="username" value="" placeholder="Username" required>
			</p>
			<p>
				<input type="password" name="password" value="" placeholder="Password" required>
			</p>
			<p>
				<input type="password" name="password2" value="" placeholder="Confirm Password" required>
			</p>
			<p>
				<input type="text" name="email" value="" placeholder="E-mail" required>
			</p>
			<p>
				<input type="text" name="firstName" value="" placeholder="First Name" required>
			</p>
			<p>
				<input type="text" name="lastName" value="" placeholder="Last Name" required>
			</p>
			<p>
				<input type="text" name="phone" value="" placeholder="Phone Number" required>
			</p>
			<p>
				<input type="text" name="address" value="" placeholder="Address" required>
			</p>
			<p>
				<input type="text" name="city" value="" placeholder="City" required>
			</p>
			<p>
				<input type="text" name="zip" value="" placeholder="Zip" required>
			</p>

			<p>
				<input type="checkbox" name="terms" id="terms">I agree to the terms and conditions. <a class="terms" href=#></a>
			</p>


			<p class="submit">
				<input type="submit" name="commit2" value="Submit">
		</form>
	</div>
</div>
