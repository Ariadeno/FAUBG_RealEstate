<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#banner {
	height: 650px;
}

.cb-slideshow, .cb-slideshow:after {
	height: 650px;
}

#findButton {
	background-image: url(/resources/images/find.png);
	background-repeat: no-repeat;
	background-position: center;
}
</style>
<div class="blueInputContainer">
	<div class="blueInput-wrapper">
		<!-- blueInput -->
		<section class="blueInput">
			<header class="blueInput-hdr">
				<h1
					class="blueInput-hdr-1 trapezoid trapezoid_br content_collapsed">Search</h1>
			</header>
			<div class="g g_gutterless">
				<div class="lg-1 md-1 sm-1 blueInput-body-container">
					<div class="blueInput-body">
						<form id="formblueInputBar" name="formblueInputBar" action="search"
							role="blueInput" class="blueInput-form" method="get">
							<ul class="radio-tabs targeted-blueInput">
								<li><input id="blueInputbar-type-allhomes"
									name="blueInputbartype" value="1_ah" type="radio"><label
									for="blueInputbar-type-allhomes" class="za-track-event"
									data-za-action="All Homes" data-za-category="Homepage">
										All Homes </label></li>
								<li><input value="for_sale" id="blueInputbar-type-forsale"
									type="radio" name="blueInputbartype"><label
									for="blueInputbar-type-forsale" class="za-track-event"
									data-za-action="For Sale" data-za-category="Homepage">
										For Sale </label></li>
								<li><input value="for_rent" id="blueInputbar-type-forrent"
									type="radio" name="blueInputbartype"><label
									for="blueInputbar-type-forrent" class="za-track-event"
									data-za-action="For Rent" data-za-category="Homepage">
										For Rent </label></li>
							</ul>
							<div class="blueInputbox blueInputbox_lg yui3-skin-sam"
								id="yui_3_15_0_1_1417199162936_106">
								<label class="hide-visually" for="citystatezip">Search</label><input
									placeholder="Search Name" tabindex="1"
									class="blueInput-input yui3-aclist-input" type="text"
									autocomplete="off" data-default-placeholder="Search Name"
									id="name" maxlength="150" name="name" autocorrect="off"
									aria-autocomplete="list" aria-expanded="false"
									aria-owns="yui_3_15_0_1_1417199162936_470">
								<button type="submit" aria-label="Submit"
									class="blueInput-submit-button blueInput-button button_alt"
									id="findButton">
								</button>
								<div id="yui_3_15_0_1_1417199162936_451"
									class="yui3-widget yui3-aclist yui3-widget-positioned yui3-aclist-hidden"
									style="width: 660px; left: 0px; top: 40px; z-index: 99; text-align: left;"
									aria-hidden="true">
									<div id="yui_3_15_0_1_1417199162936_453"
										class="yui3-aclist-content" style="box-shadow: none;">
										<ul class="yui3-aclist-list"
											id="yui_3_15_0_1_1417199162936_470" role="listbox"></ul>
									</div>
								</div>
								<div class="yui3-aclist-aria" aria-live="polite" role="status"></div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>
<div class="mainContainer">
	<h1>Welcome</h1>
	<hr />
	<div class="box">
		<h3>Main Story</h3>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam
			eget est turpis. Nam interdum vestibulum erat, sed aliquet tortor
			interdum sed. Curabitur nec ante tempor.</p>
	</div>
	<div class="box">
		<h3>Onter note</h3>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Quisque quis massa in urna consequat vehicula. Nullam lacinia
			imperdiet lorem, quis egestas augue vehicula non. Donec bibendum
			massa ligula, sit amet lacinia quam feugiat sed. Sed.</p>
	</div>
	<div class="box">
		<h3>Lorem ipsum</h3>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
			Aliquam ut neque quis purus auctor consequat sed ac tortor. Class
			aptent taciti sociosqu ad litora torquent per conubia nostra, per
			inceptos himenaeos. Cras non eros ut eros congue vulputate.
			Pellentesque habitant.</p>
	</div>
	<div class="box">
		<h3>Master Exploder</h3>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla
			vestibulum ante quis elit maximus placerat. Vestibulum ante ipsum
			primis in.</p>
	</div>
</div>