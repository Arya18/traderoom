<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
          <body class="skin-black">
        
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Add Supplier
                        <!-- <small>Control panel</small> -->
                    </h1><br/>
                    <!-- <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Blank page</li>
                    </ol> -->
                    <div class="row">
                    	<div class="col-md-12">
                            <!-- general form elements -->
                            <div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">Add Supplier</h3>
                                     
                                   
                                </div><!-- /.box-header -->
                                <!-- form start -->
                                
                                <form role="form" id="addSupplierForm" action="/dashboard/addsupplier" method="post">
                                 <c:if test="${hasError eq true}">
     
      							<div class="alert alert-danger text-center">
	    						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	   								 <strong>${message}</strong>
	 						 	</div>
     							</c:if>
     							
     						
                                    <div class="box-body">
                                    <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="name">Name</label>
                                            <input type="text" placeholder="Name" required name="name" id="name" class="form-control">
                                           
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="address">Address</label>
                                            <input type="text" placeholder="Address" required name="address" id="address" class="form-control">
                                        </div>
                                    </div>
                                  </div>
                                  <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="contactNo">Contact number</label>
                                            <input type="text" placeholder="contact No" id="contactNo" name="contactNo" required class="form-control">
                                        </div>
                                    </div>
                                    
                                       <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" placeholder="Email" name="email" id="email" class="form-control">
                                        </div>
                                    </div>
                                    </div>
                                    
                                          <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="tinNumber">Tin Number</label>
                                            <input type="text" placeholder="Tin No" id="tinNumber" name="tinNumber" class="form-control">
                                        </div>
                                    </div>
                                    
                                       <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="gstNumber">GST Number</label>
                                            <input type="text" placeholder="GST Number" name="gstNumber" id="gstNumber" class="form-control">
                                        </div>
                                    </div>
                                    </div>
                              
                                    </div>
                                    
                                    
                                    <!-- /.box-body -->

                                    <div class="box-footer">
                                        <button class="btn btn-primary" type="submit">Submit</button>
                                    </div>
                                </form>
                            </div><!-- /.box -->

                            <!-- Form Element sizes -->
                            

                        </div>
                    </div>
                </section>

                <!-- Main content -->
                <section class="content">


                </section><!-- /.content -->
            </aside><!-- /.right-side -->
            </body>
            
            <script>
            $(document)
            .ready(
                    function() {
                        console.log("started")
                        $('#addSupplierForm')
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
                                                            message : 'Contact number should be of 10 digits.'
                                                        }

                                                    }
                                                }
                                            }
                                        });
                    });
            </script>