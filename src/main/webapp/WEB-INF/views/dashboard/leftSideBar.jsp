
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="left-side sidebar-offcanvas">
	<!-- sidebar: style can be found in sidebar.less -->
	<c:if test="${sessionScope.HTTP_SESSION_USER != null }">
		<section class="sidebar">
			<!-- Sidebar user panel -->
			<div class="user-panel">
				<div class="pull-left image">
					<img src="/resources/img/avatar3.png" class="img-circle"
						alt="User Image" />
				</div>
				<div class="pull-left info">
					<p>Hello, ${HTTP_SESSION_USER.name}</p>

					<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
				</div>
			</div>
			<!-- search form -->
			<form action="#" method="get" class="sidebar-form">
				<div class="input-group">
					<input type="text" name="q" class="form-control"
						placeholder="Search..." /> <span class="input-group-btn">
						<button type='submit' name='seach' id='search-btn'
							class="btn btn-flat">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</form>
			<!-- /.search form -->
			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu">

				<c:if test="${HTTP_SESSION_USER.role=='Admin'}">

					<li class="treeview"><a href="#"> <i class="fa fa-wrench"></i>
							<span>Admin</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="/dashboard/createUsers"><i
									class="fa fa-angle-double-right"></i> Create users</a></li>
						</ul></li>

				</c:if>

				<li class="treeview"><a href="#"> <i class="fa fa-laptop"></i>
						<span>Entry master</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">


						<li><a href="/dashboard/products"><i
								class="fa fa-angle-double-right"></i> Products</a></li>

						<c:if
							test="${HTTP_SESSION_USER.role=='Checker'|| HTTP_SESSION_USER.role=='Maker' || HTTP_SESSION_USER.role=='Admin'}">
							<li><a href="/dashboard/suppliers"><i
									class="fa fa-angle-double-right"></i> Suppliers</a></li>
						</c:if>
						<li><a href="/dashboard/customers"><i
								class="fa fa-angle-double-right"></i> Customers</a></li>
						<c:if
							test="${HTTP_SESSION_USER.role=='Checker'|| HTTP_SESSION_USER.role=='Maker' || HTTP_SESSION_USER.role=='Admin'}">
							<!--  <li><a href="#"><i class="fa fa-angle-double-right"></i> Retailers</a></li> -->
						</c:if>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-edit"></i>
						<span>Transaction master</span> <i
						class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="/dashboard/salesinvoice"><i
								class="fa fa-angle-double-right"></i> Sale invoice</a></li>
						<c:if
							test="${HTTP_SESSION_USER.role=='Checker'|| HTTP_SESSION_USER.role=='Maker' || HTTP_SESSION_USER.role=='Admin'}">
							<li><a href="/dashboard/purchaseInvoice"><i
									class="fa fa-angle-double-right"></i> Purchase invoice</a></li>
							<!--  <li><a href="#"><i class="fa fa-angle-double-right"></i> Tax invoice</a></li> -->
						</c:if>
					</ul></li>
				<li class="treeview"><a href="#"> <i class="fa fa-table"></i>
						<span>Report master</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a href="/dashboard/customerReport"><i
								class="fa fa-angle-double-right"></i> Customer report</a></li>
						<c:if
							test="${HTTP_SESSION_USER.role=='Checker'|| HTTP_SESSION_USER.role=='Maker' || HTTP_SESSION_USER.role=='Admin'}">
							<li><a href="/dashboard/supplierReport"><i
									class="fa fa-angle-double-right"></i> Supplier report</a></li>
							<!-- <li><a href="/dashboard/retailerReport"><i class="fa fa-angle-double-right"></i> Retailer report</a></li> -->
						</c:if>
						<li><a href="/dashboard/stockReport"><i
								class="fa fa-angle-double-right"></i> Stock report</a></li>
					</ul></li>

			</ul>
		</section>
	</c:if>
	<!-- /.sidebar -->
</aside>