//file upload extension validation 
var _validFileExtensions = [".csv"];    
function validateFileInput(oInput) {
    if (oInput.type == "file") {
        var sFileName = oInput.value;
        console.log(sFileName)
         if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                console.log(sCurExtension)
                console.log(sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase())
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }
             
            if (!blnValid) {
                /*alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));*/
                $(".importErrMsg").html("<div class='alert alert-danger text-center'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>Sorry this " +sFileName+" file extension is not valid, allowed extensions are: "+_validFileExtensions+"  </strong></div>");
                /*setTimeout(function() {
                	console.log("close alert")
                	$(".importErrMsg").html("");
                }, 2000);*/
                
                oInput.value = "";
                return false;
            }
        }
    }
    return true;
}

	$("#productsForm, #customerForm, #supplierForm, #saleProductsForm").on('change keyup click',
		'input, #amountPaid', function(event) {
			calculation(this);
		});
	
	var calculation = function(event) {
		var proId = $(event).attr('id');
		// console.log(proId)
		proNum = proId.replace(/\D/g, '');
		var totalDiscountedAmount = 0;
		var totalAmount = 0;
		var finalBillAmount = 0;
		var totalTaxableAmount= 0;
		var billAmount = 0;
		var formId = $(event).closest("form").attr("id");
		// console.log(formId);
		if(formId == "supplierForm"){
			var addProductsDivs = $("#" +formId).children("#suppliers");
		}
		else if(formId == "customerForm") {
			var addProductsDivs = $("#" +formId).children("#customers");
		}
		else{
			var addProductsDivs = $("#" +formId).children(".clonedDiv");
		}
		// console.log(addProductsDivs)
		// var noOfProductsDivs = addProductsDivs.length;
		// console.log(noOfProductsDivs)
		// totalDiscount = 0;
		$(addProductsDivs).each(
				function(i, v) {
					i = i + 1;
					var unitPrice = parseFloat($("#unitPrice" + i).val()) * parseFloat($("#quantity" + i).val());
					var discountValue = parseFloat($("#rebate_discount" + i).val()) + parseFloat($("#trade_discount" + i).val());
					console.log("discountValue", discountValue);
					console.log("unitPrice" + unitPrice);
					if(formId == 'productsForm' || formId == "supplierForm"){
						 billAmount = unitPrice - discountValue;
					}else{
						var discountValue = parseFloat($("#trade_discount" + i).val());
						 billAmount = unitPrice - discountValue ;
					}
					console.log("totalUnitPrice", billAmount);
					$("#bill_amount"+i).val(parseFloat(billAmount, 2));
					// totalAmount = parseFloat(totalAmount, 2) + parseFloat(billAmount, 2);
					var cgstTaxValue = $("#cgst_tax" + i ).val();
					var igstTaxValue = $("#igst_tax" + i).val() ;
					var sgstTaxValue = $("#sgst_tax"+ i ).val() ;
					var totalTaxValue = parseFloat(sgstTaxValue, 2) + parseFloat(cgstTaxValue, 2) + parseFloat(igstTaxValue, 2);
					console.log("totalTaxValue " + totalTaxValue);
					var gstTaxAmount = (billAmount * totalTaxValue) / (100 + totalTaxValue);
					console.log(gstTaxAmount);
					$("#singleUnitTax"+i).val(parseFloat(gstTaxAmount, 2));
					//var discountedPercentage = $("#discountedPercent").val();
					// totalDiscountedAmount = $("#totalDiscountedAmount").val(); 
					// var finalAmount = parseInt(totalAmount)	- parseInt(totalDiscountedAmount);
					finalBillAmount = parseFloat(finalBillAmount) + parseFloat(billAmount);
					console.log("finalBillAmount  ===== ", finalBillAmount);
					console.log($("#finalAmount"));
					$("#finalAmount").val(parseFloat(finalBillAmount,2));
					totalTaxableAmount = totalTaxableAmount + gstTaxAmount;
					$("#taxableAmount").val(parseFloat(totalTaxableAmount, 2));
					totalDiscountedAmount = totalDiscountedAmount + discountValue;
					$("#totalDiscountedAmount").val(parseFloat(totalDiscountedAmount, 2));
					/*var leftBalance = finalAmount
							- parseFloat($("#amountPaid").val(), 2);*/
					// console.log("noOfProductsDivs: "+ $("#discountedAmount"+i).val());
					
					
					// $("#finalAmount").val(parseFloat(finalAmount, 2));
					/*$("#balanceLeft").val(leftBalance);
					
					if($("#balanceLeft").val() == "NaN"){
						$("#balanceLeft").val('');
					}*/
					if($("#finalAmount").val() == "NaN"){
						$("#finalAmount").val('');
					}
					if($("#totalDiscountedAmount").val() == "NaN"){
						$("#totalDiscountedAmount").val('');
					}
					
					/*if(formId == "supplierForm"){*/
						// var taxPercentage = $("#taxPercentage").val();
						// var taxableAmount = (taxPercentage/100) * (finalAmount);
						// var totalAmt = taxableAmount + finalAmount;						
						leftBalance = finalBillAmount - parseFloat($("#amountPaid").val(), 2);
						// console.log("totalDiscountedAmount "
						// 		+ totalDiscountedAmount + "finalAmount "
						// 		+ finalAmount + "balanceleft: " + leftBalance + "taxPercentage " + taxPercentage + "taxableAmount "+ taxableAmount);
						
						$("#balanceLeft").val(leftBalance);
						// $("#taxableAmount").val(taxableAmount);
						// $("#finalAmount").val(totalAmt);
						if($("#balanceLeft").val() == "NaN"){
							$("#balanceLeft").val('');
						}
						/*if($("#taxableAmount").val() == "NaN"){
							$("#balanceLeft").val('');
						}
						if($("#totalAmount").val() == "NaN"){
							$("#balanceLeft").val('');
						}*/
					/*}*/

				});
	};

//printable JS
$(document).ready(function(){
		
	
		$("#printable").find('.print').on('click', function() {
				console.log("start printing")
		$("#printable").print({

		// Use Global styles
		globalStyles : false, 

		// Add link with attrbute media=print
		mediaPrint : false, 

		//Custom stylesheet
		stylesheet : "http://fonts.googleapis.com/css?family=Inconsolata", 

		//Print in a hidden iframe
		iframe : false, 

		// Don't print this
		noPrintSelector : ".avoid-this",

		// Add this on top
		/*append : "Free jQuery Plugins<br/>",*/ 

		// Add this at bottom
		/*prepend : "<br/>jQueryScript.net",*/

		// Manually add form values
		manuallyCopyFormValues: true,

		// resolves after print and restructure the code for better maintainability
		deferred: $.Deferred(),

		// timeout
		timeout: 250,

		// Custom title
		title: null,

		// Custom document type
		doctype: '<!doctype html>'

		});		
		});
});
