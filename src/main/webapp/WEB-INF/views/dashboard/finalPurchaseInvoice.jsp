<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body class="skin-black">
	<aside class="right-side">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
			Invoice Sequence:<small>${purchaseInvoice.invoiceSequence}</small>
				Invoice :<small>${purchaseInvoice.cmpyPurchaseInvoiceNo}</small>
			</h1>
			<!--  <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Examples</a></li>
                        <li class="active">Blank page</li>
                    </ol> -->
		</section>

		<!--    <div class="pad margin no-print">
                    <div class="alert alert-info" style="margin-bottom: 0!important;">
                        <i class="fa fa-info"></i>
                        <b>Note:</b> This page has been enhanced for printing. Click the print button at the bottom of the invoice to test.
                    </div>
                </div> -->

		<!-- Main content -->
		<section class="content invoice">
			<!-- title row -->
			<div class="row">
				<div class="col-xs-12">
					<h2 class="page-header">
						<i class="fa fa-globe"></i> Deepak Cool Home <small
							class="pull-right">Date: ${purchaseInvoice.date}</small>
					</h2>
				</div>
				<!-- /.col -->
			</div>
			<!-- info row -->
			<div class="row invoice-info">
				<div class="col-sm-4 invoice-col">
					To
					<address>
						<strong>${firm.name}</strong><br>${firm.addresLine1}<br>
						${firm.addressLine2}<br> Phone: ${firm.contactNumber}<br />
						Email:${firm.email}<br /> GST Number:
						${firm.gstNumber}<br />Tin Number:${firm.tinNumber}
					</address>
				</div>
				<!-- /.col -->
				<div class="col-sm-4 invoice-col">
					From
					<address>
						<strong>${purchaseInvoice.supplier.name}</strong><br>
						${purchaseInvoice.supplier.address}<br>
						Phone:${purchaseInvoice.supplier.contactNo}<br />
						Email:${purchaseInvoice.supplier.email}<br />
						GST Number:${purchaseInvoice.supplier.tinNumber}<br />
						TIN Number:${purchaseInvoice.supplier.gstNumber}
					</address>
				</div>
				<!-- /.col -->
				<div class="col-sm-4 invoice-col">
				<b>Invoice Sequence:${purchaseInvoice.invoiceSequence}</b><br />
					<b>Invoice/Challan ${purchaseInvoice.cmpyPurchaseInvoiceNo}</b><br /> <br />
					<!--  <b>Previous Invoice No:</b> #dd84545<br/>
                            <b>Payment Due:</b> 2/22/2014<br/>
                            <b>Payment Amount:Rs1025</b>  -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<!-- Table row -->
			<div class="row">
				<div class="col-xs-12 table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>

								<th>Qty</th>
								<th>Product Type</th>
								<th>Brand</th>
								<th>Description</th>
								<th>Unit Price(in Rs)</th>
								<th>Trade Discount</th>
								<th>Rebate Discount</th> 
								<th>HSN Code</th>
								<th>Bill Amount</th>
								<th>Basic Amount</th>
								<th>Tax Amount</th>
								<th>Serial Number</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="productDetail" items="${productList}">
								<tr>
									<td>${productDetail.quantity}</td>
									<td>${productDetail.productType}</td>
									<td>${productDetail.brand}</td>
									<td>model No:${productDetail.model}
										,size:${productDetail.size}</td>
									<td>${productDetail.unitPrice }</td>
									<td>${productDetail.tradeDiscount }</td>
									<td>${productDetail.rebateDiscount }</td>
									<td>${productDetail.hsnCode }</td>
									<td>${productDetail.billAmount}</td>
									<td>${productDetail.billAmount-productDetail.singleUnitTax}</td>
									<td>${productDetail.singleUnitTax} 
									<c:if test="${not empty productDetail.cgst}">(cgst:${productDetail.cgst}%)
									</c:if><c:if test="${not empty productDetail.sgst}">,(sgst:${productDetail.sgst}%)
									</c:if><c:if test="${not empty productDetail.igst}">,(igst:${productDetail.igst}%)
									</c:if></td> 
									<td>${productDetail.serialNo}<c:if
											test="${not empty productDetail.indoorSerialNo}">
                                         >>Indoor Serial No:${productDetail.indoorSerialNo}
                                        </c:if>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<div class="row">

				<div class="col-xs-6">
					<p class="lead">Final Calculation</p>
					<div class="table-responsive">
						<table class="table">


							<tr>
								<th>Total Taxable Amount:</th>
								<td>${purchaseInvoice.taxAmount}</td>
							</tr>

							<tr>
								<th style="width: 50%">Final Amount:(in Rs)</th>
								<td>${purchaseInvoice.finalAmount }</td>
							</tr>
							<!--  <tr>
                                        <th>Tax (13%)</th>
                                        <td>1404</td>
                                    </tr> -->
							<!--      <tr>
                                        <th>Previous Balance:</th>
                                        <td>1025</td>
                                    </tr> -->

							<tr>
								<th>Amount paid:</th>
								<td>${purchaseInvoice.amountPaid}</td>
							</tr>
							<tr>
								<th>Balance left:</th>
								<td>${purchaseInvoice.balanceLeft}</td>
							</tr>
							<tr>
								<th>Payment Mode:(Cash or Cheque)</th>
								<td>${purchaseInvoice.paymentMode}
								<c:if test="${purchaseInvoice.paymentMode eq 'Cheque'}">
								<br>Cheque Number: ${purchaseInvoice.chequeNumber} <br>Bank Name :${purchaseInvoice.bankName} <br>Cheque Date:${purchaseInvoice.chequeDate}
								</c:if>
								</td>
							</tr>
						</table>
					</div>
					<input type="button" value="Print" onclick="window.print()" />
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<!--       this row will not appear when printing
                    <div class="row no-print">
                        <div class="col-xs-12">
                            <button class="btn btn-default" onclick="window.print();"><i class="fa fa-print"></i> Print</button>
                            <button class="btn btn-success pull-right"><i class="fa fa-credit-card"></i> Submit Payment</button>  
                            <button class="btn btn-primary pull-right" style="margin-right: 5px;"><i class="fa fa-download"></i> Generate PDF</button>
                        </div>
                    </div> -->
		</section>
		<!-- /.content -->
	</aside>
	<!-- /.right-side -->
</body>