
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<body class="skin-black">

	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Add products
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
							<h3 class="box-title">Add products</h3>


						</div>
						<!-- /.box-header -->
						<!-- form start -->

						<form role="form" id="addProductForm"
							action="/dashboard/addproduct" method="post">
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
											<label for="brand">Brand</label> <input type="text"
												placeholder="eg(LG,Voltas)" name="brand" id="brand"
												class="form-control">
										</div>

									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="productType">Product Type</label>
											<div class="error_productType hidden"></div>
											<input type="text" placeholder="eg(Split,window,double door)"
												name="productType" id="productType" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="modelNumber">Model Number</label>
											<div class="error_modelNumber hidden"></div>
											<input type="text" placeholder="Model Number"
												id="modelNumber" name="modelNumber" class="form-control">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="size">Size</label>
											<div class="error_size hidden"></div>
											<input type="text" placeholder="eg(1 Ton,2 Ton)" name="size"
												id="size" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="quantity">Avalible Quantity</label>
											<div class="error_quantity hidden"></div>
											<input type="number" placeholder="Available Quantity"
												id="quantity" min="0" value="0" name="quantity"
												class="form-control">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="quantity">Reorder Point</label>
											<div class="error_reorderPoint hidden"></div>
											<input type="number" id="reorderPoint" min="0"
												name="reorderPoint" value="0" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="star">Star Rating</label> <input type="text"
												placeholder="eg(No star,1 star,2 star)" id="star"
												name="star" class="form-control">
											<div class="help-block"></div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group"></div>
									</div>
								</div>

							</div>
							<!-- /.box-body -->
							<div class="clearfix">
								<div class="box-footer">
									<button class="btn btn-primary" id="addProductButton"
										type="submit" value="Submit">Submit</button>
								</div>
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
            
            $("#addProductButton").on('click',function(){
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
            	}
            });
            
            $(document)
            .ready(
                    function() {
                        console.log("started")
                        $('#addProductForm')
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
                                                size : {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'Size is required'
                                                        }/* ,
                                                        
                                                        regexp : {
                                                            regexp : /^[0-9_\.]+$/,
                                                            message : 'Mobile Number consist of only numeric values'
                                                        } */
                                                    }
                                                },
                                                quantity : {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'Quantity is required'
                                                        },
                                                        regexp : {
                                                            regexp : /^[0-9_\.]+$/,
                                                            message : 'Quantity consist of only numeric values'
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
                                                }
                                            }
                                        });
                    });
            </script>
