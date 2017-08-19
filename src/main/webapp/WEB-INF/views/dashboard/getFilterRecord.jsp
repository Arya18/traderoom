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
			
					<div class="col-md-12" style="margin-bottom: 10px;">

					<form id="filterForm" method="get"
						action="/dashboard/getStockByFilterRecord">
						<div class="alert alert-danger text-center hidden"
							id="filterError">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Please Choose Any input for filter value</strong>
						</div>
							<c:if test="${hasError eq true}">

								<div class="alert alert-danger text-center">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${message}</strong>
								</div>
							</c:if>
						<div class="control-group col-md-12">
						
						<div class="col-md-2">
								<label class="control-label">Firm Name</label>
								<div class="controls">
									<select class="form-control" name="firmName"
										id="indoorSerialNo">
										<option value="">--Select--</option>
										<c:forEach items="${firmName}" var="firmName">
										<option value="${firmName}">${firmName}</option>
										</c:forEach>
									</select>
								</div>
						</div>
						
						<div class="col-md-2">
								<label class="control-label">Unit(In case of Split Ac)</label>
								<div class="controls">
									<select class="form-control" name="unit" id="unit">
										<option value="">--Select--</option>
										<option value="I">Indoor Unit</option>
										<option value="O">Outdoor unit</option>
									</select>
								</div>
							</div>
							
							<div class="col-md-2">
								<label class="control-label">Brand</label>
								<div class="controls">
									<select class="form-control" name="brandName" id="brandName">
										<option value="">--Select--</option>
										<c:forEach items="${brandName}" var="brandName">
										<option value="${brandName}">${brandName}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-md-2">
								<label class="control-label">Model Number</label>
								<div class="controls">
									<select class="form-control" name="modelnumber"
										id="modelnumber">
										<option value="">--Select--</option>
									<c:forEach items="${modelnumber}" var="modelnumber">
										<option value="${modelnumber}">${modelnumber}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-md-2">
								<label class="control-label">Size</label>
								<div class="controls">
									<select class="form-control" name="size"
										id="indoorSerialNo">
										<option value="">--Select--</option>
										<c:forEach items="${size}" var="size">
										<option value="${size}">${size}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-md-2">
								<label class="control-label">Star</label>
								<div class="controls">
									<select class="form-control" name="starName"
										id="starName">
										<option value="">--Select--</option>
										<c:forEach items="${star}" var="starName">
										<option value="${starName}">${starName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-md-2">
								<label class="control-label">Location</label>
								<div class="controls">
									<select class="form-control" name="location"
										id="location">
										<option value="">--Select--</option>
										<option value="IUG">Indoor Unit in Godawn</option>
										<option value="IUS">Indoor Unit in Shop</option>
										<option value="UG">Unit in Godown</option>
										<option value="US">Unit in Shop</option>
									</select>
								</div>
							</div>
								<div class="col-md-1 clearfix">
								<label class="control-label"> </label>
								<div class="controls">
									<input type="submit" class="btn btn-success btn-pass "
										id="search" value="Search"/>
								</div>
							</div>
							
								<div class="col-md-2 clearfix">
								<label class="control-label"> </label>
								<div class="controls">
									<a href="/dashboard/downloadFilterStockReport"
							class="btn btn-success pull-right"><span
							class="glyphicon glyphicon-arrow-down"></span> Download Excel</a>
								</div>
							</div>
							
						</div>
					</form>
				</div> 
				<div class="col-xs-12">
					<!-- /.box -->

					<div class="box">
						<div class="box-header">
							<h3 class="box-title"> Record By Filter</h3>
						</div>
						<!-- /.box-header -->
						<a href="/dashboard/downloadFilterStockReport"
							class="btn btn-success pull-right downloadbutton"><span
							class="glyphicon glyphicon-arrow-down"></span> Download Excel</a>
						<div class="box-body table-responsive">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Brand</th>
										<th>Model Number</th>
										<th>Size</th>
										<th>Star</th>
										<th>Quantity</th>
										<th>Price</th>
										<th>Firm Name</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="finalReport" items="${finalStock}">

										<tr>

											<td>${finalReport.brandName}</td>
											<td>${finalReport.modelNumber}</td>
											<td>${finalReport.size}</td>
											<td>${finalReport.star}</td>
											<td>${finalReport.quantity}</td>
											<td>${finalReport.unitPrice}</td>
											<td>${finalReport.firmName}</td>
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
                //$("#filterForm").submit();
            });
        </script>
</body>

