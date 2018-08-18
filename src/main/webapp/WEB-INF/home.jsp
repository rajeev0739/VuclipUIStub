<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>Vodacom SouthAfrica</title>

<!-- Add to homescreen for Chrome on Android -->
<meta name="mobile-web-app-capable" content="yes">
<link rel="icon" sizes="192x192" href="images/android-desktop.png">

<!-- Add to homescreen for Safari on iOS -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-title" content="Material Design Lite">
<link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

<!-- Tile icon for Win8 (144x144 + tile color) -->
<meta name="msapplication-TileImage"
	content="images/touch/ms-touch-icon-144x144-precomposed.png">
<meta name="msapplication-TileColor" content="#3372DF">

<link rel="shortcut icon" href="images/favicon.png">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.red-blue.min.css">
<link rel="stylesheet" href="css/styles.css">
</head>
<body
	class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header
			class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
			</div>
			<div class="mdl-layout__header-row">
				<h3><img alt="ViuLogo" src="images/viu_logo.png" style="height: 50px;">Vodacom SouthAfrica Viu</h3>
			</div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
			</div>
		</header>
		<main class="mdl-layout__content"> <!-- <div class="page-content"> -->
		<div class="mdl-grid">

			<div class="mdl-cell mdl-cell--2-col"></div>
			<div class="mdl-cell mdl-cell--8-col">
				<p>Date: ${date }</p>
			</div>
			<div class="mdl-cell mdl-cell--2-col"></div>
			
			<div class="mdl-cell mdl-cell--2-col"></div>
			<div class="mdl-cell mdl-cell--8-col">
				<p>Request Url: ${requestUrl }</p>
			</div>
			<div class="mdl-cell mdl-cell--2-col"></div>
			
			<div class="mdl-cell mdl-cell--2-col"></div>
			<div class="mdl-cell mdl-cell--8-col">
				<p>Request ip: ${ip }</p>
			</div>
			<div class="mdl-cell mdl-cell--2-col"></div>
			
			<div class="mdl-cell mdl-cell--2-col"></div>
			<div class="mdl-cell mdl-cell--8-col">
				<p>Query params: ${queryParams }</p>
			</div>
			<div class="mdl-cell mdl-cell--2-col"></div>
			
			<div class="mdl-cell mdl-cell--2-col"></div>
			<div class="mdl-cell mdl-cell--8-col">
				<p>Headers: ${headers }</p>
				<p>Decrypted msisdn: ${decryptedmsisdn }</p>
			</div>
			<div class="mdl-cell mdl-cell--2-col"></div>

		</div>
	</main>
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>
</html>
