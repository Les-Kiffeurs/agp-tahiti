<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <p><strong>Destination :</strong> <%= offer.getDestination().getIslandName() %></p>
        <p><strong>Prix total :</strong> <%= offer.getPrice() %> €</p>
    </div>

    <h3>Excursions :</h3>
    <c:forEach var="entry" items="${offer.excursions}">
        <div class="excursion">
            <p><strong>Date :</strong> ${entry.key}</p>
            <c:set var="excursion" value="${entry.value}" />
            <p><strong>Départ :</strong> ${excursion.departure.name}</p>
            <p><strong>Destination :</strong> ${excursion.destination.name}</p>

            <h4>Trajets :</h4>
            <ul>
                <c:forEach var="trip" items="${excursion.sites}">
                    <li>
                        Prix : ${trip.price} €, Distance : ${trip.distance} km<br>
                        Mode de transport : ${trip.transportMode}<br>
                        Départ : ${trip.departure.name} → Destination : ${trip.destination.name}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</c:forEach>
</body>
</html>
