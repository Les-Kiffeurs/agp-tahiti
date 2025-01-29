<%@ page import="fr.cyu.depinfo.agp.tahiti.beans.EntriesBeans" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<jsp:useBean id="resultBean"
             class="fr.cyu.depinfo.agp.tahiti.beans.ResultBean"
             scope="session" />
<jsp:setProperty name="resultBean" property="*" />

<%
    resultBean.setResults("test de résultat");

%>

<html>
<head>
    <title>Search Results</title>
</head>
<body>
<h1>Search Results</h1>

<p>
    <strong>Results:</strong> <%= resultBean.getResults() %><br>
</p>

<a href="index.jsp">Back to Main Menu</a>
</body>
</html>
