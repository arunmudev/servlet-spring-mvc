<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Servlet Db project</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.css"
	rel="stylesheet">
<script
	src="https://unpkg.com/material-components-web@latest/dist/material-components-web.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"
	type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="js/servlet-db.js"></script>
<script src="js/closure-library/closure/goog/base.js"></script>


<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<div>
		<h1>
			Issue Tracker <small>by Servlet jquery</small>
		</h1>
		<button class="btn btn-primary" id="add-btn">Add</button>
		<button class="btn btn-primary" id="edit-btn">Edit</button>
		<button class="btn btn-primary" id="delete-btn">Delete</button>
	</div>
	<div id="anotherSection">
		<fieldset>
			<legend>Response from jQuery Ajax Request</legend>
			<div id="ajaxResponse"></div>
		</fieldset>

		<table id="issue_tracker">
			<tr>
				<th id="id-align">Issue ID</th>
				<th>Issue Title</th>
				<th>Issue Assignee</th>
				<th>Issue Priority</th>
			</tr>
		</table>
		<div id="row-count"></div>
	</div>
	<!-- <div class="alert alert-success">Well done! You successfully deleted this Issue Info
	</div> -->
	<button type="float" class="btn btn-primary bmd-btn-fab">
           <i class="material-icons">add</i>
     </button>
	<script src="http://chancejs.com/chance.min.js"></script>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
		<script src="js/closure-library/main.js"></script>
</body>
</html>