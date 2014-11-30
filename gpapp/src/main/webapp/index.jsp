<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.gp.users.UserDTO" %>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">





<title>Golden Pages</title>
<jsp:include page="includes/head.jsp" flush="true" />
</head>
<body>

	<jsp:include page="includes/navbar.jsp" flush="true" />
	<div class="container">
		<c:choose>
			<c:when test="${user==null}">
				<div class="row">
					<div class="col-md-6">
						<div id="message_box" class="alert" role="alert">
						</div>
					</div>
					<div class="col-md-6">
					<jsp:include page="includes/register.jsp" flush="true" />
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<%-- logged in --%>
				<c:choose>
					<c:when test="${user.type == UserDTO.Type.BIZ_USER}">
						<script type="text/javascript"> alert("hey biz") </script>
						<jsp:include page="includes/panel-biz.jsp" flush="true" />
					</c:when>
					<c:otherwise>
						<jsp:include page="includes/panel-cons.jsp" flush="true" />
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</div>







	<jsp:include page="includes/footer.jsp" flush="true" />
	<jsp:include page="includes/scripts.jsp" flush="true" />


</body>
</html>