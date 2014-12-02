<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value="/resources/css/search-box.css" />"
	type="text/css" rel="stylesheet" media="all">
<style>
#banner {
	height: 650px;
}

.cb-slideshow, .cb-slideshow:after {
	height: 650px;
}

.searchContainer {
	width: 100%;
	min-width:360px;
	max-width: 1920px;
	border-radius: 5px;
	position: absolute;
	top: 250px;
	margin-left: 0px;
}

@media screen and (max-width: 52em) {
	.centerDiv {
		/*display: none;*/
		
	}
}
</style>
<div class="searchContainer">
	<div class="search-wrapper">
		<!-- Search -->
		<section class="search">
			<header class="search-hdr">
				<h1
					class="search-hdr-1 trapezoid trapezoid_br content_collapsed">Search</h1>
			</header>
			<div class="g g_gutterless">
				<div class="lg-1 md-1 sm-1 search-body-container">
					<div class="search-body">
						<form id="formSearchBar" name="formSearchBar" action="search"
							role="search" class="search-form" method="get">
							<ul class="radio-tabs targeted-search">
								<li><input id="searchbar-type-allhomes"
									name="searchbartype" value="1_ah" type="radio"><label
									for="searchbar-type-allhomes" class="za-track-event"
									data-za-action="All Homes" data-za-category="Homepage">
										All Homes </label></li>
								<li><input value="for_sale" id="searchbar-type-forsale"
									type="radio" name="searchbartype"><label
									for="searchbar-type-forsale" class="za-track-event"
									data-za-action="For Sale" data-za-category="Homepage">
										For Sale </label></li>
								<li><input value="for_rent" id="searchbar-type-forrent"
									type="radio" name="searchbartype"><label
									for="searchbar-type-forrent" class="za-track-event"
									data-za-action="For Rent" data-za-category="Homepage">
										For Rent </label></li>
							</ul>
							<div class="searchbox searchbox_lg yui3-skin-sam"
								id="yui_3_15_0_1_1417199162936_106">
								<label class="hide-visually" for="citystatezip">Search</label><input
									placeholder="Search Name" tabindex="1"
									class="search-input yui3-aclist-input" type="text"
									autocomplete="off" data-default-placeholder="Search Name"
									id="name" maxlength="150" name="name" autocorrect="off"
									aria-autocomplete="list" aria-expanded="false"
									aria-owns="yui_3_15_0_1_1417199162936_470">
								<button type="submit" aria-label="Submit"
									class="search-button button_alt"
									id="yui_3_15_0_1_1417199162936_1062">
									<span>Find</span>
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