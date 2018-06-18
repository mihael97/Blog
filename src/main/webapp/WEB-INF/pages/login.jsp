<%@page import="hr.fer.zemris.java.tecaj_13.util.Constants"%>
<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>
<!DOCTYPE>
<html>
<body bgcolor="cyan">
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

	<h2>
		You have been successfully registered! Click to <a
			href="<%=request.getContextPath()%>/servleti/main">link</a> for
		continue
	</h2>
</body>
</html>