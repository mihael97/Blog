<%@page import="hr.fer.zemris.java.tecaj_13.util.Constants"%>
<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>
<!DOCTYPE>
<html>
<body>

	<a href="<%=request.getContextPath()%>/servleti/main">Homepage</a>
	<br>

	<%
		if (request.getSession().getAttribute(Constants.NICK) != null) {
	%>
	<p>
		Hello<br>
		<%=request.getSession().getAttribute(Constants.NICK)%><a
			href="<%=request.getContextPath()%>/servleti/logout">Log out</a>
	</p>
	<%
		} else {
	%>
	<p>There is no loged user</p>
	<%
		}
	%>

	<h2>New entry</h2>
	<form action="<%=request.getContextPath()%>/servleti/newentry">
		Title:<input type="text" name="title"><br>
		<textarea rows="10" cols="50" name="text">Enter your entry text</textarea>
		<input type="submit" value="Post comment">
	</form>
</body>
</html>