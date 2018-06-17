<%@page import="hr.fer.zemris.java.tecaj_13.util.Constants"%>
<%@page import="hr.fer.zemris.java.tecaj_13.model.BlogUser"%>
<%@page import="java.util.List"%>
<%@page import="hr.fer.zemris.java.tecaj_13.dao.DAOProvider"%>
<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>
<!DOCTYPE>
<html>
<body>
	<h1>Main page</h1>
	<h2>Already registered? Please log in from more features</h2>
	<form action="<%=request.getContextPath()%>/servleti/login"
		method="post">
		<%
			if (request.getAttribute("nickname") != null) {
		%>
		Nick: <input type="text" name="nick" value="${requestScope.nickname}"><br>
		<%
			} else {
		%>
		Nick: <input type="text" name="nick"><br>

		<%
			}
		%>
		Password: <input type="password" name="password"><br>
		<%
			if (request.getAttribute("loginError") != null) {
				out.println(String.valueOf(request.getAttribute("loginError")));
			}
		%><input type="submit" value="Log in">
	</form>
	<h2>
		New here? If you want to part of our community,click <a
			href=" <%=request.getContextPath()%>/servleti/redirect">here</a> for
		registration!
	</h2>
	<%
		List<BlogUser> blogUsers = DAOProvider.getDAO().getUsers();
		int listSize = blogUsers.size();
	%>
	<c:choose>
		<c:when test="<%=listSize == 0%>">
			<p>Unfortunately our community doesn't have any registered user.
				Be first!</p>
		</c:when>
		<c:otherwise>
			<h3>Here is list of registered user. Be one of them</h3>
			<br>
			<c:forEach var="struc" items="<%=blogUsers%>">
				<a
					href="<%=request.getContextPath()%>/servleti/author/
					${struc.getNick()}">${struc.getNick()}</a>
				<br>
			</c:forEach>
		</c:otherwise>
	</c:choose>

	<%
		if (request.getSession().getAttribute(Constants.NICK) != null) {
	%>
	<a href="<%=request.getContextPath()%>/servleti/logout">Log out</a>
	<%
		}
	%>

</body>
</html>