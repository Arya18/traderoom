<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<style>
.error_brand {
	display: inline-block;
	margin-left: 12px;
	color: red;
}
</style>
<body class="skin-black">

	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				update Firm
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
							<h3 class="box-title">Update Firm</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->

						<form role="form" id="updateProductForm"
							action="/dashboard/update-firm/${firm.id}" method="post">
							<c:if test="${hasError eq true}">

								<div class="alert alert-danger text-center">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${message}</strong>
								</div>
							</c:if>

							<div class="box-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="brand">Name</label>
											<div class="error_brand hidden"></div>
											<input type="text" placeholder="Name" name="name"
												id="name" value="${firm.name}" class="form-control">

										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="productType">AddressLine1</label> <input
												type="text" placeholder="AddressLine1"
												name="addresLine1" value="${firm.addresLine1}"
												id="addresLine1" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="brand">Short Name</label>
											<div class="error_brand hidden"></div>
											<input type="text" placeholder=" Short Name" name="shortName"
												id="shortName" value="${firm.shortName}" class="form-control">

										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="productType">Email</label> <input
												type="text" placeholder="Email"
												name="email" value="${firm.email}"
												id="eamil" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="modelNumber">AddressLine2</label> <input
												type="text" placeholder="Address Line2" id="addressLine2"
												name="addressLine2" value="${firm.addressLine2}"
												class="form-control">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="size">City</label> <input type="text"
												placeholder="city" name="city" id="city"
												value="${firm.city}" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="quantity">State</label> <input
												type="text" placeholder="Available Quantity" id="quantity"
												 name="state" value="${firm.state}"
												class="form-control">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="reorderPoint">Tin Number</label> <input
												type="text" placeholder="Tin no" id="tinNumber" name="tinNumber" value="${firm.tinNumber}"
												class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="size">GST Number</label> <input type="text"
												placeholder="GST Number" name="gstNumber"
												id="gstNumber" value="${firm.gstNumber}" class="form-control">
											<div class="help-block"></div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="size">Contact Number</label> <input type="text"
												placeholder="Contact Number" name="contactNumber"
												id="contactNumber" value="${firm.contactNumber}" class="form-control">
											<div class="help-block"></div>
										</div>
									</div>
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button class="btn btn-primary" type="submit"
									id="updateProductButton" value="Submit">Submit</button>
							</div>
						</form>
					</div>
					<!-- /.box -->

					<!-- Form Element sizes -->


				</div>
			</div>
		</section>

		<!-- Main content -->
		<section class="content"></section>
		<!-- /.content -->
	</aside>
	<!-- /.right-side -->
</body>

<script>
                	
    
            </script>