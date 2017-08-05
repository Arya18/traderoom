<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<c:url value="/resources/js/jquery.js" var="Jquery" />


<!-- jQuery 2.0.2 -->
<!--  <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script> -->
<script src="${Jquery}" type="text/javascript"></script>
<c:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<c:url value="/resources/css/font-awesome.min.css" var="awesomeCss" />
<c:url value="/resources/css/ionicons.min.css" var="IoniconsCss" />
<c:url value="/resources/css/AdminLTE.css" var="AdminLTECss" />



<!-- bootstrap 3.0.2 -->
<link href="${bootstrapCss}" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="${awesomeCss}" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="${IoniconsCss}" rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="${AdminLTECss}" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href=${Accordion } />