<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>spring-mvc-showcase</title>
	<link href="<c:url value="/resources/form.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.core.css" />" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.theme.css" />" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/resources/jqueryui/1.8/themes/base/jquery.ui.tabs.css" />" rel="stylesheet" type="text/css"/>

	<!--
		Used for including CSRF token in JSON requests
		Also see bottom of this file for adding CSRF token to JQuery AJAX requests
	-->
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<h1><a href="<c:url value="/" />">User list</a></h1>
<p/>
<c:forEach var="model" items="${modelList}">
	<c:set var="modelDisplay">
		<c:out value="${model.id}"/> <c:out value="${model.name}"/>  @ <c:out value="${model.add}"/>
	</c:set>
	<p>
		<c:url value="detail" var="detailUrl">
			<c:param name="name" value="${model.name}"></c:param>
			<c:param name="add" value="${model.add}"></c:param>
			<c:param name="id" value="${model.id}"></c:param>
		</c:url>
		<a href="${detailUrl}">${modelDisplay}</a>
	</p>
</c:forEach>
</body>
</html>