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
        <p>Find hotels by price.</p>
    </header>

    <main>
        <form action="simple-search-results" method="get" class="search-form">

            <label for="search-price">Price Range (e.g., 100-500):</label>
            <input type="text" id="search-price" name="price" placeholder="Enter a price range">

            <button type="submit">Search</button>
        </form>
    </main>

    <footer>
        <p>&copy; 2025 Tahiti Trip Configurator. All Rights Reserved.</p>
    </footer>
</div>
</body>
</html>