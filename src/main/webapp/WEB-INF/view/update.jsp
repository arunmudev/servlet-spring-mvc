<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Update Existing Issue</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"
	type="text/javascript"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/update-existing-issue.js"></script>

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

#add-btn{
   background-color: #1294F7;
}
</style>
</head>
<body>
	<div class="container">
		<h1>
			Issue Tracker <small>by Servlet jquery</small>
		</h1>
		<div class="jumbotron">
			<h3>Update Existing Issue:</h3>
			<form id="issue-update-form">
				<div class="form-group">
					<label for="id-input">Issue Id</label> <input id="id-input"
						class="form-control" type="text" readonly="readonly"/>
				</div>

				<div class="form-group">
					<label for="title-input">Issue Title</label> <input
						id="issue-input" class="form-control" type="text" />
				</div>

				<div class="form-group">
					<label for="author-input">Assignee</label> <input
						id="assignee-input" class="form-control" type="text" />
				</div>

				<div class="form-group">
					<label for="price-input">Priority</label> <select
						id="priority-input">
						<option value="Low">Low</option>
						<option value="Medium">Medium</option>
						<option value="High">High</option>
					</select>
				</div>
			</form>
		</div>
			<button class="btn btn-primary" id="exit-btn">Exit</button>
			<button class="btn btn-primary" id="save-btn">Save</button>
	</div>

	<script src="http://chancejs.com/chance.min.js"></script>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>