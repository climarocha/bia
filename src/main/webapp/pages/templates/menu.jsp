<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Menu</title>
</head>
<body>

<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" href="#">BIA</a>
				<div class="navbar-content">
					<ul class="nav  pull-left">
						<li class="active"><a href="${pageContext.request.contextPath}/pages/livro/livro.jsp">Livro</a></li>
						<li><a href="${pageContext.request.contextPath}/pages/emprestimo/emprestimo.jsp">Empréstimo</a></li>
						<li><a href="${pageContext.request.contextPath}/pages/devolucao/devolucao.jsp">Devolução</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>