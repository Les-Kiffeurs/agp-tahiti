<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Search Results</title>
</head>
<body>
<h1>Search Results</h1>

<p>
    <strong>Results:</strong>
    <c:forEach items="${sites}" var="site">
        <a href="/sites?id=${site.id}">${site.name}</a>
    </c:forEach>
    <br>
</p>
<a href="/">Back to Main Menu</a>
</body>
</html>
