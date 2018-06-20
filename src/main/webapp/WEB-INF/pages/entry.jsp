<%@page import="hr.fer.zemris.java.tecaj_13.util.Constants"%>
<%@page import="hr.fer.zemris.java.tecaj_13.dao.DAOProvider"%>
<%@page import="hr.fer.zemris.java.tecaj_13.model.BlogEntry"%>
<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>
<!DOCTYPE>
<html>
<body bgcolor="cyan">
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

	<%
		BlogEntry entry = (BlogEntry) request.getAttribute("entry");
		String nick = String.valueOf(request.getAttribute("nickname"));
	%>
	<h2>
		Blog entry by user
		<%=entry.getCreator().getNick()%>
	</h2>
	<h3><%=entry.getTitle()%></h3>
	<p><%=entry.getText()%></p>
	<p>
		Last modified at :
		<%=entry.getLastModifiedAt()%></p>
	<%
		String currentUser = String.valueOf(request.getSession().getAttribute(Constants.NICK));

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
	<form action="<%=request.getContextPath()%>/servleti/comment"
		method="post">
		<input type="hidden" name="entryID" value=<%=entry.getId()%>><br>
		<%
			if (request.getSession().getAttribute(Constants.NICK) == null) {
		%>
		<h4>You are not logged. Please enter your email address</h4>
		<input type="email" name="email">
		<%
			}
		%>
		<input type="hidden" name="entryID" value=<%=entry.getId()%>><br>
		<textarea rows="10" cols="50" name="message">Enter your comment</textarea>
		<input type="submit" value="Post comment">
	</form>
</body>
</html>