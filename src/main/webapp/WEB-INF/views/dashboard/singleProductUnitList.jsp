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
				Get product By Filter
				<!-- <small>advanced tables</small> -->
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="#">Update product location</a></li>
				<li class="active">Update product location</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">

				<div class="col-md-12" style="margin-bottom: 10px;">

					<form id="filterForm" method="get"
						action="/dashboard/getSingleProductUnitByFilter">
						<div class="alert alert-danger text-center hidden"
							id="filterError">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Please Choose Any input for filter value</strong>
						</div>
						<div class="control-group">

							<div class="col-md-4">
								<label class="control-label">Serila Number Sale Status</label>
								<div class="controls">
									<select class="form-control" name="serialNo" id="serialNo">
										<option value="">--Select--</option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
							</div>

							<div class="col-md-4">
								<label class="control-label">Indoor Serial Number Sale
									Status</label>
								<div class="controls">
									<select class="form-control" name="indoorSerialNo"
										id="indoorSerialNo">
										<option value="">--Select--</option>
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select>
								</div>
							</div>

							<div class="col-md-4">
								<label class="control-label"> </label>
								<div class="controls">
									<button type="button" class="btn btn-success btn-pass"
										id="search" value="Search">Search</button>
								</div>
							</div>
							<input type="hidden" name="productid" value="${productid}">

						</div>
					</form>
				</div>
				<div class="col-xs-12">
					<!-- /.box -->

					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Update Product</h3>
						</div>
						<!-- /.box-header -->

						<c:if test="${param.Update_Msg==true}">

							<div class="alert alert-success text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>Product has
									been updated successfully</strong>
							</div>
						</c:if>
						<div class="box-body table-responsive">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Purchase Invoice No.</th>
										<th>Trade Discount</th>
										<th>Rebate Discount</th>
										<th>Unit price</th>
										<th>Firm Name</th>
										<th>Serial No</th>
										<th>Sale</th>
										<th>Indoor Serial No</th>
										<th>Indoor Sale</th>
										<th>Outer Unit Location</th>
										<th>Indoor Unit Location</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="productPurchaseInvoice"
										items="${productPurchaseInvoiceList}">

										<tr id="${productPurchaseInvoice.id}">

											<td>${productPurchaseInvoice.cmpyPurchaseInvoiceNo}</td>
											<td>${productPurchaseInvoice.tradeDiscount}</td>
											<td>${productPurchaseInvoice.rebateDiscount}</td>
											<td>${productPurchaseInvoice.unitPrice}</td>
											<td>${productPurchaseInvoice.firmName}</td>
											<td>${productPurchaseInvoice.serialNo}<c:if
													test="${productPurchaseInvoice.saleInvoiceNo!=null}">(
									         <a
														href="/dashboard/finalSaleInvoice?si=${productPurchaseInvoice.saleInvoiceId}">${productPurchaseInvoice.saleInvoiceNo}</a>)
									         </c:if>
											</td>
											<td><c:choose>
													<c:when test="${productPurchaseInvoice.sale==0}">No</c:when>
													<c:otherwise>Yes</c:otherwise>
												</c:choose></td>
											<td>${productPurchaseInvoice.indoorSerialNo}<c:if
													test="${productPurchaseInvoice.saleInvoiceNoForIndoor!=null}">(
									         <a
														href="/dashboard/finalSaleInvoice?si=${productPurchaseInvoice.saleInvoiceIdForIndoor}">${productPurchaseInvoice.saleInvoiceNoForIndoor}</a>)
									         </c:if>
											</td>
											<td><c:choose>
													<c:when test="${productPurchaseInvoice.indoorsale==0}">No</c:when>
													<c:otherwise>Yes</c:otherwise>
												</c:choose></td>
											<td>${productPurchaseInvoice.location}</td>
											<td>${productPurchaseInvoice.indoorLocation}</td>
											<td><a
												href="/dashboard/update-unitLocation/${productPurchaseInvoice.id}"
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
                $("#search").click(function(){
                	if($("#serialNo").val()=="" && $("#indoorSerialNo").val()==""){
                    	$("#filterError").removeClass("hidden").addClass("active");
                    	return false;
                    }
                	else{
                		$("#filterError").removeClass("active").addClass("hidden");
                		$("#filterForm").submit();
                	}
                });
                
            });
        </script>
</body>

