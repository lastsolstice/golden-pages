

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-default " role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Golden Pages, LLC</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<!--
              <li class="active"><a href="#">Link</a></li>
              <li><a href="#">Link</a></li> 
            -->
			</ul>
			<form class="navbar-form navbar-left" role="search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">Go!</button>
					</span>
				</div>
				<!-- /input-group -->
			</form>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${user!=null}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><i class="fa fa-flag"></i></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">1 notification</a></li>
								<li><a href="#">2 notification</a></li>
								<li><a href="#">3 notification</a></li>
							</ul></li>

						<li class="dropdown">
							<!-- If the user is logged in only --> <a href="#"
							class="dropdown-toggle" data-toggle="dropdown">Settings <span
								class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
							</ul>
						</li>
						 <li><a href="${pageContext.request.contextPath}/MainController?action=LOGOUT">Logout</a></li>
					</c:when>
					<c:otherwise>
						<li class="dropdown" id="menuLogin">
						    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Login <span class="caret"></span></a>
							<div class="dropdown-menu" style="padding:17px;">
								<form class="form" id="formLogin" method="POST" action="MainController">
									<input hidden=true name="action" value="LOGIN" >
									<div class="form-group">
										<label for="log_username">Username</label> <input
											type="text" class="form-control" name="log_username" id="log_username"
											placeholder="Username">
									</div>
									<div class="form-group">
										<label for="log_password">Password</label> <input
											type="password" class="form-control"
											id="log_password" name="log_password" placeholder="Password">
									</div>
									<button type="submit" class="btn btn-default">Log In</button>
								</form>
							</div></li>






						<!-- If the user is logged out only -->
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>