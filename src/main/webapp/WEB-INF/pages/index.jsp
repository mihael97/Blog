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
		Nick: <input type="text" name="nick" value="${requestScope.nickname}"><br>
		<%
			if (request.getAttribute("loginError") != null) {
				out.println(String.valueOf(request.getAttribute("loginError")));
			}
		%>
		Password: <input type="password" name="password"><br> <input
			type="submit" value="Log in">
	</form>
	<h2>
		New here? If you want to part of our community,click <a
			href="<%=request.getContextPath()%>\WEB-INF\pages\register.jsp">here</a>
		for registration!
	</h2>
</body>
</html>