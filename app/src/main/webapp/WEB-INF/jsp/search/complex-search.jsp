<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="entriesBean" class="fr.cyu.depinfo.agp.tahiti.beans.EntriesBeans" scope="session" />
<jsp:setProperty name="entriesBean" property="*" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Travel Configurator</title>
    <link rel="stylesheet" href="../../../resources/css/travel-config.css">
    <link rel="icon" type="image/x-icon" href="../../../resources/img/favicon.ico">
</head>
<body>
<a href="../index.jsp" class="return-home">Back to Main Menu</a>
<div class="container">
    <header>
        <h1>Travel Planner</h1>
        <p>Customize your trip by entering the details below.</p>
    </header>

    <main>
        <!-- Notice we're using 'number_of_hotels' and 'comfort_level' etc. to match the request.getParameter(...) below -->
        <form action="/offer-detail" method="get" class="search-form">
            <label for="keywords">Keywords (e.g., beach, luxury):</label>
            <input type="text" id="keywords" name="keywords" placeholder="Enter keywords separated by commas" required>

            <label for="number-of-hotels">Number of Hotels:</label>
            <input type="number" id="number-of-hotels" name="number_of_hotels" min="1" placeholder="e.g., 3" required>

            <label for="comfort-level">Comfort Level (1-5):</label>
            <input type="number" id="comfort-level" name="comfort_level" min="1" max="5" placeholder="1 to 5" required>

            <label for="min-price">Minimum Price (per night):</label>
            <input type="number" id="min-price" name="min_price" min="0" placeholder="e.g., 100" required>

            <label for="max-price">Maximum Price (per night):</label>
            <input type="number" id="max-price" name="max_price" min="0" placeholder="e.g., 500" required>

            <label for="arrival-date">Arrival Date:</label>
            <input type="date" id="arrival-date" name="arrival_date" required>

            <label for="departure-date">Departure Date:</label>
            <input type="date" id="departure-date" name="departure_date" required>

            <button type="submit">Submit</button>
        </form>

        <section class="search-results">
            <h2>Results</h2>
            <%

            %>
        </section>
    </main>

    <footer>
        <p>&copy; 2025 Tahiti Trip Planner. All Rights Reserved.</p>
    </footer>
</div>
</body>
</html>
