<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Menu - Tahiti Trip Planner</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
</head>
<body>
<div class="container">
    <header>
        <h1>Tahiti Trip Planner</h1>
        <p>Plan your dream trip with ease!</p>
    </header>

    <main>
        <div class="menu">
            <a href="advanced-search" class="menu-item">
                <h2>Travel Planner</h2>
                <p>Customize your trip by choosing hotels, comfort levels, and price range.</p>
            </a>

            <div class="menu-horizontal">
                <a href="search-hotels" class="menu-item">
                    <h2>Search Hotels</h2>
                    <p>Find hotels by price range and comfort.</p>
                </a>
                <a href="search-activities" class="menu-item">
                    <h2>Search Activities</h2>
                    <p>Find activities by keyword.</p>
                </a>
            </div>
        </div>
    </main>

    <footer>
        <p>&copy; 2025 Tahiti Trip Planner. All Rights Reserved.</p>
    </footer>
</div>
</body>
</html>
