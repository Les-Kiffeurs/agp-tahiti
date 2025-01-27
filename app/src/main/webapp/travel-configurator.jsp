<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 27/01/2025
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Travel Configurator</title>
    <link rel="stylesheet" href="resources/css/travel-config.css">
</head>
<body>
<a href="index.jsp" class="return-home">Back to Main Menu</a>
<div class="container">
    <header>
        <h1>Travel Configurator</h1>
        <p>Customize your trip by entering the details below.</p>
    </header>

    <main>
        <form action="#" method="post" class="config-form">
            <label for="keywords">Keywords (e.g., beach, luxury):</label>
            <input type="text" id="keywords" name="keywords" placeholder="Enter keywords separated by commas">

            <label for="number-of-hotels">Number of Hotels:</label>
            <input type="number" id="number-of-hotels" name="number_of_hotels" min="1" placeholder="e.g., 3">

            <label for="comfort-level">Comfort Level:</label>
            <input type="number" id="comfort-level" name="comfort_level" min="1" max="5" placeholder="1 to 5">

            <label for="min-price">Minimum Price (per night):</label>
            <input type="number" id="min-price" name="min_price" min="0" placeholder="e.g., 100">

            <label for="max-price">Maximum Price (per night):</label>
            <input type="number" id="max-price" name="max_price" min="0" placeholder="e.g., 500">

            <button type="submit">Submit</button>
        </form>
    </main>

    <footer>
        <p>&copy; 2025 Tahiti Trip Configurator. All Rights Reserved.</p>
    </footer>
</div>
</body>
</html>