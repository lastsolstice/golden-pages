<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${user!=null}">
	<%-- logged in users should not be able to recover password--%>
	<c:redirect url="../" />
</c:if>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">





<title>Golden Pages</title>
<jsp:include page="../includes/head.jsp" flush="true" />
</head>
<body>

	<jsp:include page="../includes/navbar.jsp" flush="true" />
	<div class="container">

		<div class="row">
			<div class="col-md-6">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/MainController" method="POST">
					<div class="form-group">
						<h4 style="margin-top:60px;">Please enter the email used for registration</h4>
						<input type="text" name="action" hidden=true value="RECOVER_PASSWORD">
						<label for="recover-email" class="col-sm-2 control-label">Email:</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" name="recover-email" id="recover-email"
								placeholder="Email">
						</div>
						<button id="" type="submit" class="btn btn-success">Submit</button>
					</div>
				</form>


			</div>
			<div class="col-md-6">
				<div id="message_box" class="alert" role="alert"></div>
			</div>

		</div>
	</div>







	<jsp:include page="../includes/footer.jsp" flush="true" />
	<jsp:include page="../includes/scripts.jsp" flush="true" />


</body>
</html>