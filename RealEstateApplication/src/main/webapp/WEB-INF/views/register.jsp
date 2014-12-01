<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mainContainer">
	<h1>Sign Up</h1>

	<form method="post" action="registerRequest">
		<p>
			<input type="text" name="username" value="" placeholder="Username">
		</p>
		<p>
			<input type="password" name="password" value=""
				placeholder="Password">
		</p>
		<p>
			<input type="password" name="password2" value=""
				placeholder="Confirm Password">
		</p>
		<p>
			<input type="text" name="email" value="" placeholder="E-mail">
		</p>
		<p>
			<input type="text" name="firstName" value="" placeholder="First Name">
		</p>
		<p>
			<input type="text" name="lastName" value="" placeholder="Last Name">
		</p>
		<p>
			<input type="text" name="phone" value="" placeholder="Phone Number">
		</p>
		<p>
			<input type="text" name="address" value="" placeholder="Address">
		</p>
		<p>
			<input type="text" name="city" value="" placeholder="City">
		</p>
		<p>
			<input type="text" name="zip" value="" placeholder="Zip">
		</p>

		<p>
			<input type="checkbox" name="terms" id="terms">I agree to the
			terms and conditions. <a class="terms" href=#></a>
		</p>


		<p class="submit">
			<input type="submit" name="commit2" value="Submit">
	</form>
</div>
