<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- page script -->

<body class="skin-black">
	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Supplier Report
				<!-- <small>advanced tables</small> -->
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="#">Supplier Report</a></li>
				<li class="active">Supplier Report</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<!-- /.box -->

					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Data Table With Full Features</h3>
						</div>
						<!-- /.box-header -->

						<c:if test="${param.Update_Msg==true}">

							<div class="alert alert-success text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>Purchase
									Invoice has been updated successfully</strong>
							</div>
						</c:if>

						<c:if test="${param.hasError eq true}">

							<div class="alert alert-danger text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>${param.message}</strong>
							</div>
						</c:if>

						<div class="box-body table-responsive">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Supplier name</th>
										<th>Phone</th>
										<th>Invoice Number</th>
										<th>Invoice Amount</th>
										<th>Amount Paid</th>
										<th>Amount Left</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="purchaseInv" items="${purchaseInvoice}">

										<tr>
											<td>${purchaseInv.supplier.name}</td>
											<td>${purchaseInv.supplier.contactNo}</td>
											<td><a
												href="/dashboard/finalPurchaseInvoice?pi=${purchaseInv.invoiceNo}">${purchaseInv.cmpyPurchaseInvoiceNo}</a>
												(${purchaseInv.date})</td>
											<td>${purchaseInv.finalAmount}</td>
											<td>${purchaseInv.amountPaid}</td>
											<td>${purchaseInv.balanceLeft}</td>
											<td><a
												href="/dashboard/update-purchaseInvoice/${purchaseInv.invoiceNo}"
												class="btn btn-sm btn-success"><span
													class="glyphicon glyphicon-pencil"></span> Edit</a></td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
			</div>

		</section>
		<!-- /.content -->
	</aside>
	<!-- /.right-side -->
	<script type="text/javascript">
        $(document).ready(function() {
        	
            	console.log("sad");
            	jQuery("#example1").dataTable();
                console.log("sad");
                $('#example2').dataTable({
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": true,
                    "bAutoWidth": false
                });
            });
        </script>
</body>

