<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
          <body class="skin-black">
        
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Add Firm
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
                                    <h3 class="box-title">Add Firm</h3>
                                     
                                   
                                </div><!-- /.box-header -->
                                <!-- form start -->
                                
                                <form role="form" id="addFirmForm" action="/dashboard/addfirm" method="post">
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
                                            <label for="shortName">Short Name</label>
                                            <input type="text" placeholder="DCH" id="shortName" name="shortName" required class="form-control">
                                        </div>
                                    </div>
                                  </div>
                                    <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="contactNo">contact Number</label>
                                            <input type="text" placeholder="contact No" required name="contactNo" id="contactNo" class="form-control">
                                           
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" placeholder="Email" id="email" name="email" required class="form-control">
                                        </div>
                                    </div>
                                  </div>
                                  <div class="row">
                                     <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="address">Address Line1</label>
                                            <input type="text" placeholder="Address" required name="addresLine1" id="addresLine1" class="form-control">
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="address">Address Line2</label>
                                            <input type="text" placeholder="Address" name="addressLine2" id="addressLine2" class="form-control">
                                        </div>
                                    </div>
                                    </div>
                                    
                                      <div class="row">
                                     <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="address">City</label>
                                            <input type="text" placeholder="Address" required name="city" id="city" class="form-control">
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="address">State</label>
                                            <input type="text" placeholder="Address" name=state id="state" class="form-control">
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
            $("#name").change(function(){
            	var str=$("#name").val();
            	var matches = str.match(/\b(\w)/g);              
            	var shortName = matches.join(''); 
            	$("#shortName").val(shortName);
            });
            </script>
            
          