<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body class="skin-black">
	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Generate sale invoice
				<!-- <small>Control panel</small> -->
			</h1>
		</section>
		<!-- <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Blank page</li>
                    </ol> -->
		<div class="loaderContent" id="ajax_loader" style="display: none;">
			<div class="ajaxLoader">
				<img style="width: 50px; height: 50px;"
					src="/resources/img/ajax-loader.gif" />
			</div>
		</div>

		<section class="content">
			<div class="row">

			<!-- 	<div class="col-md-12" style="margin-bottom: 10px;">
					<form id="frm1" method="post" enctype="multipart/form-data"
						action="/dashboard/uploadSaleinvoice">
						<div class="control-group">
							<label class="control-label">Upload your CSV file </label>
							<div class="controls">
								<input type="file" id="importCsvforUpdate" name="uploadInvoice"
									accept=".csv" pattern="^.+\.(csv)$"
									onChange="validateFileInput(this)"> <a
									href="/dashboard/downloadforSaleInvoice">Download sample
									for Update</a></br>
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
						<form role="form" id="saleProductsForm">
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
												class="form-control product_model" name="modelNumber1"
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
											<label for="exampleInputEmail1">Select serial no.</label> <select
												class="form-control serialNo" name="serialNumber1"
												id="serialNo1" required>
												<option value="">--Select--</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2 hide">
										<div class="form-group">
											<label for="exampleInputEmail1">Select indoor no.</label> <select
												class="form-control indoor_serial_number"
												name="indoorModelNumber" required id="indoorModelNumber1">
												<option value="">--Select--</option>
											</select>
										</div>
									</div>
									<div class="col-md-2 hide">
										<div class="form-group">
											<label for="Quantity">Quantity</label> <input type="hidden"
												name="quantity1" min="0" value="1" placeholder="Quantity"
												id="quantity1" class="form-control" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="trade_discount">Trade Discount</label> <input
												type="text" name="tradeDiscount"
												placeholder="Trade Discount" value="0" id="trade_discount1"
												class="form-control trade_discount">
										</div>
									</div>
									<div>

										<input type="hidden" name="discountRate" id="discountRate1"
											value="0" id="discount" class="form-control" required>
									</div>

									<div>
										<input type="hidden" id="discountedAmount1"
											name="discountedAmount" value="0"
											placeholder="Discounted amount" class="form-control" required>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="unitPrice">Unit Price</label> <input type="text"
												id="unitPrice1" name="unitPrice" placeholder="Unit Price"
												class="form-control" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="bill_amount">Bill Amount</label> <input
												type="text" name="billAmount" placeholder="Bill Amount"
												value="0" id="bill_amount1" class="form-control bill_amount">
										</div>
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
								<input type="hidden" id="id1" name="id1" /> <input type="hidden"
									id="purchaseInvoiceNo1" name="purchaseInvoiceNo1" /> <input
									type="hidden" id="indoorPurchaseInvoiceNo1"
									name="indoorPurchaseInvoiceNo1" value="0" />
								<!--  <input type="hidden" id="purchaseinvoiceDate1" name="purchaseinvoiceDate1"/> -->

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
					<!-- another -->
					<div class="row">
						<div class="col-md-12 saveButtonDiv">
							<p style='font-size: 18px;'>
								<span style="color: red;">Note:</span> Click on save button to
								fill supplier details
							</p>
						</div>
					</div>

					<div class="box box-primary" id="customerDiv">
						<div class="box-header">
							<h3 class="box-title">Generate Invoice</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form role="form" id="customerForm">
							<div class="box-body" id="customers">
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label for="invoiceNumber">Invoice number</label> <input
												type="text" placeholder="invoice number" id="invoiceNo"
												class="form-control" name="invoiceNumber" required>
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
											<label for="finalAmount">Total Discount</label> <input
												type="text" placeholder="Discounted amount"
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
											<label for="taxAmount">Tax amount</label> <input type="text"
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
												type="text" name="chequeNumber " placeholder="Cheque number"
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
												<input type="text" id="chequeDate"
													data-inputmask="'alias': 'yyyy-mm-dd'" data-mask
													class="form-control invoicedate" name="chequeDate" required>
											</div>
											<!-- /.input group -->
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="amountPaid">Amount Paid</label> <input
												type="text" name="amountPaid" id="amountPaid"
												placeholder="Quantity" class="form-control qty" required>
										</div>
									</div>
									<div class="col-md-2">
										<div class="form-group">
											<label for="balanceLeft">Balance Left</label> <input
												type="text" name="balanceLeft" placeholder="2300"
												id="balanceLeft" class="form-control disc" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<label>Date masks:</label>
											<div class="input-group">
												<div class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</div>
												<input type="text" id="datemask"
													data-inputmask="'alias': 'yyyy-mm-dd'" data-mask
													class="form-control invoicedate" name="date" required>
											</div>
											<!-- /.input group -->
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="CustomerName">Customer Name</label> <input
												type="text" placeholder="Customer name" id="customerName"
												name="customerName" class="form-control" required>
										</div>
									</div>



									<div class="col-md-2">
										<div class="form-group">
											<label for="CustomerContact">Customer Contact</label> <input
												type="text" placeholder="999999999" id="customerContact"
												name="customerContact" class="form-control" required>
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="CustomerAddress">Customer Address</label>
											<textarea class="form-control" name="customerAddress"
												required placeholder="Address here.."></textarea>
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="comments">Comments</label>
											<textarea class="form-control" name="comments"
												placeholder="Comments here.."></textarea>
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
								<button class="btn btn-primary" id="customerButton"
									type="button">Submit</button>
							</div>
						</form>
					</div>
					<!-- /.box-body -->

				</div>

				<!-- Form Element sizes -->


			</div>
			</div>


			<!-- Main content -->



		</section>
		<!-- /.content -->
	</aside>
	<!-- /.right-side -->

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
		                 ranges: {
		                     'Today': [moment(), moment()],
		                     'Yesterday': [moment().subtract('days', 1), moment().subtract('days', 1)],
		                     'Last 7 Days': [moment().subtract('days', 6), moment()],
		                     'Last 30 Days': [moment().subtract('days', 29), moment()],
		                     'This Month': [moment().startOf('month'), moment().endOf('month')],
		                     'Last Month': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
		                 },
		                 startDate: moment().subtract('days', 29),
		                 endDate: moment()
		             },
				     function(start, end) {
				         $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
				     }
				     );
				
				     //iCheck for checkbox and radio inputs
				     $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
				         checkboxClass: 'icheckbox_minimal',
				         radioClass: 'iradio_minimal'
				     });
				     //Red color scheme for iCheck
				     $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
				         checkboxClass: 'icheckbox_minimal-red',
				         radioClass: 'iradio_minimal-red'
				     });
				     //Flat red color scheme for iCheck
				     $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
				         checkboxClass: 'icheckbox_flat-red',
				         radioClass: 'iradio_flat-red'
				     });
				
				     //Colorpicker
				     $(".my-colorpicker1").colorpicker();
				     //color picker with addon
				     $(".my-colorpicker2").colorpicker();
				
				     //Timepicker
				     $(".timepicker").timepicker({
				         showInputs: false
				     });
				 });
	
		$('body').on('change', '#paymentModeContainer', function() {
			var paymentType = $(this).val();
			console.log(paymentType); 
			if(paymentType == "Cheque"){
				$("#chequeNumber").parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
				$("#chequeDate").parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
				$("#bankName").parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
			}else{
				$("#chequeNumber").parents('div[class^="col-md-2"]').addClass("hide",1000);
				$("#chequeDate").parents('div[class^="col-md-2"]').addClass("hide",1000);
				$("#bankName").parents('div[class^="col-md-2"]').addClass("hide",1000);
			}
		});
		 
		 $('body').on('change', '.brand_type', function() {
			 var brandName = $(this).val();
			 var currentDivID = $(this).closest(".clonedDiv").attr("id");
			 var regex = /\d+/g;
			 var currentDivPos = currentDivID.match(regex);
			 console.log($(this));
			 $("#productTypeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
			 $("#modelContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	         $("#sizeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	         $("#star_rating"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	         $("#serialNo"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	         $("#indoorModelNumber"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
             url=null;
             if(brandName!=null){
                  url="/dashboard/getproductType/"+brandName;
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
                       for( var index = 0; index < response.brandName.length; index ++){
                           ProductContainer ="<option value='"+ response.brandName[index] +"'>"+response.brandName[index]+"</option>";
                           
                           
                           $("#productTypeContainer"+currentDivPos).append(ProductContainer);
                      
                       }
                       
                       //ProductContainer ="</select></div></div>";
                       
                       jQuery("#productTypeContainer").append("</select></div></div>"); 
                       
                       }
                   else{
                	   $(".error_msg").html("<div class='alert alert-danger text-center'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>No record for this brand</strong></div>");
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
		 
		 $('body').on('change', '.star_rating', function() {
			  var starRating = $(this).val();
	          var currentDivID = $(this).closest(".clonedDiv").attr("id");
	          var regex = /\d+/g;
			  var currentDivPos = currentDivID.match(regex);
	          console.log(currentDivID)
	          var brandName=$('#brand'+currentDivPos).val();
        	  var productType=$('#productTypeContainer'+currentDivPos).val();
        	  var modelNumber=$('#modelContainer'+currentDivPos).val();
        	  modelNumber = encodeURIComponent(modelNumber);
        	  
        	  $("#sizeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
 	          $("#serialNo"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
 	          $("#indoorModelNumber"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
        	 
        	   var url="/dashboard/getsize/"+brandName+"/"+productType+"/"+modelNumber;
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
		 
     });
      
		 $('body').on('change', '.product_type', function() {
		  var productType = $(this).val();
          var currentDivID = $(this).closest(".clonedDiv").attr("id");
          var regex = /\d+/g;
		  var currentDivPos = currentDivID.match(regex);
          console.log(currentDivID);
          
          if(productType.replace(" ", "").toLowerCase() == "splitac"){
			  //show both units
			  $("#indoorModelNumber"+currentDivPos).parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
		  }else{
		  $("#indoorModelNumber"+currentDivPos).parents('div[class^="col-md-2"]').addClass("hide");
		  $("#indoorModelNumber"+currentDivPos).parents('div[class^="col-md-2"]').children().removeClass("has-error");
		  }
          
          if(productType.replace(" ", "").toLowerCase().indexOf("ac") ==-1){
        	  $("#star_rating"+currentDivPos).parents('div[class^="col-md-2"]').addClass("hide");
        	  $("#star_rating"+currentDivPos).parents('div[class^="col-md-2"]').children().removeClass("has-error");
			  
		  }else{
			  $("#star_rating"+currentDivPos).parents('div[class^="col-md-2 hide"]').removeClass("hide",1000);
		  }
          var brandName=$('#brand'+currentDivPos).val();
          $("#modelContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
          $("#sizeContainer"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	      $("#star_rating"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	      $("#serialNo"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	      $("#indoorModelNumber"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
           url=null;
           if(brandName!=""){
                url="/dashboard/getModelNumber/"+brandName+"/"+productType;
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
                   
                   }
               else{
                   jQuery(".error_container").html("No Product Type for corresponding selected brand.");
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
         	$("#star_rating"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	         $("#serialNo"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	         $("#indoorModelNumber"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
	         
         	 $('#ajax_loader').show();
         	  url = null;
         	  
         	  if((productType.replace(" ", "").toLowerCase().indexOf("ac") !=-1)){
         		 url="/dashboard/getStar/"+brandName+"/"+productType+"/"+modelNumber;
         		 
         	      $.ajax({
                      url : url,
                       dataType : "json",
                       cache: false,
                       async: false,
                      success: function(response, status, code){
                 
                        if(response.exists){
                            jQuery(".error_container").html("");
                            for( var index = 0; index < response.star.length; index ++){
                            	if(response.star[index]==null)
                            		continue;
                                    StarContainer ="<option value='"+ response.star[index] +"'>"+response.star[index]+"</option>";
                                    jQuery("#star_rating"+currentDivPos).append(StarContainer);
                               
                            }
                                
                            StarContainer ="</select></div></div>";
                                
                                jQuery("#star_rating"+currentDivPos).append(StarContainer); 
                                
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
         	  else{
          		 url="/dashboard/getsize/"+brandName+"/"+productType+"/"+modelNumber;
         	        
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
		 
		 
			$('body').on('change','.product_size',
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
						var starRating=$('#star_rating' + currentDivPos).val();
						/* alert(starRating); */
						
						$("#serialNo"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
				         $("#indoorModelNumber"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>');
						url = null;
						
						if(starRating==null ||starRating=='undefined' || starRating.trim().length==0){
							starRating="NA";
						}
							url = "/dashboard/getproduct-info/" + brandName + "/"
							+ productType + "/" + modelNumber + "/" + size+"/"+starRating;

						$.ajax({
									url : url,
									dataType : "json",
									cache : false,
									async : false,
									success : function(response, status, code) {

										if (response.exists) {
											jQuery(".error_msg").html("");

											if(response.hsnCode!=null)
											$('#cgst_tax' + currentDivPos)
											.val(response.cgst);
											if(response.igst!=null)
											$('#igst_tax' + currentDivPos)
											.val(response.igst);
											if(response.sgst!=null)
											$('#sgst_tax' + currentDivPos)
											.val(response.sgst);

											/* $('#unitPrice' + currentDivPos)
													.val(response.unitPrice); */
											/* globalUnitPrice=response.unitPrice;
											globalAvailableQuantity=response.availableQuantity; */
											/* $('#discountRate' + currentDivPos)
													.val(response.discountRate); */
													if(response.productSerialNo.length == 0){
														/* alert("no available qty"); */
														jQuery(".error_msg").html("<p class='alert alert-danger'>There is no available quantity for sale in the inventory.</p>");
													}
											$('#id' + currentDivPos)
													.val(response.productId);
											
											if(!checkAvailableQuantity(response,currentDivPos)){
												var errorDiv = $(".error_msg").html("<p style='font-size:18px;'><span style='color:red;'>Note:</span> No more available quantity of this model.</p>");
												var scrollPos = errorDiv.offset().top;
												$(window).scrollTop(scrollPos);
												return false;
											}
											$(".error_msg").html("");
										    for( var index = 0; index < response.productSerialNo.length; index ++){
										    	if(response.productSerialNo[index]==null)
				                            		continue;
						                    	   serialNoContainer ="<option value='"+ response.productSerialNo[index] +"'>"+response.productSerialNo[index]+"</option>";
						                           jQuery("#serialNo"+currentDivPos).append(serialNoContainer);
						                      
						                   }
						                       
						                       serialNoContainer ="</select></div></div>";
						                       
						                       jQuery("#serialNo"+currentDivPos).append(serialNoContainer);
						                       
						                       if(response.productIndoorSerialNo!=null){
						                       
						                       for( var index = 0; index < response.productIndoorSerialNo.length; index ++){
						                    	   if(response.productIndoorSerialNo[index]==null)
					                            		continue;
						                    	   indoorSerialNoContainer ="<option value='"+ response.productIndoorSerialNo[index] +"'>"+response.productIndoorSerialNo[index]+"</option>";
						                           jQuery("#indoorModelNumber"+currentDivPos).append(indoorSerialNoContainer);
						                      
						                   }
						                       
						                       indoorSerialNoContainer ="</select></div></div>";
						                       
						                       jQuery("#indoorModelNumber"+currentDivPos).append(indoorSerialNoContainer);
						                    }

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
			
			function checkAvailableQuantity(response,currentDivPos){
				var addProductsDivs = $("#saleProductsForm").children(".clonedDiv");
		        console.log(addProductsDivs)
		        var duplicateCount;
		        $(addProductsDivs).each(function(i, v) {
		        	var tempModelNumber = $("#modelContainer"+currentDivPos).val();
		        	console.log("tempModelNumber"+tempModelNumber);
		        	var currentModelNumber = $(".clonedDiv:last").find('.product_model').val();
		        	console.log("currentModelNumber"+currentModelNumber)
		        	if(tempModelNumber == currentModelNumber){
		        		duplicateCount = currentDivPos;
		        		console.log(duplicateCount + " avalbl " + response.availableQuantity )
		        	}
		        });
		        if(response.availableQuantity < duplicateCount ){
		        	console.log("no more available model left");
		        	return false;
	        		
	        	}
		        return true;
			}
			
			 $('body').on('change', '.serialNo', function() {
				  var serialNo = $(this).val();
				  serialNo=encodeURIComponent(serialNo);
		          var currentDivID = $(this).closest(".clonedDiv").attr("id");
		          var regex = /\d+/g;
				  var currentDivPos = currentDivID.match(regex);
		          console.log(currentDivID);
		          /* $("#indoorModelNumber"+currentDivPos).find('option').remove().end().append('<option value="">--Select--</option>'); */
	         	  url = null;
	                url="/dashboard/getpurchaseInvoiceNoBySerialNo/"+serialNo+"/";
	                $('#ajax_loader').show();
	          $.ajax({
	             url : url,
	              dataType : "json",
	              cache: false,
	              async: false,
	             success: function(response, status, code){
	        
	               if(response.exists){
	                   jQuery(".error_container").html("");
	                   
	                   $('#purchaseInvoiceNo'+currentDivPos).val(response.purchaseInvoiceid);
	                 // $('#purchaseInvoiceDate'+currentDivPos).val(response.purchaseInvoiceDate);
	                    $('#unitPrice' + currentDivPos).val(response.purchaseInvoiceUnitPrice); 
				 globalUnitPrice=response.purchaseInvoiceUnitPrice;
				//globalAvailableQuantity=response.availableQuantity;
				 $('#discountRate' + currentDivPos).val(response.purchaseinvoiceDiscountRate);
	                  }
	               else{
	                   jQuery(".error_container").html("No data available  for corresponding selected size.");
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
			 
			 $('body').on('change', '.indoor_serial_number', function() {
				  var indoor_serial_number = $(this).val();
				  indoor_serial_number=encodeURIComponent(indoor_serial_number);
		          var currentDivID = $(this).closest(".clonedDiv").attr("id");
		          var regex = /\d+/g;
				  var currentDivPos = currentDivID.match(regex);
		          console.log(currentDivID);
	         	  url = null;
	                url="/dashboard/getindoorPurchaseInvoiceNoBySerialNo/"+indoor_serial_number+"/";
	                $('#ajax_loader').show();
	          $.ajax({
	             url : url,
	              dataType : "json",
	              cache: false,
	              async: false,
	             success: function(response, status, code){
	        
	               if(response.exists){
	                   jQuery(".error_container").html("");
	                   
	                   $('#indoorPurchaseInvoiceNo'+currentDivPos).val(response.indoorPurchaseInvoiceid);
	                 // $('#purchaseInvoiceDate'+currentDivPos).val(response.purchaseInvoiceDate);
	                   
	                  }
	               else{
	                   jQuery(".error_container").html("No data available  for corresponding selected size.");
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
				$('#saleProductsForm').bootstrapValidator('validate');
				var clonedDiv = $("#addProducts1").clone().attr('id',
						'addProducts' + cloneCount);
				if($(".clonedDiv:last").find('.has-error').length>0){ //it's `
					console.log($(".clonedDiv:last").find('.has-error'));
					$("#customerDiv").hide(1000);
					$(".saveButtonDiv").show(1000);
				}else{
					$("#customerDiv").show(1000);
					$(".saveButtonDiv").hide(1000);
				}
			}
		 function clonedDivCheck(event) {
				$("#customerDiv").hide(1000);
				$(".saveButtonDiv").show(1000);
				cloneCount = $(".clonedDiv").length;
				cloneCount++;
				$('#saleProductsForm').bootstrapValidator('validate');
				var clonedDiv = $("#addProducts1").clone().attr('id',
						'addProducts' + cloneCount);
				if($(".clonedDiv:last").find('.has-error').length>0){ //it's `
					console.log($(".clonedDiv:last").find('.has-error'));
					$("#customerDiv").hide(1000);
					$(".saveButtonDiv").show(1000);
				}else{
					
				clonedDiv.insertAfter($('[id^=addProducts]:last')).find(
						"input[type='text'],input[type='number']").val("");
				var validator = $('#saleProductsForm').data('bootstrapValidator');
				$(clonedDiv).find("input, select").each(function() {
					var Id = $(this).attr('id');
					var newId = Id.replace(/[0-9]/g, '');
					var Name = $(this).attr('name');
					var newName = Name.replace(/[0-9]/g, '');
					$(this).attr({
						id : newId + cloneCount,
						name : newName + cloneCount
					});
					validator.addField($(this));
				});
			}
			}
		 var finalArray = {};
		   
		 var saveProduct = function() {
		        var innerArray = [];
		        var sumPrice = 0;
		        var addProductsDivs = $("#saleProductsForm").children(".clonedDiv");
		        console.log(addProductsDivs)
		        $(addProductsDivs).each(function(i, v) {
		            console.log($(this))
		          
		           productsArray = {};
		            $(this).find("input, select").each(function() {

		                 productvalue = $(this).val();
		                 productName = $(this).attr("name").replace(/[0-9]/g, '');;

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
		    var customerArray = {};
		    $("#customerButton").click(function() {
		    	//$('#customerForm').bootstrapValidator('validate');
				//var validator = $('#customerForm').data('bootstrapValidator');
		        var customerData = $("#customerForm").children("#customers");
		        if((customerData).find('.has-error').length>0){ //it's `
					alert("Please fill all the required fields.")
				}else{
		        $(customerData).find("input, select, textarea").each(function(i) {
		            var value = $(this).val();
		            var keyName = $(this).attr("name");
		            finalArray[keyName] = value;
		        });
		        //finalArray["customerArray"] = customerArray;

		        console.log(JSON.stringify(finalArray));
		        /* alert(JSON.stringify(finalArray)); */
		        $('#ajax_loader').show();
		          jQuery.ajax({
                    type : "POST",
                    contentType: "application/json",
                    url : "/dashboard/save-saleinvoice",
                    data : JSON.stringify(finalArray),

                    success : function(response, status, code){
                       window.location.href="/dashboard/finalSaleInvoice?si="+response;
                    }, 
                    complete: function(){
                        $('#ajax_loader').hide();
                      },
                    error : function(response, status, code){
                   	 /* alert("error "+response.responseText);
                   	 $(".error_msg").html("<div class='alert alert-danger text-center'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>"+response.responseText+"</strong></div>");
                       */   
                    }
                });   
				}
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
							id : newId + i,
							name : newName + i
						});

					});

				});
				saveProduct();
			});

		    /* $("#saleProductsForm, #customerForm").on('change keyup click',
					'input, #amountPaid', function(event) {
						calculation(this);
					}); */

			/* function finalCalculation() {
				var totalDiscountedAmount = 0;
				var totalAmount = 0;
				var addProductsDivs = $("#saleProductsForm").children(".clonedDiv");
				// console.log(addProductsDivs)
				// var noOfProductsDivs = addProductsDivs.length;
				// console.log(noOfProductsDivs)
				// totalDiscount = 0;
				$(addProductsDivs).each(
						function(i, v) {

							i = i + 1
							totalDiscountedAmount = parseInt(totalDiscountedAmount)
									+ parseInt($("#discountedAmount" + i).val());
							var totalUnitPrice = $("#unitPrice" + i).val()
									* $("#quantity" + i).val();
							console.log("totalUnitPrice" + totalUnitPrice);
							totalAmount = parseFloat(totalAmount, 2)
									+ parseFloat(totalUnitPrice, 2);
							var finalAmount = parseInt(totalAmount)
									- parseInt(totalDiscountedAmount);
							var leftBalance = finalAmount
									- parseFloat($("#amountPaid").val(), 2);
							// console.log("noOfProductsDivs: "+ $("#discountedAmount"+i).val());
							console.log("totalDiscountedAmount "
									+ totalDiscountedAmount + "finalAmount "
									+ finalAmount + "balanceleft: " + leftBalance);
							$("#totalDiscountedAmount").val(totalDiscountedAmount);
							$("#finalAmount").val(parseFloat(finalAmount, 2));
							$("#balanceLeft").val(leftBalance);
							
							if($("#balanceLeft").val() == "NaN"){
								$("#balanceLeft").val('');
							}
							if($("#finalAmount").val() == "NaN"){
								$("#finalAmount").val('');
							}
							if($("#totalDiscountedAmount").val() == "NaN"){
								$("#totalDiscountedAmount").val('');
							}

						});
			}; */

			/* var calculation = function(event) {
				var proId = $(event).attr('id');
				proNum = proId.replace(/\D/g, '');
				var qtyVal = $("#quantity" + proNum).val();
				// console.log(qtyVal);
				var discVal = $("#discountRate" + proNum).val();
				// console.log(discVal);
				var unitPr = $("#unitPrice" + proNum).val();
				// console.log(unitPr);
				var discAmount = unitPr * (discVal / 100) * qtyVal;
				$("#discountedAmount" + proNum).val(parseFloat(discAmount, 2));
				// var cls = $(this).attr('class');
				// if(cls == "form-control qty"){
				//     qtyVal = $(this).val();
				// }
				// if(cls == "form-control disc"){
				//     discVal = $(this).val();
				// }
				// console.log(qtyVal+" "+discVal);
				// if(discVal > 0 && qtyVal > 0){
				// var totalVAl = Number(discVal) + Number(qtyVal);
				// console.log(totalVAl);
				// }
				finalCalculation();
			}; */
			
			$(document).ready(function(){
				console.log("started")	
				$("#customerDiv").hide();
				$('#saleProductsForm')
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
										'starRating': {
											validators : {
												notEmpty : {
													message : 'Select star rating please.'
												}
											}
										},
										'indoorModelNumber':{
											validators : {
												notEmpty : {
													message : 'Select indoor model number please.'
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
												},
                                                regexp : {
                                                    regexp : /^[0-9_\.]+$/,
                                                    message : 'Only numbers'
                                                }
											}
										},
										'unitPrice': {
											validators : {
												notEmpty : {
													message : 'Select unitprice please.'
												},
	                                            regexp : {
	                                                regexp : /^[0-9_\.]+$/,
	                                                message : 'Only numbers allowed'
	                                            }
											}
										},
										
										'discountRate': {
											validators : {
												notEmpty : {
													message : 'Select discount rate please.'
												},
                                                regexp : {
                                                    regexp : /^[0-9_\.]+$/,
                                                    message : 'Only numbers'
                                                }
											}
										},
										'discountedAmount': {
											validators : {
												notEmpty : {
													message : 'click on this field to get discounted amount'
												},
                                                regexp : {
                                                    regexp : /^[0-9_\.]+$/,
                                                    message : 'Only numbers'
                                                }
											}
										}
									}
									
								});
				});
			
				
				
			 $(document)
		      .ready(
		              function() {
					$('#customerForm')
					.bootstrapValidator(
							{

								message : 'This value is not valid',
								feedbackIcons : {

								},
								fields : {
									'invoiceNumber' : {
										validators : {
											notEmpty : {
												message : 'Please fill invoice number.'
											}
										}
									},
									'totalDiscount' : {
										validators : {
											
											notEmpty : {
												message : "Total discount can't be empty"
											},
                                            regexp : {
                                                regexp : /^[0-9_\.]+$/,
                                                message : 'Only numbers allowed'
                                            }
										}
									},
									'customerContact':{
										validators : {
											notEmpty : {
												message : 'Please enter contact number.'
											},
											stringLength: {
						                        min: 10,
						                        max: 10,
						                        message: 'The customer number has to of 10 digits'
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
												message : "Final amount Can't be empty"
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
												message : 'Please select mode of payment.'
											}
										}
									},
									'customerAddress':{
										validators : {
											notEmpty : {
												message : 'Fill customer address.'
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
									'saleInvoicedate': {
										validators : {
											notEmpty : {
												message : 'Please fill date.'
											}
										}
									},
									'balanceLeft': {
										validators : {
											notEmpty : {
												message : 'Select role please.'
											},
                                            regexp : {
                                                regexp : /^[0-9_\.]+$/,
                                                message : 'Only numbers allowed'
                                            }
										}
									},
									'comments': {
										validators : {
											notEmpty : {
												message : 'Fill comment please.'
											}
										}
									}
								}
								
							});
					
					$("#invoiceNo").change(function() {
				    	   var invoiceNo=$('#invoiceNo').val();
				    	   /* alert(invoiceNo); */
				    	   if(invoiceNo.trim().length>0){
				    		   invoiceNo=encodeURIComponent(invoiceNo);
				    	   var url="/dashboard/checkUniqueSaleInvoice/"+invoiceNo+"/";
				    	   $.ajax({
				    	           url : url,
				    	            dataType : "json",
				    	            cache: false,
				    	            async: false,
				    	           success: function(data) {
				    	           if(data.exists){
				    	            $(".error_invoice").removeClass('hidden');
				    	                 $(".error_invoice").html(data.message);
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
</body>