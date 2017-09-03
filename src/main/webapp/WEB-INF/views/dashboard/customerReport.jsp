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
						<c:if test="${param.Update_Msg==true}">

							<div class="alert alert-success text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>Sale Invoice
									has been updated successfully</strong>
							</div>
						</c:if>

						<c:if test="${param.hasError eq true}">

							<div class="alert alert-danger text-center">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>${param.message}</strong>
							</div>
						</c:if>

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
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="customerReport" items="${customerReports}">

										<tr id="${customerReport.saleInvoice.saleInvoiceNo}">
											<td>${customerReport.customer.name}</td>
											<td>${customerReport.customer.contactNo}</td>
											<td><a
												href="/dashboard/finalSaleInvoice?si=${customerReport.saleInvoice.saleInvoiceNo}">${customerReport.saleInvoice.cmpySaleInvoiceNo}</a>
												(${customerReport.saleInvoice.invoiceDate})</td>
											<td>${customerReport.saleInvoice.finalAmount}</td>
											<td>${customerReport.saleInvoice.amountPaid}</td>
											<td>${customerReport.saleInvoice.balanceLeft}</td>
											<td><a
												href="/dashboard/update-saleInvoice/${customerReport.saleInvoice.saleInvoiceNo}"
												class="btn btn-sm btn-success"><span
													class="glyphicon glyphicon-pencil"></span> Edit</a>
													<%-- <button type="button" onclick="openDisableModel('${customerReport.saleInvoice.saleInvoiceNo}')"class="btn btn-sm btn-success"><span
													class="glyphicon glyphicon-pencil"></span> Delete</button> --%>
													</td>

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
	
	<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title text-center">Are you sure to delete?</h4>
        
      </div>
      <div class="modal-body text-center">
        <p>If press ok,then your inventory stock is updated automatically.</p>
      </div>
      <input type="hidden" id="saleInvoiceId"/>
      <div class="modal-footer">
        <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-sm btn-success" onclick="disableSaleInvoice()" data-dismiss="modal">OK</button>
         <div class="modal-footer">
        
      </div>
    </div>
    </div>
    </div>
    </div>
    
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
        
        function openDisableModel(saleinvoiceId){
        	$("#myModal").modal();
        	$("#saleInvoiceId").val(saleinvoiceId);
        	
        }
        function disableSaleInvoice(){
        	var saleinvoiceId=$("#saleInvoiceId").val();
        	url="/dashboard/disableSaleInvoice/"+saleinvoiceId+"/";
        	$.ajax({
        	       url : url,
        	        dataType : "json",
        	        cache: false,
        	        async: false,
        	       success: function(response, status, code){
        	    	   if(response.disabled){
        	    		   $("#"+saleinvoiceId).hide();
        	    	   }
        	       }, 
        	       complete: function(){
        	           $('#ajax_loader').hide();
        	         },  
        	       error: function(response, status, code){

        	       }
        	     });
        }
        </script>
</body>

