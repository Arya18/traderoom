<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<body class="skin-black">

	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Update Sale Invoice Balance
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
							<h3 class="box-title">
								Update Sale Invoice Balance &nbsp &nbsp &nbsp <b>Final
									Amount: ${finalAmount}</b>
							</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->

						<form role="form" id="updateSaleinvoiceForm"
							action="/dashboard/update-saleInvoice/${saleInvoiceId}"
							method="post">

							<div class="box-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="amountPaid">Amount Paid</label> <input
												type="text" name="amountPaid" id="amountPaid"
												value="${amountPaid}" class="form-control">

										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="balanceLeft">Balance Left</label> <input
												type="text" name="balanceLeft" value="${balanceLeft}"
												id="balanceLeft" class="form-control">
										</div>
									</div>
								</div>



							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button class="btn btn-primary" type="submit"
									id="updateSaleinvoiceButton" value="Submit">Submit</button>
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