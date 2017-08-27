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
	<section class="content" >
		<div class="row">
    		<div class="col-lg-4 col-xs-6">
		      <!-- small box -->
			      <div class="small-box bg-aqua">
			        <div class="inner">
			          <h3>${reorderCount}</h3>
			          <p>Net Reorder Products</p>
			        </div>
			        <!-- div class="icon">
			          <i class="ion ion-bag"></i>
			        </div> -->
			        <a href="/dashboard/stockReportReorderProduct" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
			      </div>
	    	</div>
        <!-- ./col -->
			<div class="col-lg-4 col-xs-6">
	          <!-- small box -->
	          <div class="small-box bg-green">
	            <div class="inner">
	              <h3>${payDueCustomerCount}</h3>

	              <p>Net due payment Sale Invoices</p>
	            </div>
	            <!-- <div class="icon">
	              <i class="ion ion-stats-bars"></i>
	            </div> -->
	            <a href="/dashboard/paymentDueSalesInvoice" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
	        <!-- ./col -->
	        <div class="col-lg-4 col-xs-6">
	          <!-- small box -->
	          <div class="small-box bg-yellow">
	            <div class="inner">
	              <h3>${payDueSupplierCount}</h3>

	              <p>Net due payment Purchase Invoices</p>
	            </div>
	            <!-- <div class="icon">
	              <i class="ion ion-person-add"></i>
	            </div> -->
	            <a href="/dashboard/paymentDuePurchaseInvoice" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
	        <!-- ./col -->
	      	</div>
  	</section>

			<!-- Main content -->
			<div class="col-md-6">
			<table class="table table-hover table-bordered">
				<thead>
					<th colspan="6"><span style="color: red;">Top 5 sale product</span></th>
				</thead>
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
					<%-- <tr>
  <td>Total Number of Due payment Retailer</td>
  <td>${reorderCount}</td>
  <td><a href="#" class="btn btn-success"><span class="glyphicon glyphicon-arrow-right"></span> Show</a></td>
  </tr> --%>
				</tbody>
			</table>
			</div>
			
						<!-- Table row -->
						<div class="col-md-6">
					<table class="table table-hover table-bordered">
						<thead>
							<th colspan="6"><span style="color: red;">Top 5 purchase product</span></th>
						</thead>
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
				</div>
				<!-- /.col -->
				</aside>
			</div>
			
			<!-- Table row -->
		
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
</body>
</html>