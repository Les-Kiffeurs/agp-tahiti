<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 27/01/2025
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="entriesBean" class="fr.cyu.depinfo.agp.tahiti.beans.EntriesBeans" scope="session" />
<jsp:setProperty name="entriesBean" property="*" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple Search</title>
    <link rel="stylesheet" href="resources/css/simple-search.css">
    <link rel="icon" type="image/x-icon" href="resources/img/favicon.ico">
</head>
<body>
<a href="index.jsp" class="return-home">Back to Main Menu</a>

<div class="container">
    <header>
        <h1>Simple Search</h1>
        <p>Find hotels or activities by typing a keyword, price range, or comfort level.</p>
    </header>

    <main>
        <form action="simple-search.jsp" method="get" class="search-form">
            <label for="search-keyword">Keyword:</label>
            <input type="text" id="search-keyword" name="keyword" placeholder="Enter a keyword (e.g., beach, spa, hiking)">

            <label for="search-price">Price Range (e.g., 100-500):</label>
            <input type="text" id="search-price" name="price" placeholder="Enter a price range">

            <label for="search-comfort">Comfort Level (1-5):</label>
            <input type="number" id="search-comfort" name="comfort" min="1" max="5" placeholder="1 to 5">

            <button type="submit">Search</button>
        </form>

        <section class="search-results">
            <h2>Results</h2>
            <%
                String keyword = request.getParameter("keyword");
                String  priceRange = request.getParameter("price");
                String comfortLevel = request.getParameter("comfort");

                if (keyword != null && priceRange != null && comfortLevel != null) {
            %>
            <p>
                <strong>Keyword:</strong> <%= !keyword.isEmpty() ? keyword : "None" %><br>
                <strong>Price Range:</strong> <%= !priceRange.isEmpty() ? priceRange : "None" %><br>
                <strong>Comfort Level:</strong> <%= !comfortLevel.isEmpty() ? comfortLevel : "None" %>
            </p>
            <%
            } else {
            %>
            <p>Your search results will appear here.</p>
            <%
                }
            %>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 Tahiti Trip Configurator. All Rights Reserved.</p>
    </footer>
</div>
</body>
</html>