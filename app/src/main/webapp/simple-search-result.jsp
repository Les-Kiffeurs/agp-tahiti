<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 28/01/2025
  Time: 06:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<f:view>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple Search Results</title>
    <link rel="stylesheet" href="resources/css/simple-search.css">
  </head>
  <body>
  <a href="index.jsp" class="return-home">Back to Main Menu</a>

  <div class="container">
    <header>
      <h1>Simple Search Results</h1>
      <p>You searched for: <strong><%= request.getParameter("keyword") %></strong></p>
    </header>

    <main>
      <section class="search-results">
        <h2>Results</h2>

        <c:choose>
          <c:when test="${empty simpleBean.searchResults}">
            <p>No results found for your query.</p>
          </c:when>
          <c:otherwise>
            <table border="1">
              <thead>
              <tr>
                <th>Name</th>
                <th>Type</th>
                <th>Description</th>

              </tr>
              </thead>
              <tbody>
              <c:forEach items="${simpleBean.searchResults}" var="result">
                <tr>
                  <td>${result.name}</td>
                  <td>${result.type}</td>
                  <td>${result.description}</td>

                </tr>
              </c:forEach>
              </tbody>
            </table>
          </c:otherwise>
        </c:choose>
      </section>
    </main>

    <footer>
      <p>&copy; 2025 Tahiti Trip Planner. All Rights Reserved.</p>
    </footer>
  </div>
  </body>
  </html>
</f:view>