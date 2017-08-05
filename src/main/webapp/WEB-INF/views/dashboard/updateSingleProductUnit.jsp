<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<body class="skin-black">

	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				update Single Products
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
						<div class="box-header"></div>
						<!-- /.box-header -->
						<!-- form start -->

						<form role="form"
							action="/dashboard/update-unitLocation/${productPurchaseInvoice.id}"
							method="post">
							<c:if test="${hasError eq true}">

								<div class="alert alert-danger text-center">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${message}</strong>
								</div>
							</c:if>

							<div class="box-body">
								<div class="col-md-6">
									<div class="form-group">
										<label for="unitPrice">Unit Price</label> <input type="text"
											name="unitPrice" id="unitPrice"
											value="${productPurchaseInvoice.unitPrice}"
											class="form-control">

									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="discountRate">Discount Rate</label> <input
											type="text" name="discountRate"
											value="${productPurchaseInvoice.discountRate}"
											id="discountRate" class="form-control">
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label for="modelNumber">Serial No</label> <input type="text"
											placeholder="Serial No." id="serialNo" name="serialNo"
											value="${productPurchaseInvoice.serialNo}"
											class="form-control">
									</div>
								</div>

								<%--     <div class="col-md-6">
										<div class="form-group">
											<label for="sale">Sale Stauts</label> 
											<select class="form-control" name="sale"
												id="sale">
												<option value="0" <c:if test="${productPurchaseInvoice.sale==0}">selected</c:if>>Not Sale</option>
												<option value="1" <c:if test="${productPurchaseInvoice.sale==1}">selected</c:if>>Saled</option>
											</select>
										</div>
									</div> --%>

								<c:if
									test="${productPurchaseInvoice.indoorSerialNo!=null || productPurchaseInvoice.indoorSerialNo!=''}">
									<div class="col-md-6">
										<div class="form-group">
											<label for="modelNumber">Indoor Serial No</label> <input
												type="text" placeholder="Indoor Serial No."
												id="indoorSerialNo" name="indoorSerialNo"
												value="${productPurchaseInvoice.indoorSerialNo}"
												class="form-control">
										</div>
									</div>

									<%--   <div class="col-md-6">
										<div class="form-group">
											<label for="sale">Indoor Sale Stauts</label> 
											<select class="form-control" name="sale"
												id="sale">
												<option value="0" <c:if test="${productPurchaseInvoice.indoorsale==0}">selected</c:if>>Not Sale</option>
												<option value="1" <c:if test="${productPurchaseInvoice.indoorsale==1}">selected</c:if>>Saled</option>
											</select>
										</div>
									</div> --%>
								</c:if>
								<div class="col-md-6">
									<div class="form-group">
										<label for="sale">Location</label> <select
											class="form-control" name="location" id="location">
											<option value="">--Select--</option>
											<option value="Shop"
												<c:if test="${productPurchaseInvoice.location eq 'Shop'}">selected</c:if>>Shop</option>
											<option value="Godown"
												<c:if test="${productPurchaseInvoice.location eq 'Godown'}">selected</c:if>>Godown</option>
										</select>
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

		<!-- Main content -->
		<section class="content"></section>
		<!-- /.content -->
	</aside>
	<!-- /.right-side -->
</body>