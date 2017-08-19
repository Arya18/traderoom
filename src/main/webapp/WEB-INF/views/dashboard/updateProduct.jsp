<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<style>
.error_brand {
	display: inline-block;
	margin-left: 12px;
	color: red;
}
</style>
<body class="skin-black">

	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				update Products
				<!-- <small>Control panel</small> -->
			</h1>
			<br />
			<!-- <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Blank page</li>
                    </ol> -->
			<div class="row">
				<div class="col-md-12">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Update products</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->

						<form role="form" id="updateProductForm"
							action="/dashboard/update-product/${product.id}" method="post">
							<c:if test="${hasError eq true}">

								<div class="alert alert-danger text-center">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${message}</strong>
								</div>
							</c:if>

							<div class="box-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="brand">Brand</label>
											<div class="error_brand hidden"></div>
											<input type="text" placeholder="eg(LG,Voltas)" name="brand"
												id="brand" value="${product.brand}" class="form-control">

										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="productType">Product Type</label> <input
												type="text" placeholder="eg(Split,window,double door)"
												name="productType" value="${product.productType}"
												id="productType" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="modelNumber">Model Number</label> <input
												type="text" placeholder="Model Number" id="modelNumber"
												name="modelNumber" value="${product.modelNumber}"
												class="form-control">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="size">Size</label> <input type="text"
												placeholder="eg(1 Ton,2 Ton)" name="size" id="size"
												value="${product.size}" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="quantity">Avalible Quantity</label> <input
												type="number" placeholder="Available Quantity" id="quantity"
												min="0" name="quantity" value="${product.quantity}"
												class="form-control">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="reorderPoint">Reorder Point</label> <input
												type="number" placeholder="Reorder point" id="reorderPoint"
												min="0" name="reorderPoint" value="${product.reorderPoint}"
												class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="size">Star</label> <input type="text"
												placeholder="eg(No star,1 star,2 star)" name="star"
												id="star" value="${product.star}" class="form-control">
											<div class="help-block"></div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="star">HSN code</label> <input type="text"
												placeholder="Enter HSN code" id="hsnCode"
												name="hsnCode" class="form-control">
											<div class="help-block"></div>
										</div>
									</div>
								</div>
								<div class="row"><br/>
									<div class="col-md-12">
										<label>GST Taxation</label>
									</div>
									<div class="col-md-4">
										<div class="form-group">
										<label for="CGST">CGST</label>
											<input type="checkbox" name="cgstCheck" id="cgstCheck">
											<input type="text" name="cgstValue" id="cgstValue">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
										<label for="IGST">IGST</label>
											<input type="checkbox" name="igstCheck" id="igstCheck">
											<input type="text" name="igstValue" id="igstValue">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
										<label for="SGST">SGST</label>
											<input type="checkbox" name="sgstCheck" id="sgstCheck">
											<input type="text" name="sgstValue" id="sgstValue">
										</div>
									</div>
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button class="btn btn-primary" type="submit"
									id="updateProductButton" value="Submit">Submit</button>
							</div>
						</form>
					</div>
					<!-- /.box -->

					<!-- Form Element sizes -->


				</div>
			</div>
		</section>

		<!-- Main content -->
		<section class="content"></section>
		<!-- /.content -->
	</aside>
	<!-- /.right-side -->
</body>

<script>
                	
        	/* function validateForm(){
        		if($(".error_brand").hasClass("hidden")){
        			$("#updateProductForm").submit();
        		}
        		else{
        			return false;
        		}
        		
        	} */
        	
        	$("#updateProductButton").on('click',function(){
            	var productType = $("#productType").val();
            	console.log(productType)
            	if(productType.replace(" ", "").toLowerCase().indexOf("ac") ==-1){
            		$(".help-block").html("");
            		}
            		else{
            			console.log($("#star").val());
                		if($("#star").val() == ''){
                			console.log($("#star").val());
                			$(".help-block").html("<p style='color:red;'>Please enter a valid number</p>");
                			return false;
            		}
                		else{
                			$(".help-block").html("");
                			  // $('#updateProductForm').submit();
                		}
            	}
            });
        	
            $(document)
            .ready(
                    function() {
                        console.log("started")
                        $('#updateProductForm')
                                .bootstrapValidator(
                                        {

                                            message : 'This value is not valid',
                                            feedbackIcons : {

                                            },
                                            fields : {
                                            	brand : {
                                                    message : 'The brand is not valid',
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'The brand is required and cannot be empty'
                                                        }
                                                    
                                                        /* regexp : {
                                                            regexp : /^[a-zA-Z0-9_]+$/,
                                                            message : 'The username can only consist of alphabetical, number and underscore'
                                                        } */
                                                    }
                                                },
                                                productType : {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'The productType is required and cannot be empty'
                                                        }
                                                        
                                                    }
                                                },

                                                modelNumber : {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'The model number is required'
                                                        }/* ,
                                                        regexp : {
                                                            regexp : /^[0-9_\.]+$/,
                                                            message : 'Model number consist of only numeric values'
                                                        } */

                                                    }
                                                },
                                                
                                                quantity : {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'quantity is required'
                                                        },
                                                        regexp : {
                                                            regexp : /^[0-9_\.]+$/,
                                                            message : 'quantity consist of only numeric values'
                                                        }
                                                    }
                                                },

                                                reorderPoint : {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'Please fill reorder point.'
                                                        },
                                                        regexp : {
                                                            regexp : /^[0-9_\.]+$/,
                                                            message : 'Reorder point consist of only numeric values'
                                                        }
                                                    }
                                                },
                                                hsnCode: {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'Please enter HSN code'
                                                        },
                                                    }
                                                }
                                            }
                                        });
                    });
            </script>