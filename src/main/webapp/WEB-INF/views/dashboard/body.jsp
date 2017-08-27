<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			
						<!-- Table row -->
						Top 5 sale product
			<div class="row">
				<div class="col-xs-12 table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>

								<th>Product Type</th>
								<th>Brand</th>
								<th>Model Number</th>
								<th>Size</th>
								<th>Total Sale Price</th>
								<th>Total Quantity</th>
								

							</tr>
						</thead>
						<tbody>
							<c:forEach var="top5SalesProduct" items="${top5SalesProductMap}">
								<tr>
									<td>${top5SalesProduct.productType}</td>
									<td>${top5SalesProduct.brand}</td>
									<td>${top5SalesProduct.modelNumber }</td>
									<td>${top5SalesProduct.size}</td>
									<td>${top5SalesProduct.totalSale }</td>
									<td>${top5SalesProduct.quantity}</td>
									
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- /.col -->
			</div>
			
			<!-- Table row -->
						Top 5 purchase product
			<div class="row">
				<div class="col-xs-12 table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>

								<th>Product Type</th>
								<th>Brand</th>
								<th>Model Number</th>
								<th>Size</th>
								<th>Total Sale Price</th>
								<th>Total Quantity</th>
								

							</tr>
						</thead>
						<tbody>
							<c:forEach var="top5purchaseProduct" items="${top5purchaseProductMap}">
								<tr>
									<td>${top5purchaseProduct.productType}</td>
									<td>${top5purchaseProduct.brand}</td>
									<td>${top5purchaseProduct.modelNumber }</td>
									<td>${top5purchaseProduct.size}</td>
									<td>${top5purchaseProduct.totalSale }</td>
									<td>${top5purchaseProduct.quantity}</td>
									
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- /.col -->
			</div>


		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
</body>
</html>