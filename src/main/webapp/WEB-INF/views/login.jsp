<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:if test="${not empty hasError}">
	<div class="alert alert-danger text-center">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>${message} </strong>
	</div>
</c:if>
<div class="form-box" id="login-box">
	<div class="error_container"></div>
	<div class="header">Sign In</div>
	<form method="post" action="/login">
		<div class="body bg-gray">
			<div class="form-group">
				<input type="text" name="email" id="email" class="form-control"
					placeholder="Email" />
			</div>
			<div class="form-group">
				<input type="password" name="password" id="pass"
					class="form-control" placeholder="Password" />
			</div>
			<div class="form-group">
				<select class="form-control" id="role_value" name="role_value">
					<option value="-1">--Login As--</option>
					<option value="Admin">Admin</option>
					<option value="Checker">Checker</option>
					<option value="Maker">Maker</option>
					<option value="Sales Person">Sales Person</option>
				</select>

			</div>
			<!-- <div class="form-group">
                        <input type="checkbox" name="remember_me"/> Remember me
                    </div> -->
		</div>
		<div class="footer">
			<button type="submit" class="btn bg-olive btn-block">Sign me
				in</button>

			<p>
				<a href="/forgot">I forgot my password</a>
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
     /*   function checkLoginDetails(){
    	   var email=$('#email').val();
    	   var password=$('#pass').val();
    	   var role=$('#role_value').val();
    	  
    	   if(email.trim().length ==0 ||password.trim().length ==0 || role=='-1'){
    		   $('.error_container').html("User Id or Password or Login As cannot be null");
    		   return false;
    	   }
    	   loginRequest ={};
    	   loginRequest["userid"] =userid;
    	   loginRequest["password"] =password;
			
    	   var url=null;
			 if(role=='Admin'){
				 url="/admin/login/";
			 }
			 else if(role=='Admin'){
				 url="/checker/login/";
			 }
			 else if(role=='Admin'){
				 url="/maker/login/";
			 }
			 else{
				 url="/salesPerson/login/";
				 }
			 
          
           jQuery.ajax({
               type : "POST",
               contentType: "application/json",
               url :url,
               data : JSON.stringify(loginRequest),
               dataType: "text",
               success : function(response, status, code){
          		var returnedData = JSON.parse(response);
                  
          		if(returnedData.login=='successful');
                   {
                	   window.location.href="/dashboard/";
                   }
                   if(returnedData.login=='unsuccessful'){
                	   $('.error_container').html("User Id or password is wrong");
                   }
               },
               error : function(response, status, code){
            	   $('.error_container').html("Some Error Occured,Please try later");
               }
           });
       } */
       </script>