<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de l'offre</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h2 { color: #2c3e50; }
        .offer-details, .excursion { border: 1px solid #ccc; padding: 10px; margin-bottom: 10px; }
    </style>
</head>
<body>
<c:forEach var="offer" items="${offers}">
    <h2>Détails de l'Offre</h2>
    <div class="offer-details">
        <p><strong>Destination :</strong> ${offer.getDestination().getIslandName()}</p>
        <p><strong>Prix total :</strong> <fmt:formatNumber value="${offer.getPrice()}" type="number" pattern="#0.00"/> €</p>
    </div>

    <h3>Excursions :</h3>
    <c:forEach var="excursion" items="${offer.getExcursions().values()}">
        <div class="excursion">
            <p><strong>Date :</strong> ${excursion.getDate()}</p>
            <p><strong>Départ :</strong> ${excursion.getDeparture().getName()}</p>
            <p><strong>Destination :</strong> ${excursion.getDestination().getName()}</p>

            <h4>Trajets : ${excursion.getSites().size()}</h4>
            <ul>
                <c:forEach var="trip" items="${excursion.getSites()}">
                    <li>
                        Prix : <fmt:formatNumber value="${trip.getPrice()}" type="number" pattern="#0.00"/> €<br>
                        Mode de transport : ${trip.getTransportMode()}<br>
                        Départ : ${trip.getDeparture().getName()} → Destination : ${trip.getDestination().getName()}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</c:forEach>
</body>
</html>
