<%@ page language="java" contentType="text/html; charset=UTF-8" import="leave.system.Employee"
	pageEncoding="UTF-8"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" />
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#startDate").datepicker({
			dateFormat : "yy-mm-dd"
		});
	});
</script>
<title>Add An Employee</title>
</head>
<body>
	<br>
	<br>

	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<h2 align="center">Add Emplyee</h2>
				
				<% if(request.getAttribute("successMsg") != null) { %>
					<div style="border: 2px solid green; border-radius: 5px;">
						<ul style="color: green; padding: 10px 10px 5px 30px;">
							<li><% out.print(request.getAttribute("successMsg")); %></li>
						</ul>
					</div>
				<% } else if (request.getAttribute("errorMsg") != null) { %>
					
					<div style="border: 2px solid red; border-radius: 5px;">
						<ul style="color: red; padding: 10px 10px 5px 30px;">
							<li><% out.print(request.getAttribute("errorMsg")); %></li>
						</ul>
					</div>
					
				<% } %>
				
				
				<form class="form-horizontal" action="addUser"
					style="margin-top: 30px;" method="post">
					<div class="form-group">
						<label class="control-label col-sm-3" for="firstname">Firstname:</label>
						<div class="col-sm-9">
							<input type="text" name="firstname" class="form-control"
								id="firstname">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="lastname">Lastname:</label>
						<div class="col-sm-9">
							<input type="text" name="lastname" class="form-control"
								id="lastname">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="idNumber">ID
							Number:</label>
						<div class="col-sm-9">
							<input type="text" name="idNumber" class="form-control"
								id="idNumber">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="startDate">Start
							Date:</label>
						<div class="col-sm-9">
							<input type="text" name="startDate" class="form-control"
								id="startDate">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="startDate">Minimum
							leave days(per year):</label>
						<div class="col-sm-9">
							<input type="text" name="minLeaveDaysAllocated"
								class="form-control" id="minLeaveDaysAllocated">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-block btn-primary">Submit</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
</body>
</html>