<%@page import="hr.fer.zemris.java.tecaj_13.util.Util"%>
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

	<%
		String userName = String.valueOf(request.getAttribute("nickname"));
	%>
	<h2>List of entries for user ${requestScope.nickname}</h2>
	<table border="2">
		<c:forEach var="struc" items="${requestScope.entries}">
			<tr>
				<td><a
					href="<%=request.getContextPath()%>/servleti/author/<%=userName%>/${struc.getId()}">${struc.getTitle()}</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%
		if (String.valueOf(request.getSession().getAttribute("current.user.nick")).equals(userName)) {
	%>
	<a
		href="<%=request.getContextPath()%>/servleti/author/<%=userName%>/new">Add
		new entry</a>
	<%
		}
	%>
</body>
</html>