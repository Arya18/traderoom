<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:if test="${not empty hasError}">
	<div class="alert alert-danger text-center">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>${message} </strong>
	</div>
</c:if>
<c:if test="${Reset eq true}">
	<div class="alert alert-success text-center">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>password reset successfully </strong>
	</div>
</c:if>
<div class="form-box" id="login-box">
	<div class="error_container"></div>
	<div class="header">Forgot</div>
	<form method="post" action="/forgot">
		<div class="body bg-gray">
			<div class="form-group">
				<input type="text" name="email" id="email" class="form-control"
					placeholder="Email" />
			</div>
			<div class="form-group">
				<input type="password" name="password" id="pass"
					class="form-control" placeholder="New Password" />
			</div>
			<div class="form-group">
				<select class="form-control" id="role_value" name="role_value">
					<option value="-1">--Select Role--</option>
					<option value="Admin">Admin</option>
					<option value="Checker">Checker</option>
					<option value="Maker">Maker</option>
					<option value="Sales Person">Sales Person</option>
				</select>

			</div>

			<div class="footer">
				<button type="submit" class="btn bg-olive btn-block">Forgot</button>

				<p>
					<a href="/login">Login</a>
				</p>

				<a href="/signup" class="text-center">Register a new membership</a>
			</div>
	</form>

	<div class="margin text-center">
		<span>Sign in using social networks</span> <br />
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

<script>
    
       </script>