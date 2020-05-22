<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GoogleClosure</title>
</head>
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
<body>
<div id="hello">
</div>
Issue Id:<input id="id-input" type="text"/>
<button id="save-btn">Save</button>
<table id="issue_tracker">
			<tr>
				<th id="id-align">Issue ID</th>
				<th>Issue Title</th>
				<th>Issue Assignee</th>
				<th>Issue Priority</th>
			</tr>
		</table>
		<div id="row-count"></div>
<script src="js/closure-library/closure/goog/base.js"> </script>
<script src="js/closure.js"> </script>
<script>
example();
</script>
</body>
</html>