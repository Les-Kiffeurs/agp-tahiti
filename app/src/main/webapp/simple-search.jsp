<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 27/01/2025
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Simple Search</title>
    <link rel="stylesheet" href="resources/css/simple-search.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Simple Search</h1>
        <p>Quickly find hotels or activities by typing a keyword, price range, or comfort level.</p>
    </header>

    <main>
        <form action="#" method="get" class="search-form">
            <label for="search-keyword">Keyword:</label>
            <input type="text" id="search-keyword" name="keyword" placeholder="Enter a keyword (e.g., beach, spa, hiking)">

            <label for="search-price">Price Range (e.g., 100-500):</label>
            <input type="text" id="search-price" name="price" placeholder="Enter a price range">

            <label for="search-comfort">Comfort Level (1-5):</label>
            <input type="number" id="search-comfort" name="comfort" min="1" max="5" placeholder="1 to 5">

            <button type="submit">Search</button>
        </form>

        <div class="search-results">
            <h2>Results</h2>
            <p>Search results will be displayed here.</p>
            <!-- Placeholder for now -->
        </div>
    </main>

    <footer>
        <p>&copy; 2025 Tahiti Trip Configurator. All Rights Reserved.</p>
    </footer>
</div>
</body>
</html>

