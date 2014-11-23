<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gp.User,java.io.PrintWriter"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 
	<%
		User u = (User) request.getSession().getAttribute("user");
		if (u == null) { //we need to render a default page
			
			PrintWriter pw = response.getWriter();
			pw.println("<a href='link'>login</a>");
			pw.println("<a href='link'>register</a>");
			

		} else if (u.getType() == User.Type.BIZ_USER) { //load biz homepage

		} else { //load user page

		}
	%>
--%>






</body>
</html>