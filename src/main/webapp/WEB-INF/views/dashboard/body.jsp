<style>
.table {
	width: 35%;
	margin: 0 auto;
}

.table td, table th {
	text-align: center;
}

.table th, .table td {
	overflow: hidden;
	width: 117px;
}
</style>
<html>
<body class="skin-black">

	<div class="wrapper row-offcanvas row-offcanvas-left">


		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Dashboard <small>Control panel</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol>
			</section>

			<!-- Main content -->

			<table class="table table-hover table-bordered">
				<thead>
					<th colspan="3"><span style="color: red;">Quick Link</span></th>
				</thead>

				<tbody>
					<tr>
						<td>Total Reorder Products</td>
						<td>${reorderCount}</td>
						<td><a href="/dashboard/stockReportReorderProduct"
							class="btn btn-success"><span
								class="glyphicon glyphicon-arrow-right"></span> Show</a></td>
					</tr>

					<tr>
						<td>Total Number of Due payment Sale Invoices</td>
						<td>${payDueCustomerCount}</td>
						<td><a href="/dashboard/paymentDueSalesInvoice"
							class="btn btn-success"><span
								class="glyphicon glyphicon-arrow-right"></span> Show</a></td>
					</tr>

					<tr>
						<td>Total Number of Due payment Purchase Invoices</td>
						<td>${payDueSupplierCount}</td>
						<td><a href="/dashboard/paymentDuePurchaseInvoice"
							class="btn btn-success"><span
								class="glyphicon glyphicon-arrow-right"></span> Show</a></td>
					</tr>

					<%-- <tr>
  <td>Total Number of Due payment Retailer</td>
  <td>${reorderCount}</td>
  <td><a href="#" class="btn btn-success"><span class="glyphicon glyphicon-arrow-right"></span> Show</a></td>
  </tr> --%>

				</tbody>
			</table>


		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
</body>
</html>