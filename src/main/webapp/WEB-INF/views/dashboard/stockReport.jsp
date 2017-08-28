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
			
			<!-- 		<div class="col-md-12" style="margin-bottom: 10px;">

					<form id="filterForm" method="get"
						action="/dashboard/getSingleProductUnitByFilter">
						<div class="alert alert-danger text-center hidden"
							id="filterError">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Please Choose Any input for filter value</strong>
						</div>
						<div class="control-group col-md-12">
						
						<div class="col-md-2">
								<label class="control-label">Firm Name</label>
								<div class="controls">
									<select class="form-control" name="indoorSerialNo"
										id="indoorSerialNo">
										<option value="">--Select--</option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
						</div>
							
							<div class="col-md-2">
								<label class="control-label">Brand</label>
								<div class="controls">
									<select class="form-control" name="serialNo" id="serialNo">
										<option value="">--Select--</option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
							</div>

							<div class="col-md-2">
								<label class="control-label">Model Number</label>
								<div class="controls">
									<select class="form-control" name="indoorSerialNo"
										id="indoorSerialNo">
										<option value="">--Select--</option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
							</div>
							
							<div class="col-md-2">
								<label class="control-label">Size</label>
								<div class="controls">
									<select class="form-control" name="indoorSerialNo"
										id="indoorSerialNo">
										<option value="">--Select--</option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
							</div>
							
							<div class="col-md-2">
								<label class="control-label">Star</label>
								<div class="controls">
									<select class="form-control" name="indoorSerialNo"
										id="indoorSerialNo">
										<option value="">--Select--</option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
							</div>
								<div class="col-md-1 clearfix">
								<label class="control-label"> </label>
								<div class="controls">
									<button type="button" class="btn btn-success btn-pass "
										id="search" value="Search">Search</button>
								</div>
							</div>
							
							<div class="col-md-1 clearfix">
								<label class="control-label"> </label>
								<div class="controls">
									<button type="button" class="btn btn-success btn-pass"
										id="search" value="Search">Download Excel</button>
								</div>
							</div>
						</div>
						<div class="control-group col-md-12">
							<div class="col-md-3 pull-right">
								<label class="control-label"> </label>
								<div class="controls">
									<button type="button" class="btn btn-success btn-pass"
										id="search" value="Search">Search</button>
								</div>
							</div>
							
							<div class="col-md-3 pull-right">
								<label class="control-label"> </label>
								<div class="controls">
									<button type="button" class="btn btn-success btn-pass"
										id="search" value="Search">Search</button>
								</div>
							</div>

						</div>
					</form>
				</div> -->
				<div class="col-xs-12">
					<!-- /.box -->

					<div class="box">
						<div class="box-header">
							<h3 class="box-title"><a href="/dashboard/getFilterRecord"
							class="btn btn-info"><span
							class="glyphicon glyphicon-arrow-down"></span> Record By Filter</a></h3>
						</div>
						<!-- /.box-header -->
						<a href="/dashboard/downloadStockReport"
							class="btn btn-success pull-right downloadbutton"><span
							class="glyphicon glyphicon-arrow-down"> Download Excel</span></a>
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

