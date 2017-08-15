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
				Firms List
				<!-- <small>advanced tables</small> -->
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="#">Firms List</a></li>
				<li class="active">Firms List</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<!-- /.box -->

					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Firms</h3>


						</div>
						<!-- /.box-header -->
						<a href="/dashboard/addfirm"
							class="btn btn-primary btn-info pull-right addbutton"><span
							class="glyphicon glyphicon-plus"> Add Firms</span></a>

						<c:if test="${param.Update_Msg==true}">

							<div class="alert alert-success text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>Firms has
									been updated successfully</strong>
							</div>
						</c:if>

						<c:if test="${param.Success==true}">

							<div class="alert alert-success text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>Firms has
									been added successfully</strong>
							</div>
						</c:if>
						<div class="box-body table-responsive">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Name</th>
										<th>Address</th>
										<th>City-State</th>
										<th>Phone Number</th>
										<th>Tin Number</th>
										<th>GST Number</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="firm" items="${firms}">

										<tr id="${firm.id}">

											<td>${firm.name}</td>
											<td>${firm.addresLine1} - ${firm.addressLine2}</td>
											<td>${firm.city}- ${firm.state}</td>
											<td>${firm.contactNumber}</td>
											<td>${firm.tinNumber}</td>
											<td>${firm.gstNumber}</td>
											
											<td><a href="/dashboard/update-firm/${firm.id}"
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

