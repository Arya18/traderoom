<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body class="skin-black">

	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				My profile
				<!-- <small>Control panel</small> -->
			</h1>
			<br />
			<!-- <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Blank page</li>
                    </ol> -->
			<div class="row">
				<div class="col-md-12">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">My profile</h3>


						</div>
						<!-- /.box-header -->
						<!-- form start -->

						<form role="form" action="/dashboard/myprofile" method="post">
							<c:if test="${param.hasError eq true}">

								<div class="alert alert-danger text-center">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${param.message}</strong>
								</div>
							</c:if>

							<c:if test="${param.Update_Msg==true}">

								<div class="alert alert-success text-center">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>Your Profile
										has been updated successfully</strong>
								</div>
							</c:if>


							<div class="box-body">
								<div class="col-md-6">
									<div class="form-group">
										<label for="name">Name</label> <input type="text"
											placeholder="Name" name="name" id="name"
											value="${currentUser.name}" class="form-control">

									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="address">Address</label> <input type="text"
											placeholder="Address" name="address" id="address"
											value="${currentUser.address}" class="form-control">
									</div>
								</div>

								<%-- Email can be be editable because here email is not verified via link,and directly update it into DB  --%>
								<div class="col-md-6">
									<div class="form-group">
										<label for="size">Email</label> <input type="text"
											placeholder="Email" name="email" id="email"
											value="${currentUser.email}" class="form-control">
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label for="size">User name</label> <input type="text"
											placeholder="user Name" name="username" id="username"
											value="${currentUser.username}" class="form-control">
									</div>
								</div>


							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button class="btn btn-primary" type="submit">Submit</button>
							</div>
						</form>
					</div>
					<!-- /.box -->

					<!-- Form Element sizes -->


				</div>
			</div>
		</section>
		f
		<!-- Main content -->
		<section class="content"></section>
		<!-- /.content -->
	</aside>
	<!-- /.right-side -->
</body>