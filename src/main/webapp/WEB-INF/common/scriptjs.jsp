<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<c:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<c:url value="/resources/js/AdminLTE/app.js" var="AdminLTEApp" />

<c:url value="/resources/js/plugins/datatables/jquery.dataTables.js"
	var="DataTableJs" />

<c:url value="/resources/js/plugins/datatables/dataTables.bootstrap.js"
	var="DataTableBootstrapJs" />

<c:url value="/resources/js/custom/commonUtility.js"
	var="commonUtilityJs" />

<c:url
	value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"
	var="bootstrapValidatorJs" />


<!-- Bootstrap -->
<script src="${bootstrapJs}" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="${AdminLTEApp}" type="text/javascript"></script>

<script src="${commonUtilityJs}" type="text/javascript"></script>

<script src="${DataTableJs}" type="text/javascript"></script>

<script src="${DataTableBootstrapJs}" type="text/javascript"></script>

<c:url value="/resources/js/plugins/input-mask/jquery.inputmask.js"
	var="inputMask" />

<script src="${inputMask}" type="text/javascript"></script>

<c:url
	value="/resources/js/plugins/input-mask/jquery.inputmask.date.extensions.js"
	var="dateExtension" />
<script src="${dateExtension}" type="text/javascript"></script>

<c:url
	value="/resources/js/plugins/input-mask/jquery.inputmask.extensions.js"
	var="Extension" />
<script src="${Extension}" type="text/javascript"></script>

<c:url value="/resources/js/plugins/daterangepicker/daterangepicker.js"
	var="dateRangePicker" />
<script src="${dateRangePicker}" type="text/javascript"></script>

<c:url
	value="/resources/js/plugins/colorpicker/bootstrap-colorpicker.min.js"
	var="colorPicker" />
<script src="${colorPicker}" type="text/javascript"></script>

<c:url
	value="/resources/js/plugins/timepicker/bootstrap-timepicker.min.js"
	var="timePicker" />
<script src="${timePicker}" type="text/javascript"></script>

<script src="${bootstrapValidatorJs}" type="text/javascript"></script>

