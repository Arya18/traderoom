<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- page script -->

<style>
.downloadbutton {
	margin-top: -40px;
}
</style>
<body class="skin-black">
	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Stock Report
				<!-- <small>advanced tables</small> -->
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="#">Stock Report</a></li>
				<li class="active">Stock Report</li>
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
						<a href="/dashboard/downloadStockReport"
							class="btn btn-success pull-right downloadbutton"><span
							class="glyphicon glyphicon-arrow-down"></span> Download Excel</a>
						<div class="box-body table-responsive">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Brand</th>
										<th>Product Type</th>
										<th>Model Number</th>
										<th>Size</th>
										<th>Available Quantity</th>
										<th>Reorder Point</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="product" items="${products}">

										<tr id="${product.id}">

											<td>${product.brand}</td>
											<td>${product.productType}</td>
											<td>${product.modelNumber}</td>
											<td>${product.size}</td>
											<td>${product.quantity}</td>
											<td>${product.reorderPoint}</td>
											<td><a
												href="/dashboard/singleProductUnitList/${product.id}"
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

