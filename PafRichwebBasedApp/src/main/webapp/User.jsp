<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>GadgetBadget User management System</h1>
				<form id="formUser" name="formUser" method="post" action="User.jsp">
					User name: <input id="userName" name="userName" type="text"
						class="form-control form-control-sm"> <br> Phone number:
					<input id="phone_no" name="phone_no" type="text"
						class="form-control form-control-sm"> <br>Address
						<input id="address" name="address" type="text"
						class="form-control form-control-sm"> <br>Designation
						<input id="designation" name="designation" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidUserIDSave" name="hidUserIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divUserGrid">
					<%
					User userObj = new User();
					out.print(userObj.readUsers());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>