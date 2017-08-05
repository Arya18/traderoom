<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty hasError}">

		<div class="alert alert-danger text-center">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>${message} </strong>
		</div>

	</c:if>

	<c:if test="${SUCCESS==true}">

		<div class="alert alert-info text-center">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Success! </strong>Admin Added successfully.
		</div>

	</c:if>
	<div class="form-box" id="login-box">



		<div class="header">Register New Membership</div>
		<form method="post" action="/signup">
			<div class="body bg-gray">
				<div class="form-group">
					<input type="text" name="name" class="form-control"
						placeholder="Full name" required />
				</div>
				<div class="form-group">
					<input type="text" name="email" class="form-control"
						placeholder="Email" required />
				</div>
				<div class="form-group">
					<input type="text" name="username" class="form-control"
						placeholder="User ID" required />
				</div>
				<div class="form-group">
					<input type="password" name="password" class="form-control"
						placeholder="Password" required />
				</div>
				<div class="form-group">
					<input type="password" name="password2" class="form-control"
						placeholder="Retype password" required />
				</div>
			</div>
			<div class="footer">

				<button type="submit" class="btn bg-olive btn-block">Sign
					me up</button>

				<a href="/login" class="text-center">I already have a membership</a>
			</div>
		</form>

		<div class="margin text-center">
			<span>Register using social networks</span> <br />
			<button class="btn bg-light-blue btn-circle">
				<i class="fa fa-facebook"></i>
			</button>
			<button class="btn bg-aqua btn-circle">
				<i class="fa fa-twitter"></i>
			</button>
			<button class="btn bg-red btn-circle">
				<i class="fa fa-google-plus"></i>
			</button>

		</div>
	</div>
</body>
</html>