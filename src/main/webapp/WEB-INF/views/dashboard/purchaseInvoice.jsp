<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<body class="skin-black">
	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Generate purchase invoice
				<!-- <small>Control panel</small> -->
			</h1>
		</section>
		<!-- <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Blank page</li>
                    </ol> -->
		<section class="content">

			<div class="row">
			<!-- 	<div class="col-md-12" style="margin-bottom: 10px;">
					<form id="frm1" method="post" enctype="multipart/form-data"
						action="/dashboard/uploadPurchaseinvoice">
						<div class="control-group">
							<label class="control-label">Upload your CSV file </label>
							<div class="controls">
								<input type="file" id="importCsvforUpdate"
									name="uploadPurchaseInvoice"> <a
									href="/dashboard/downloadforPurchaseInvoice">Download
									sample for Update</a></br>
								</br>
								<div class="control-label importErrMsg"></div>
								<input type="submit" class="btn btn-success btn-pass"
									value="Submit" disabled />

							</div>
						</div>
					</form>
				</div> -->

				<div class="col-md-12 error_msg"></div>
				<div class="col-md-12">
					<!-- general form elements -->
					<div class="box box-primary">

						<div class="box-header">
							<h3 class="box-title">Add products</h3>

						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form role="form" id="productsForm">
						<input type="hidden" id="maxId" value="${maxId}"/>
							<div class="box-body clonedDiv" id="addProducts1">
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label for="exampleInputEmail1">Brand</label> <select
												class="form-control brand_type" name="brand1" id="brand1"
												required>
												<option value="">--Select--</option>
												<c:forEach items="${products}" var="product">
													<option value="${product}">${product}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="exampleInputEmail1">Product type</label> <select
												class="form-control product_type" name="productType"
												id="productTypeContainer1" required>
												<option value="">--Select--</option>
											</select>
										</div>
									</div>

									<div class="col-md-2">
										<div class="form-group">
											<label for="exampleInputEmail1">Model number</label> <select
												class="form-control product_model" name="modelNumber"
												id="modelContainer1" required>
												<option value="">--Select--</option>
											</select>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="exampleInputEmail1">Star rating</label> <select
												class="form-control star_rating" name="starRating"
												id="star_rating1" required>
												<option value="">--Select--</option>
											</select>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="exampleInputEmail1">Size</label> <select
												class="form-control product_size" name="size"
												id="sizeContainer1" required>
												<option value="">--Select--</option>
											</select>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="serialNumber">Serial number </label> <input
												class="form-control" required type="text" id="serialNumber1"
												name="serialNumber" />
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="hsnCode">HSN code </label> <input
												class="form-control" required type="text" id="hsnCode1"
												name="hsnCode" />
										</div>
									</div>

									<div class="col-md-2 hide">
										<div class="form-group">
											<label for="exampleInputEmail1">Indoor Serial no.</label> <input
												class="form-control indoor_serial_number" type="text"
												name="indoorModelNumber" id="indoorModelNumber1" />
										</div>
									</div>
									<div class="col-md-2 hide">
										<div class="form-group">
											<label for="Quantity">Quantity</label> <input type="hidden"
												name="quantity" id="quantity1" min="1" value="1"
												placeholder="Quantity" class="form-control qty" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="rebate_discount">Rebate/Cash Discount</label> <input
												type="text" name="rebateDiscount"
												placeholder="Rebate Discount" value="0"
												id="rebate_discount1" class="form-control rebate_discount">
										</div>
									</div>
									</div>
									<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label for="trade_discount">Trade Discount</label> <input
												type="text" name="tradeDiscount"
												placeholder="Trade Discount" value="0" id="trade_discount1"
												class="form-control trade_discount">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="unitPrice">Unit Price</label> <input type="text"
												name="unitPrice" placeholder="Unit Price" value=""
												id="unitPrice1" class="form-control unitPrice" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="bill_amount">Bill Amount</label> <input
												type="text" name="billAmount" placeholder="Bill Amount"
												value="0" id="bill_amount1" class="form-control bill_amount">
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="outdoor_location">Unit Location</label> <select
												class="form-control product_location" name="location"
												id="outdoor_location1" required>
												<option value="Shop">Shop</option>
												<option value="Godown">Godown</option>
											</select>
										</div>
									</div>

									<div class="col-md-2 hide">
										<div class="form-group">
											<label for="indoor_location">Indoor Unit Location</label> <select
												class="form-control product_location" name="indoorLocation"
												id="indoor_location1" required>
												<option value="Shop">Shop</option>
												<option value="Godown">Godown</option>
											</select>
										</div>
									</div>
									<div class="">
										<div class="form-group">
											<input type="hidden" name="id1" id="id1" class="form-control">
										</div>
									</div>
									<div>
										<input type="hidden" id="discountedAmount1"
											name="discountedAmount" value="0"
											placeholder="Discounted amount" class="form-control" required>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
											<div class="form-group">
												<label for="cgst">CGST</label> 

												<input class="form-control cgst_tax" name="cgst"
													id="cgst_tax1" value="0" required />
											</div>
									</div>
									<div class="col-md-2">
											<div class="form-group">
												<label for="igst">IGST</label> 
												<input class="form-control igst_tax" name="igst"
													id="igst_tax1" required value="0" />
											</div>
									</div>
									<div class="col-md-2">
											<div class="form-group">
												<label for="sgst">SGST</label> 
												<input class="form-control sgst_tax" name="sgst"
													id="sgst_tax1" required value="0" />
											</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="unitPrice">Tax amount</label> <input type="text"
												name="singleUnitTax" placeholder="Tax Amount" value=""
												id="singleUnitTax1" class="form-control singleUnitTax" required>
										</div>
									</div>
									<div class="col-md-2" style="margin-top: 30px;">
										<p>
											<a href="#" class="deleteRow" name="deleteRow">Delete</a>
										</p>
									</div>
								</div>
							</div>
							<!-- /.box-body -->
							<div class="row"
								style="margin-left: 0px !important; margin-right: 0px !important;">
								<div class="box-footer">
									<button class="btn btn-primary" id="addProduct" type="button">Add
										more</button>
									<button class="btn btn-primary" id="saveProduct" type="button">Save</button>
								</div>
							</div>
						</form>
					</div>
					<!-- /.box -->

					<div class="row">
						<div class="col-md-12 saveButtonDiv">
							<p>
								<span style="color: red;">Note:</span> Click on save button to
								fill supplier details
							</p>
						</div>
					</div>
					<div class="box box-primary" id="supplierDiv">
						<div class="box-header">
							<h3 class="box-title">Choose supplier</h3>
						</div>
						<div class="col-md-12 error_msg"></div>
						<!-- /.box-header -->
						<!-- form start -->
						<form role="form">
							<div class="row box-body">
								<div class="col-md-2">
									<div class="form-group">
										<label for="exampleInputEmail1">Supplier Name</label> <select
											class="form-control supplier_type" name="supplierName"
											onchange="showSupplierDetails()" id="supplierNameContainer1"
											required>
											<option value="">--Select--</option>
											<c:forEach items="${suppliers}" var="supplier">
												<option value='${supplier.value}'>${supplier.key}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-2" style="margin-top: 30px;">
									<p>
										<a href="/dashboard/addsupplier" class="newSupplier">Add
											new supplier</a>
									</p>
								</div>
							</div>
							<div class="row box-body">
								<div class="col-md-12 supplier_details">
									<div id="supplierInfo"></div>
								</div>
							</div>
						</form>
						<form role="form" id="supplierForm">
							<div class="box-body" id="suppliers">
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label for="invoiceNumber">Invoice/Challan Number</label> <input
												placeholder="invoice number" id="invoiceNo"
												class="form-control" name="invoiceNumber" required=""
												type="text">
										</div>
										<div class="error_invoice hidden" id="invoiceErrorMsg"
											style="color: red;"></div>
									</div>
									<div class="col-md-2">
									<div class="form-group">
										<label for="firm">Firm Name</label>
										 <select class="form-control" name="firmId" id="firmId1">
											<option value="0">--Select--</option>
											<c:forEach items="${firms}" var="firm">
												<option value='${firm.id}'>${firm.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="invoiceNumber">Invoice Sequence</label> <input
												type="text" placeholder="invoice sequence" id="invoiceSequence"
												class="form-control" name="invoiceSequence" readonly/>
										</div>
									
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="totalDiscountedAmount">Discounted amount</label>
											<input type="text" placeholder="Discounted amount"
												id="totalDiscountedAmount" name="totalDiscount"
												class="form-control" required>
										</div>
									</div>

									<!-- <div class="col-md-2">
										<div class="form-group">
											<label for="taxPercent">Tax %</label> <input type="text"
												placeholder="% tax" id="taxPercentage" name="taxPercent"
												class="form-control" required>
										</div>
									</div> -->
									<div class="col-md-2">
										<div class="form-group">
											<label for="taxAmount">Total Tax amount</label> <input type="text"
												placeholder="Tax amount" id="taxableAmount" name="taxAmount"
												class="form-control" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="finalAmount">Final amount</label> <input
												type="text" name="finalAmount" placeholder="Final amount"
												id="finalAmount" class="form-control" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="paymentMode">Payment Mode</label> <select
												class="form-control" name="paymentMode"
												id="paymentModeContainer" required>
												<option>--Select--</option>
												<option>Cheque</option>
												<option>CASH</option>
											</select>
										</div>
									</div>
							 	 	<div class="col-md-2 hide">
										<div class="form-group">
											<label for="chequeNumber">Cheque Number</label> 
											<input
												type="text" name="chequeNumber" placeholder="Cheque number"
												id="chequeNumber" class="form-control" required />
										</div>
									</div>
									<div class="col-md-2 hide">
										<div class="form-group">
											<label for="bankName">Bank Name</label> 
											<input
												type="text" name="bankName" placeholder="Bank name"
												id="bankName" class="form-control" required />
										</div>
									</div>
								 	<div class="col-md-2 hide">
										<div class="form-group">
											<label>Cheque Date:</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" id="datemask2"
													data-inputmask="'alias': 'yyyy-mm-dd'" data-mask
													class="form-control chequeDate" name="chequeDate" required>
											</div>
										</div>
									</div>  
									<div class="col-md-2">
										<div class="form-group">
											<label for="amountPaid">Amount Paid</label> <input
												type="text" name="amountPaid" id="amountPaid"
												placeholder="Amount paid" class="form-control qty" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="balanceLeft">Balance Left</label> <input
												type="text" name="balanceLeft" placeholder="2300"
												id="balanceLeft" class="form-control disc" required>
										</div>
									</div>
									<div class="">
										<div class="form-group">
											<input type="hidden" name="supplierId" id="supplierId"
												class="form-control">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="comments">Comments</label>
											<textarea class="form-control" name="comments"
												placeholder="Comments here.."></textarea>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label>Date:</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" data-inputmask="'alias': 'yyyy-mm-dd'"
													data-mask class="form-control" name="date" id="date"
													required>
											</div>
											<!-- /.input group -->
										</div>
									</div>

								</div>
								<!-- div class="checkbox">
                                            <label class="">
                                                <div class="icheckbox_minimal" style="position: relative;" aria-checked="false" aria-disabled="false"><input type="checkbox" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins></div> Active
                                            </label>
                                            <label class="">
                                                <div class="icheckbox_minimal" style="position: relative;" aria-checked="false" aria-disabled="false"><input type="checkbox" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;"></ins></div> Inactive
                                            </label>
                                        </div> -->
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button class="btn btn-primary" id="supplierButton" disabled
									type="button">Submit</button>
							</div>
						</form>
					</div>
					<!-- another -->
					<!-- /.box-body -->

				</div>

				<!-- Form Element sizes -->


			</div>
			</div>


			<!-- Main content -->



		</section>
		<!-- /.content -->
	</aside>
</body>

<script type="text/javascript">
	$(function() {
		//Datemask dd/mm/yyyy
		$("#datemask").inputmask("yyyy/mm/dd", {
			"placeholder" : "yyyy/mm/dd"
		});
		//Datemask2 mm/dd/yyyy
		$("#datemask2").inputmask("yyyy/mm/dd", {
			"placeholder" : "yyyy/mm/dd"
		});
		//Money Euro
		$("[data-mask]").inputmask();

		//Date range picker
		$('#reservation').daterangepicker();
		//Date range picker with time picker
		$('#reservationtime').daterangepicker({
			timePicker : true,
			timePickerIncrement : 30,
			format : 'YYYY/MM/DD h:mm A'
		});
		//Date range as a button
		$('#daterange-btn').daterangepicker(
				{
					ranges : {
						'Today' : [ moment(), moment() ],
						'Yesterday' : [ moment().subtract('days', 1),
								moment().subtract('days', 1) ],
						'Last 7 Days' : [ moment().subtract('days', 6),
								moment() ],
						'Last 30 Days' : [ moment().subtract('days', 29),
								moment() ],
						'This Month' : [ moment().startOf('month'),
								moment().endOf('month') ],
						'Last Month' : [
								moment().subtract('month', 1).startOf('month'),
								moment().subtract('month', 1).endOf('month') ]
					},
					startDate : moment().subtract('days', 29),
					endDate : moment()
				},
				function(start, end) {
					$('#reportrange span').html(
							start.format('MMMM D, YYYY') + ' - '
									+ end.format('MMMM D, YYYY'));
				});

		//iCheck for checkbox and radio inputs
		$('input[type="checkbox"].minimal, input[type="radio"].minimal')
				.iCheck({
					checkboxClass : 'icheckbox_minimal',
					radioClass : 'iradio_minimal'
				});
		//Red color scheme for iCheck
		$('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red')
				.iCheck({
					checkboxClass : 'icheckbox_minimal-red',
					radioClass : 'iradio_minimal-red'
				});
		//Flat red color scheme for iCheck
		$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
				.iCheck({
					checkboxClass : 'icheckbox_flat-red',
					radioClass : 'iradio_flat-red'
				});

		//Colorpicker
		$(".my-colorpicker1").colorpicker();
		//color picker with addon
		$(".my-colorpicker2").colorpicker();

		//Timepicker
		$(".timepicker").timepicker({
			showInputs : false
		});
	});
	
	

	$('body')
			.on(
					'change',
					'.brand_type',
					function() {
						var brandName = $(this).val();
						var currentDivID = $(this).closest(".clonedDiv").attr(
								"id");
						var regex = /\d+/g;
						var currentDivPos = currentDivID.match(regex);
						console.log($(this));
						$("#productTypeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
						 $("#modelContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
				         $("#sizeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
				         $("#star_rating"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
				         $("#serialNumber"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
				         $("#indoorModelNumber"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
				        
						url = null;
						if (brandName != null) {
							url = "/dashboard/getproductType/" + brandName+"/";
						}
						$
								.ajax({
									url : url,
									dataType : "json",
									cache : false,
									async : false,
									success : function(response, status, code) {

										if (response.exists) {
											jQuery(".error_container").html("");
											for ( var index = 0; index < response.brandName.length; index++) {
												ProductContainer = "<option value='"+ response.brandName[index] +"'>"
														+ response.brandName[index]
														+ "</option>";

												$(
														"#productTypeContainer"
																+ currentDivPos)
														.append(
																ProductContainer);

											}

											//ProductContainer ="</select></div></div>";

											jQuery("#productTypeContainer")
													.append(
															"</select></div></div>");
											$(".error_msg").html("");

										} else {
											$(".error_msg").html("<div class='alert alert-danger text-center'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>No record for this brand</strong></div>");
										}

									},
									error : function(response, status, code) {

										jQuery(".error_container")
												.html(
														"Some error unknown error occurred.");
									}
								});

					});
	
	 $('body').on('change', '.star_rating', function() {
		  var starRating = $(this).val();
		  if(starRating!="None"){
         var currentDivID = $(this).closest(".clonedDiv").attr("id");
         var regex = /\d+/g;
		  var currentDivPos = currentDivID.match(regex);
         console.log(currentDivID)
         var brandName=$('#brand'+currentDivPos).val();
   	  var productType=$('#productTypeContainer'+currentDivPos).val();
   	  var modelNumber=$('#modelContainer'+currentDivPos).val();
   	modelNumber = encodeURIComponent(modelNumber);
   	  
   	 $("#sizeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
   	 
   	   var url="/dashboard/getsize/"+brandName+"/"+productType+"/"+modelNumber+"/";
   	   $('#ajax_loader').show();
    $.ajax({
       url : url,
        dataType : "json",
        cache: false,
        async: false,
       success: function(response, status, code){
  
         if(response.exists){
             jQuery(".error_container").html("");
             for( var index = 0; index < response.size.length; index ++){
                     SizeContainer ="<option value='"+ response.size[index] +"'>"+response.size[index]+"</option>";
                     jQuery("#sizeContainer"+currentDivPos).append(SizeContainer);
                
             }
                 
            SizeContainer ="</select></div></div>";
                 
                 jQuery("#sizeContainer"+currentDivPos).append(SizeContainer); 
                 $(".error_msg").html("");
                }
         else{
        	 $(".error_msg").html("<div class='alert alert-danger text-center'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>No record for this star rating</strong></div>");
         }

                         
       }, 
       complete: function(){
           $('#ajax_loader').hide();
         },  
       error: function(response, status, code){

      jQuery(".error_container").html("Some error unknown error occurred.");
       }
     });
		 }
});

 	 $('body').on('change', '#paymentModeContainer', function() {
			var paymentType = $(this).val();
			console.log(paymentType); 
			if(paymentType == "Cheque"){
				$("#chequeNumber").parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
				$(".chequeDate").parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
				$("#bankName").parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
			}else{
				$("#chequeNumber").parents('div[class^="col-md-2"]').addClass("hide",1000);
				$(".chequeDate").parents('div[class^="col-md-2"]').addClass("hide",1000);
				$("#bankName").parents('div[class^="col-md-2"]').addClass("hide",1000);
			}
		});  
	
 	$('body').on('change', '#firmId1', function() {
		var maxId=$("#maxId").val();
		var frimName=$("#firmId1 option:selected").text();
		var saleInvoiceSequence=maxId+"- "+frimName;
		$("#invoiceSequence").val(saleInvoiceSequence);
	}); 
 	
	 $('body').on('change', '.product_type', function() {
		
		  var productType = $(this).val();
		  var x=productType.replace(" ", "");
         var currentDivID = $(this).closest(".clonedDiv").attr("id");
         var regex = /\d+/g;
		  var currentDivPos = currentDivID.match(regex);
         console.log(currentDivID);
         
         if(productType.replace(" ", "").toLowerCase() == "splitac"){
			  //show both units
			  $("#indoorModelNumber"+currentDivPos).parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
			  //$("#star_rating"+currentDivPos).parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
			  $("#indoor_location"+currentDivPos).parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
		  }else{
		  $("#indoorModelNumber"+currentDivPos).parents('div[class^="col-md-2"]').addClass("hide");
		  $("#indoorModelNumber"+currentDivPos).parents('div[class^="col-md-2"]').children().removeClass("has-error");
		  // console.log("##################" , $("#indoorModelNumber"+currentDivPos).parents('div[class^="col-md-2"]').children());
		 //$("#star_rating"+currentDivPos).parents('div[class^="col-md-2"]').removeClass("hide",1000);
		  $("#indoor_location"+currentDivPos).parents('div[class^="col-md-2"]').addClass("hide");
		  $("#indoor_location"+currentDivPos).parents('div[class^="col-md-2"]').children().removeClass("has-error");
		  }
         
    //      if(productType.replace(" ", "").toLowerCase().indexOf("ac") ==-1){
    //    	  $("#star_rating"+currentDivPos).parents('div[class^="col-md-2"]').addClass("hide");
		  // $("#star_rating"+currentDivPos).parents('div[class^="col-md-2"]').children().removeClass("has-error");	  
		  // }else{
			 //  $("#star_rating"+currentDivPos).parents('div[class^="col-md-2 hide"]').addClass("hide",1000);
			 //  $("#star_rating"+currentDivPos).parents('div[class^="col-md-2"]').children().removeClass("has-error");
		  // }
         var brandName=$('#brand'+currentDivPos).val();
         $("#star_rating"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
         $("#modelContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
         $("#sizeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
          url=null;
          if(brandName!=""){
               url="/dashboard/getModelNumber/"+brandName+"/"+productType+"/";
          }
          $('#ajax_loader').show();
         $.ajax({
            url : url,
             dataType : "json",
             cache: false,
             async: false,
            success: function(response, status, code){
       
              if(response.exists){
                  jQuery(".error_container").html("");
                  for( var index = 0; index < response.modelNumber.length; index ++){
                          ModelContainer ="<option value='"+ response.modelNumber[index] +"'>"+response.modelNumber[index]+"</option>";
                          jQuery("#modelContainer"+currentDivPos).append(ModelContainer);
                     
                  }
                      
                     ModelContainer ="</select></div></div>";
                      
                      jQuery("#modelContainer").append(ModelContainer); 
                      $(".error_msg").html("");
                  
                  }
              else{
            	  $(".error_msg").html("<div class='alert alert-danger text-center'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>No record for this product type</strong></div>");
              }

                              
            }, 
            complete: function(){
                $('#ajax_loader').hide();
              }, 
            error: function(response, status, code){
           
           jQuery(".error_container").html("Some error unknown error occurred.");
            }
          });
     });

	 $('body').on('change', '.product_model', function() {
		  var modelNumber = $(this).val();
		  modelNumber = encodeURIComponent(modelNumber);
         var currentDivID = $(this).closest(".clonedDiv").attr("id");
         var regex = /\d+/g;
		  var currentDivPos = currentDivID.match(regex);
         console.log(currentDivID)
         var brandName=$('#brand'+currentDivPos).val();
    	  var productType=$('#productTypeContainer'+currentDivPos).val();
    	  
    	 $("#sizeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
    	 $("#star_rating"+currentDivPos).find('option').remove().end().append('<option value="None">--Select--</option>');
    	 $('#ajax_loader').show();
    	  var starFlag=false;
  		 url="/dashboard/getStar/"+brandName+"/"+productType+"/"+modelNumber+"/";
  	      $.ajax({
               url : url,
                dataType : "json",
                cache: false,
                async: false,
               success: function(response, status, code){
          
                 if(response.exists){
                     jQuery(".error_container").html("");
                     for( var index = 0; index < response.star.length; index ++){
                     	if(response.star[index]==null || response.star[index]=='undefined' || response.star[index].trim().length==0){
                     		continue;
                     	}
                     	else{
                             StarContainer ="<option value='"+ response.star[index] +"'>"+response.star[index]+"</option>";
                             jQuery("#star_rating"+currentDivPos).append(StarContainer);
                             starFlag=true;
                     	}
                        
                     }
                         if(starFlag){
                     StarContainer ="</select></div></div>";
                         
                         jQuery("#star_rating"+currentDivPos).append(StarContainer); 
                         }
                         
                      }
                 else{
                     jQuery(".error_container").html("No size for corresponding selected model.");
                 }
      
               }, 
               complete: function(){
                   $('#ajax_loader').hide();
                 },  
               error: function(response, status, code){

              jQuery(".error_container").html("Some error unknown error occurred.");
               }
             });
  		  if(!starFlag){
       		 url="/dashboard/getsize/"+brandName+"/"+productType+"/"+modelNumber+"/";
    	        
     $.ajax({
        url : url,
         dataType : "json",
         cache: false,
         async: false,
        success: function(response, status, code){
   
          if(response.exists){
              jQuery(".error_container").html("");
              for( var index = 0; index < response.size.length; index ++){
                      SizeContainer ="<option value='"+ response.size[index] +"'>"+response.size[index]+"</option>";
                      jQuery("#sizeContainer"+currentDivPos).append(SizeContainer);
                 
              }
                  
             SizeContainer ="</select></div></div>";
                  
                  jQuery("#sizeContainer"+currentDivPos).append(SizeContainer); 
                  
                 
              }
          else{
              jQuery(".error_container").html("No size for corresponding selected model.");
          }

                          
        }, 
        complete: function(){
            $('#ajax_loader').hide();
          },  
        error: function(response, status, code){

       jQuery(".error_container").html("Some error unknown error occurred.");
        }
      });
	 }
 });

	$('body')
			.on(
					'change',
					'.product_size',
					function() {
						var size = $(this).val();
						var currentDivID = $(this).closest(".clonedDiv").attr(
								"id");
						var regex = /\d+/g;
						var currentDivPos = currentDivID.match(regex);
						console.log(currentDivID)
						var brandName = $('#brand' + currentDivPos).val();
						var productType = $(
								'#productTypeContainer' + currentDivPos).val();
						var modelNumber = $('#modelContainer' + currentDivPos)
								.val();
						modelNumber = encodeURIComponent(modelNumber);
						var starRating=$('#star_rating' + currentDivPos)
						.val();
						url = null;
						if(starRating==null ||starRating=='undefined' || starRating.trim().length==0 || starRating==='None'){
							starRating="NA";
						}
						url = "/dashboard/getproduct-info/" + brandName + "/"
						+ productType + "/" + modelNumber + "/" + size+"/"+starRating+"/";

						$.ajax({
									url : url,
									dataType : "json",
									cache : false,
									async : false,
									success : function(response, status, code) {

										if (response.exists) {
											jQuery(".error_container").html("");

											$('#id' + currentDivPos)
													.val(response.productId);
											if(response.hsnCode!=null)
											$('#hsnCode' + currentDivPos)
											.val(response.hsnCode);
											if(response.hsnCode!=null)
											$('#cgst_tax' + currentDivPos)
											.val(response.cgst);
											if(response.igst!=null)
											$('#igst_tax' + currentDivPos)
											.val(response.igst);
											if(response.sgst!=null)
											$('#sgst_tax' + currentDivPos)
											.val(response.sgst);

										} else {
											jQuery(".error_container")
													.html(
															"No data available  for corresponding selected size.");
										}

									},
									error : function(response, status, code) {

										jQuery(".error_container")
												.html(
														"Some error unknown error occurred.");
									}
								});

					}); 

	$(".supplier_type").on("change", function() {
		$("#supplierButton").prop("disabled", false);
		$(".supplier_details").show(1000);
	})
	var cloneCount = 1;
	$("#addProduct").click(function(){
		clonedDivCheck();
	});
	$("#saveProduct").click(function(){
		clonedValidate();
	});
	
	function clonedValidate(){
		cloneCount = $(".clonedDiv").length;
		cloneCount++;
		$('#productsForm').bootstrapValidator('validate');
		var clonedDiv = $("#addProducts1").clone().attr('id',
				'addProducts' + cloneCount);
		if($(".clonedDiv:last").find('.has-error').length>0){ //it's `
			console.log($(".clonedDiv:last").find('.has-error'));
			$("#supplierDiv").hide(1000);
			$(".saveButtonDiv").show(1000);
		}else{
			$("#supplierDiv").show(1000);
			$(".saveButtonDiv").hide(1000);
		}
	}
			function clonedDivCheck(event) {
				$("#supplierDiv").hide(1000);
				$(".saveButtonDiv").show(1000);
				cloneCount = $(".clonedDiv").length;
				cloneCount++;
				$('#productsForm').bootstrapValidator('validate');
				var clonedDiv = $("#addProducts1").clone().attr('id',
						'addProducts' + cloneCount);
				if($(".clonedDiv:last").find('.has-error').length>0){ //it's `
					console.log($(".clonedDiv:last").find('.has-error'));
					$("#supplierDiv").hide(1000);
					$(".saveButtonDiv").show(1000);
				}else{
					
				clonedDiv.insertAfter($('[id^=addProducts]:last')).find(
						"input[type='text'],input[type='number']").val("");
				var validator = $('#productsForm').data('bootstrapValidator');
				$(clonedDiv).find("input, select").each(function() {
					var Id = $(this).attr('id');
					var newId = Id.replace(/[0-9]/g, '');
					var Name = $(this).attr('name');
					var newName = Name.replace(/[0-9]/g, '');
					$(this).attr({
						id : newId + cloneCount
						// name : newName + cloneCount
					});
					validator.addField($(this));
				});
			}
			}
	var finalArray = {};

	var saveProduct = function() {
		var innerArray = [];
		var sumPrice = 0;
		var addProductsDivs = $("#productsForm").children(".clonedDiv");
		console.log(addProductsDivs)
		$(addProductsDivs).each(function(i, v) {
			console.log($(this))

			productsArray = {};
			$(this).find("input, select").each(function() {

				productvalue = $(this).val();
				productName = $(this).attr("name").replace(/[0-9]/g, '');
				;

				//console.log(keyName)
				//console.log("innerarray",productvalue)
				productsArray[productName] = productvalue;

			});
			innerArray.push(productsArray);

			/* $(this).find(".unitPrice").each(function() {
				var quantity = $(this).val();
				sumPrice = sumPrice + Number(quantity);
			}); */
			console.log(JSON.stringify(innerArray));
		});

		/* $("#finalAmount").val(sumPrice); */
		finalArray["productsArray"] = innerArray;
		//console.log(productsArray);
		return productsArray;
	};
	$("body").on("click", "#saveProduct", function(event) {
		saveProduct();
	})
	var supplierArray = {};
	$("#supplierButton").click(function() {
		$('#supplierForm').bootstrapValidator('validate');
		var validator = $('#supplierForm').data('bootstrapValidator');
		var customerData = $("#supplierForm").children("#suppliers");
		if((customerData).find('.has-error').length>0){ //it's `
			//alert("Please fill the required field.");
			$("#supplierForm").submit(function(e){
			        e.preventDefault();
			    });
		}
		$(customerData).find("input, select, textarea").each(function(i) {
			var value = $(this).val();
			var keyName = $(this).attr("name");
			finalArray[keyName] = value;
			//finalArray.push({keyName:value});
		});
		

		console.log(JSON.stringify(finalArray));
		/* alert(JSON.stringify(finalArray)); */		
	     jQuery.ajax({
             type : "POST",
             contentType: "application/json",
             url : "/dashboard/save-purchaseinvoice",
             data : JSON.stringify(finalArray),

             success : function(response, status, code){
            	 /* alert(response); */
              window.location.href="/dashboard/finalPurchaseInvoice?pi="+response;
             },
             error : function(response, status, code){
            	 $(".error_msg").html("<div class='alert alert-danger text-center'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>"+response.responseText+"</strong></div>");
                  
             }
         });

		// createUpdatedJson();

		// function createUpdatedJson(){

		//   var updatedJson = finalArray;

		//   updatedJson.productsArray = [updatedJson.productsArray];

		//   // $('#jsondata').html(JSON.stringify(updatedJson));

		//   console.log(JSON.stringify(updatedJson));
		// }
	});
	$("body").on("click", ".deleteRow", function(event) {
		// console.log($(this))
		var delRow = $(this).closest(".clonedDiv");
		if ($(".clonedDiv").length == 1) {
			alert("Unit row can't be deleted");
		} else {
			delRow.remove();
		}
		// console.log(delRow);

		var clonedDivLength = $(".clonedDiv").length;
		$(".clonedDiv").each(function(i, v) {
			console.log("clonedDivLength" + clonedDivLength)
			i = i + 1;
			var clonedDiv = $(this).attr('id', 'addProducts' + i);
			$(this).find('input, select').each(function() {
				console.log($(this))
				var Id = $(this).attr('id');
				var newId = Id.replace(/[0-9]/g, '');
				var Name = $(this).attr('name');
				var newName = Name.replace(/[0-9]/g, '');
				$(this).attr({
					id : newId + i
					// name : newName + i
				});

			});

		});
		saveProduct();
	});
	

	function showSupplierDetails() {
		var supplierDetailsJson = $('#supplierNameContainer1').val();
		var supplierDetails = JSON.parse(supplierDetailsJson);
		var id = supplierDetails.id;
		$("#supplierId").val(id);
		$("#supplierInfo")
				.html(
						"<label>Address:</label><span> "
								+ supplierDetails.address
								+ " </span><span><label> Contact Number:</label><span> "
								+ supplierDetails.contact
								+ " </span><label>Email:</label><span> "
								+ supplierDetails.email + "</sapn></label>");
		$("#supplierButton").prop("disabled", false);
		$(".supplier_details").show(1000);

	}
	
	
	$(document).ready(function(){
		console.log("started")
		
		$("#supplierDiv").hide();
		
		$('#productsForm')
				.bootstrapValidator(
						{
							message : 'This value is not valid',
							feedbackIcons : {

							},
							fields : {
								'size' : {
									validators : {
										notEmpty : {
											message : 'Select size please.'
										}
									}
								},
								'productType' : {
									validators : {
										notEmpty : {
											message : 'Select product type please.'
										}
									}
								},
								'modelNumber': {
									validators : {
										notEmpty : {
											message : 'Select model number please.'
										}
									}
								},
								'brand': {
									validators : {
										notEmpty : {
											message : 'Select brand please.'
										}
									}
								},
								'quantity': {
									validators : {
										notEmpty : {
											message : 'Select quantity please.'
										}
									}
								},
								'indoorModelNumber': {
									validators : {
										notEmpty : {
											message : 'Select indoor model number please.'
										}
									}
								},
								'unitPrice': {
									validators : {
										notEmpty : {
											message : 'Select unit price please.'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Only numbers'
                                        }
									}
								},
								'starRating': {
									validators : {
										notEmpty : {
											message : 'Select star rating please.'
										}
									}
								},
								'discountedAmount': {
									validators : {
										notEmpty : {
											message : 'Select discount amount please.'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Only numbers'
                                        }
									}
								},
								'location': {
									validators : {
										notEmpty : {
											message : 'Select location please.'
										}
									}
								},
								'rebateDiscount':{
									validators : {
										notEmpty : {
											message : 'Enter a value or enter 0 if none'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Only numbers are allowed'
                                        }
									}
								},
								'tradeDiscount':{
									validators : {
										notEmpty : {
											message : 'Enter a value or enter 0 if none.'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Only numbers are allowed'
                                        }
									}
								},
								'indoorLocation': {
									validators : {
										notEmpty : {
											message : 'Select indoor unit location please.'
										}
									}
								},
								'billAmount':{
									validators : {
										notEmpty : {
											message : 'Enter bill amount'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Only numbers are allowed'
                                        }
									}
								},
								'serialNumber': {
									validators : {
										notEmpty : {
											message : 'Select serial number please'
										}
									}
								},
								'sgst': {
									validators : {
										notEmpty : {
											message : 'Enter a value or enter 0 if none.'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Reorder point consist of only numeric values'
                                        }
									}
								},
								'igst': {
									validators : {
										notEmpty : {
											message : 'Enter a value or enter 0 if none.'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Reorder point consist of only numeric values'
                                        }
									}
								},
								'cgst': {
									validators : {
										notEmpty : {
											message : 'Enter a value or enter 0 if none.'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Reorder point consist of only numeric values'
                                        }
									}
								},
								"singleUnitTax":{
									notEmpty : {
											message : 'Enter tax amount, if not getting then click on ant textfield'
										},
										regexp : {
                                            regexp : /^[0-9_\.]+$/,
                                            message : 'Reorder point consist of only numeric values'
                                        }
								}
							}
							
						});
	});
		$(document)
	      .ready(
	              function() {
			$('#supplierForm')
			.bootstrapValidator(
					{
						message : 'This value is not valid',
						feedbackIcons : {
						},
						fields : {
							'invoiceNumber' : {
								validators : {
									notEmpty : {
										message : 'Enter invoice number, cannot be empty.'
									}
								}
							},
							'totalDiscount' : {
								validators : {
									notEmpty : {
										message : 'Cannot be empty, if not getting then click on ant textfield'
									},
									regexp : {
                                        regexp : /^[0-9_\.]+$/,
                                        message : 'Only numbers allowed'
                                    }
								}
							},
							'finalAmount': {
								validators : {
									notEmpty : {
										message : 'Cannot be empty, if not getting then click on ant textfield'
									},
									regexp : {
                                        regexp : /^[0-9_\.]+$/,
                                        message : 'Only numbers allowed'
                                    }
								}
							},
							'paymentMode': {
								validators : {
									notEmpty : {
										message : 'Select mode of payment, if not getting then click on ant textfield'
									}
								}
							},
							'amountPaid': {
								message : 'The amount is not valid',
                                validators : {
                                    notEmpty : {
                                        message : 'The amount paid is required and cannot be empty'
                                    },
                                
                                    regexp : {
                                        regexp : /^[0-9_\.]+$/,
                                        message : 'Amount paid can only consist of number'
                                    }
                                }
							},
							'unitPrice': {
								validators : {
									notEmpty : {
										message : 'Cannot be empty'
									},
									regexp : {
                                        regexp : /^[0-9_\.]+$/,
                                        message : 'Only numbers allowed'
                                    }
								}
							},
							'balanceLeft': {
								validators : {
									notEmpty : {
										message : 'Cannot be empty'
									},
									regexp : {
                                        regexp : /^[0-9_\.]+$/,
                                        message : 'Only numbers allowed'
                                    }
								}
							},
							// 'comments': {
							// 	validators : {
							// 		notEmpty : {
							// 			message : 'Add comments, cannot be empty'
							// 		}
							// 	}
							// },
							'purchaseInvoicedate': {
								validators : {
									notEmpty : {
										message : 'Select date please.'
									}
								}
							},
							"taxAmount": {
								validators : {
									notEmpty : {
										message : 'Enter tax amount, if not getting then click on ant textfield.'
									}
								}
							}
						}
						
					});
			
 			$("#invoiceNo").change(function() {
		    	   var invoiceNo=$('#invoiceNo').val();
		    	   if(invoiceNo.trim().length>0){
		    		   invoiceNo=encodeURIComponent(invoiceNo);
		    	   var url="/dashboard/checkUniquePurchaseInvoice/"+invoiceNo+"/";
		    	   $.ajax({
		    	           url : url,
		    	            dataType : "json",
		    	            cache: false,
		    	            async: false,
		    	           success: function(data) {
		    	           if(data.exists){
		    	            $(".error_invoice").removeClass('hidden');
		    	                 $(".error_invoice").html(data.message);
		    	                 $("#supplierForm").submit(function(e){
		    	 			        e.preventDefault();
		    	 			    });
		    	                $('#invoiceNo').focus();

		    	           }
		    	           else{
		    	            $(".error_invoice").addClass('hidden');
		    	           }
		    	           
		    	           },
		    	           error: function(e) {
		    	          
		    	           }
		    	         });
		       }
					}); 
		});
	
	
</script>