<%@page import="hr.fer.zemris.java.tecaj_13.util.Constants"%>
<%@page import="hr.fer.zemris.java.tecaj_13.dao.DAOProvider"%>
<%@page import="hr.fer.zemris.java.tecaj_13.model.BlogEntry"%>
<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>
<!DOCTYPE>
<html>

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

<%
	BlogEntry entry = (BlogEntry) request.getAttribute("entry");
	String nick = String.valueOf(request.getAttribute("nickname"));
%>
<h2>
	Blog entry by user
	<%=nick%></h2>
<h3><%=entry.getTitle()%></h3>
<p><%=entry.getText()%></p>
<%
	String currentUser = String.valueOf(request.getSession().getAttribute("current.user.nick"));

	if (nick.equals(currentUser)) {
%>
<a
	href="<%=request.getContextPath()%>/servleti/author/<%=nick%>/edit/<%=entry.getId()%>">Edit
	entry</a>
<%
	}
%>
<h3>Comments</h3>
<c:forEach var="comment" items="<%=entry.getComments()%>">
	<h4>Comment is posted on ${comment.getPostedOn() } by user
		${comment.getUsersEMail() }</h4>
	<p>${comment.getMessage()}</p>
</c:forEach>
<h3>Add new comment</h3>
<%
	if (String.valueOf(request.getSession().getAttribute("current.user.nick")) != null) {
%>
<form action="<%=request.getContextPath()%>/servleti/comment"
	method="post">
	<input type="hidden" name="entryID" value=<%=entry.getId()%>><br>
	<textarea rows="10" cols="50" name="message">Enter your comment</textarea>
	<input type="submit" value="Post comment">
</form>
<%
	} else {
%>
<h4>
	You should <a
		href="<%=request.getContextPath()%>/WEB-INF/pages/index.jsp">register</a>
	for post comments
</h4>
<%
	}
%>
</html>