<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.addbutton {
	margin-top: -40px;
}
</style>

<body class="skin-black">

	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Data Tables
				<!-- <small>advanced tables</small> -->
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="#">Tables</a></li>
				<li class="active">Data tables</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<!-- /.box -->

					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Products</h3>



						</div>
						<!-- /.box-header -->
						<a href="/dashboard/addsupplier"
							class="btn btn-primary btn-info pull-right addbutton"><span
							class="glyphicon glyphicon-plus"></span> Add Supplier</a>

						<c:if test="${param.Update_Msg==true}">

							<div class="alert alert-success text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>Supplier has
									been updated successfully</strong>
							</div>
						</c:if>

						<c:if test="${param.Success==true}">

							<div class="alert alert-success text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>Supplier has
									been added successfully</strong>
							</div>
						</c:if>

						<div class="box-body table-responsive">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Name</th>
										<th>Address</th>
										<th>Email</th>
										<th>Phone</th>
										<th>GST No.</th>
										<th>Tin No.</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="supplier" items="${suppliers}">

										<tr id="${supplier.id}">

											<td>${supplier.name}</td>
											<td>${supplier.address}</td>
											<td>${supplier.email}</td>
											<td>${supplier.contactNo}</td>
											<td>${supplier.tinNumber}</td>
											<td>${supplier.gstNumber}</td>

											<td><a href="/dashboard/update-supplier/${supplier.id}"
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

