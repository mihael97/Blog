<%@page import="hr.fer.zemris.java.tecaj_13.util.Constants"%>
<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>

<!DOCTYPE>

<html>
<body bgcolor="cyan">
	<%!private String getError(HttpServletRequest req) {
		Object str = req.getAttribute("error");
		return str == null ? "" : String.valueOf(str);
	}%>

	<a href="<%=request.getContextPath()%>/servleti/main">Homepage</a>
	<br>

	<%
		if (request.getSession().getAttribute(Constants.NICK) != null) {
	%>
	<p>
		Hello
		<%=request.getSession().getAttribute(Constants.FIRST_NAME)%>
		<%=request.getSession().getAttribute(Constants.LAST_NAME)%><br> <a
			href="<%=request.getContextPath()%>/servleti/logout">Log out</a>
	</p>
	<%
		} else {
	%>
	<p>There is no logged user</p>
	<%
		}
	%>

	<h1>New user registration</h1>
	<form action="<%=request.getContextPath()%>/servleti/register"
		method="post">
		First Name: <input type="text" name="firstName"><br> Last
		Name: <input type="text" name="lastName"><br> E-mail: <input
			type="email" name="email"><br> Nick: <input type="text"
			name="nick"><br> Password: <input type="password"
			name="password"><br>
		<%=getError(request)%><br> <input type="submit" value="Register">
	</form>
</body>
</html>