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
	<h2>You tried do access informations which cannot be showed to
		current registered user!</h2>
	<a href="<%=request.getContextPath()%>/servleti/index.jsp">Home
		page</a>
</body>
</html>