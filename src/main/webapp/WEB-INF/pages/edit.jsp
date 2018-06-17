<%@page import="hr.fer.zemris.java.tecaj_13.dao.DAOProvider"%>
<%@page import="hr.fer.zemris.java.tecaj_13.model.BlogEntry"%>
<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>
<!DOCTYPE>
<html>
<body>
	<%
		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(Long.parseLong(request.getParameter("entryID")));
	%>
	<h2>Edit entry</h2>
	<form action="<%=request.getContextPath()%>/servleti/edit">
		<input type="hidden" name="entryID"
			value=<%=Long.parseLong(request.getParameter("entryID"))%>>
		Title:<input type="text" name="title" value="<%=entry.getTitle()%>"><br>
		Text
		<textarea rows="10" cols="50" name="text"><%=entry.getText()%></textarea>
		<input type="submit" value="Edit entry">
	</form>
</body>
</html>