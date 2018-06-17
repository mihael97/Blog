<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>

<!DOCTYPE>

<html>
<body>
	<%!private String getError(HttpServletRequest req, String string) {
		Object str = req.getAttribute(string);
		return str == null ? "" : String.valueOf(str);
	}%>
	<h1>New user registration</h1>
	<form action="<%=request.getContextPath()%>/servleti/register"
		method="post">
		First Name: <input type="text" name="firstName"><br>
		<%=getError(request, "errorFirstName")%>
		Last Name: <input type="text" name="lastName"><br>
		<%=getError(request, "errorLastName")%>
		E-mail: <input type="email" name="email"><br>
		<%=getError(request, "errorEmail")%>
		Nick: <input type="text" name="nick"><br>
		<%=getError(request, "errorNick")%>
		Password: <input type="password" name="password"><br>
		<%=getError(request, "errorPassword")%>
		<input type="submit" value="Register">
	</form>
</body>
</html>