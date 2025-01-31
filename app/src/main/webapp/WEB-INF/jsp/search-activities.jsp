<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple Search</title>
    <link rel="stylesheet" href="../../resources/css/simple-search.css">
    <link rel="icon" type="image/x-icon" href="../../resources/img/favicon.ico">
</head>
<body>
<a href="/" class="return-home">Back to Main Menu</a>

<div class="container">
    <header>
        <h1>Simple Search</h1>
        <p>Find hotels or activities by typing a keyword, price range, or comfort level.</p>
    </header>

    <main>
        <form action="simple-search-results" method="get" class="search-form">

            <label for="search-keywords">Keyword(s):</label>
            <input type="text" id="search-keywords" name="keywords" placeholder="Enter some keywords">

            <button type="submit">Search</button>
        </form>
    </main>

    <footer>
        <p>&copy; 2025 Tahiti Trip Configurator. All Rights Reserved.</p>
    </footer>
</div>
</body>
</html>