<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- page script -->

<body class="skin-black">
	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Customer Report
				<!-- <small>advanced tables</small> -->
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="#">Customer Report</a></li>
				<li class="active">Customer Report</li>
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
						<div class="box-body table-responsive">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Customer name</th>
										<th>Phone</th>
										<th>Invoice Number</th>
										<th>Invoice Amount</th>
										<th>Amount Paid</th>
										<th>Amount Left</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="saleInvoice" items="${saleInvoices}">

										<tr>
											<td>${saleInvoice.customer.name}</td>
											<td>${saleInvoice.customer.contactNo}</td>
											<td><a
												href="/dashboard/finalSaleInvoice?si=${saleInvoice.saleInvoiceNo}">${saleInvoice.cmpySaleInvoiceNo}</a>
												(${saleInvoice.invoiceDate})</td>
											<td>${saleInvoice.finalAmount}</td>
											<td>${saleInvoice.amountPaid}</td>
											<td>${saleInvoice.balanceLeft}</td>

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

