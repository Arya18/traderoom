<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<body class="skin-black">

	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Update Supplier
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
							<h3 class="box-title">Update Supplier</h3>


						</div>
						<!-- /.box-header -->
						<!-- form start -->

						<form role="form"
							action="/dashboard/update-supplier/${supplier.id}"
							id="updateSupplierForm" method="post">
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
											<label for="name">Name</label> <input type="text"
												placeholder="Name" name="name" id="name"
												value="${supplier.name}" class="form-control">

										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="address">Address</label> <input type="text"
												placeholder="Address" name="address" id="address"
												value="${supplier.address}" class="form-control">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="modelNumber">Contact number</label> <input
												type="text" placeholder="contact No" id="contactNo"
												name="contactNo" value="${supplier.contactNo}"
												class="form-control">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="size">Email</label> <input type="text"
												placeholder="Email" name="email" id="email"
												value="${supplier.email}" class="form-control">
										</div>
									</div>
								</div>

							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button class="btn btn-primary" type="submit">Submit</button>
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
            $(document)
            .ready(
                    function() {
                        console.log("started")
                        $('#updateSupplierForm')
                                .bootstrapValidator(
                                        {

                                            message : 'This value is not valid',
                                            feedbackIcons : {

                                            },
                                            fields : {
                                            	name : {
                                                    message : 'The name is not valid',
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'The name is required and cannot be empty'
                                                        }
                                                    
                                                        /* regexp : {
                                                            regexp : /^[a-zA-Z0-9_]+$/,
                                                            message : 'The username can only consist of alphabetical, number and underscore'
                                                        } */
                                                    }
                                                },
                                                address : {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'The address is required and cannot be empty'
                                                        }
                                                        
                                                    }
                                                },

                                                contactNo : {
                                                    validators : {
                                                        notEmpty : {
                                                            message : 'The contact number is required'
                                                        },
                                                        regexp : {
                                                            regexp : /^[0-9_\.]+$/,
                                                            message : 'Contact number consist of only numeric values'
                                                        } ,
                                                        stringLength : {
                                                            min : 10,
                                                            max : 10,
                                                            message : 'Contact number should not be less than 10.'
                                                        }

                                                    }
                                                }
                                            }
                                        });
                    });
            </script>