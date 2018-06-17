<%@ page contentType="text/html; charset=UTF-8
	" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  page session="true"%>
<!DOCTYPE>
<html>
<body>
	<h2>New entry</h2>
	<form action="<%=request.getContextPath()%>/servleti/newEntry">
		Title:<input type="text" name="title"><br>
		<textarea rows="10" cols="50" name="text">Enter your entry text</textarea>
		<input type="submit" value="Post comment">
	</form>
</body>
</html>