<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>

<!DOCTYPE>
<html>
<body>
	<%
		String userName = String.valueOf(request.getAttribute("nickname"));
	%>
	<h2>List of entries for user ${requestScope.nickname}</h2>
	<c:forEach var="struc" items="${requestScope.entries}">
		<p>
			<a
				href="<%=request.getContextPath()%>/servleti/author/<%=userName%>/${struc.getId()}">${struc.getTitle()}</a>
		</p>
	</c:forEach>
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