<%@page import="hr.fer.zemris.java.tecaj_13.util.Constants"%>
<%@page import="hr.fer.zemris.java.tecaj_13.dao.DAOProvider"%>
<%@page import="hr.fer.zemris.java.tecaj_13.model.BlogEntry"%>
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
		BlogEntry entry = DAOProvider.getDAO()
				.getBlogEntry(Long.parseLong(String.valueOf(request.getAttribute("pollID"))));
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

	<h2>Edit entry</h2>
	<form action="<%=request.getContextPath()%>/servleti/edit"
		method="post">
		<input type="hidden" name="entryID" value=<%=entry.getId()%>>
		Title:<input type="text" name="title" value="<%=entry.getTitle()%>"><br>
		Text
		<textarea rows="10" cols="50" name="text"><%=entry.getText()%></textarea>
		<input type="submit" value="Edit entry">
	</form>
</body>
</html>