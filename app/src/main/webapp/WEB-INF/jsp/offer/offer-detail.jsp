<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de l'itinéraire</title>
    <style>
        :root {
            --background: hsl(0 0% 100%);
            --foreground: hsl(240 10% 3.9%);
            --muted: hsl(240 4.8% 95.9%);
            --muted-foreground: hsl(240 3.8% 46.1%);
            --border: hsl(240 5.9% 90%);
            --card: hsl(0 0% 100%);
            --card-foreground: hsl(240 10% 3.9%);
            --primary: hsl(240 5.9% 10%);
            --primary-foreground: hsl(0 0% 98%);
            --secondary: hsl(240 4.8% 95.9%);
            --secondary-foreground: hsl(240 5.9% 10%);
            --accent: hsl(240 4.8% 95.9%);
            --accent-foreground: hsl(240 5.9% 10%);
            --success: hsl(145.6 100% 39.2%);
            --success-light: hsl(145.6 100% 96.1%);
            --radius: 0.5rem;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: system-ui, -apple-system, sans-serif;
            line-height: 1.5;
            color: var(--foreground);
            background-color: var(--background);
            padding: 1.5rem;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        h2 {
            font-size: 1.5rem;
            font-weight: 600;
            letter-spacing: -0.025em;
            color: var(--foreground);
        }

        .text-sm {
            font-size: 0.875rem;
        }

        .text-muted {
            color: var(--muted-foreground);
        }

        .font-medium {
            font-weight: 500;
        }

        .font-semibold {
            font-weight: 600;
        }

        .offer-card {
            background-color: var(--card);
            border: 1px solid var(--border);
            border-radius: var(--radius);
            padding: 1.5rem;
            margin-bottom: 1.5rem;
        }

        .card-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 2rem;
            padding-bottom: 1.5rem;
            border-bottom: 1px solid var(--border);
        }

        .destination-pill {
            display: inline-flex;
            background-color: var(--success-light);
            color: var(--success);
            padding: 0.375rem 0.75rem;
            border-radius: 9999px;
            font-weight: 500;
            font-size: 0.875rem;
            margin-top: 0.75rem;
        }

        .price {
            font-size: 1.25rem;
            font-weight: 600;
            color: var(--foreground);
        }

        .segment {
            background-color: var(--accent);
            border-radius: var(--radius);
            padding: 1.25rem;
            margin-bottom: 1rem;
        }

        .segment-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1rem;
        }

        .route {
            display: flex;
            align-items: center;
            gap: 0.75rem;
            margin: 1rem 0;
        }

        .route-point {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            min-width: 0;
        }

        .route-separator {
            color: var(--muted-foreground);
            margin: 0 0.25rem;
        }

        .step {
            background-color: var(--card);
            border: 1px solid var(--border);
            border-radius: var(--radius);
            padding: 1rem;
            margin-top: 0.75rem;
        }

        .step-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 0.5rem;
        }

        .badge {
            display: inline-flex;
            align-items: center;
            padding: 0.25rem 0.75rem;
            border-radius: 9999px;
            font-size: 0.75rem;
            font-weight: 500;
            background-color: var(--secondary);
            color: var(--secondary-foreground);
        }

        @media (max-width: 640px) {
            .card-header {
                flex-direction: column;
                gap: 1rem;
            }

            .route {
                flex-direction: column;
                align-items: flex-start;
                gap: 0.5rem;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <c:forEach var="offer" items="${offers}">
        <div class="offer-card">
            <div class="card-header">
                <div>
                    <h2>Détails de l'itinéraire</h2>
                    <div class="destination-pill">
                            ${offer.getDestination().getIslandName()}
                    </div>
                </div>
                <div class="price">
                    <fmt:formatNumber value="${offer.getPrice()}" type="number" pattern="#0.00"/> €
                </div>
            </div>

            <div class="segments">
                <c:forEach var="excursion" items="${offer.getExcursions().values()}">
                    <div class="segment">
                        <div class="segment-header">
                            <div class="text-sm font-medium">
                                    ${excursion.getDate()}
                            </div>
                            <div class="badge">
                                    ${excursion.getSites().size()} trajets
                            </div>
                        </div>

                        <div class="route">
                            <div class="route-point">
                                <span class="font-medium">${excursion.getDeparture().getName()}</span>
                            </div>
                            <span class="route-separator">→</span>
                            <div class="route-point">
                                <span class="font-medium">${excursion.getDestination().getName()}</span>
                            </div>
                        </div>

                        <div class="steps">
                            <c:forEach var="trip" items="${excursion.getSites()}">
                                <div class="step">
                                    <div class="step-header">
                                        <div class="font-medium">
                                            <fmt:formatNumber value="${trip.getPrice()}" type="number" pattern="#0.00"/> €
                                        </div>
                                    </div>
                                    <div class="route">
                                        <div class="route-point text-sm">
                                            <span class="text-muted">Départ</span>
                                            <span class="font-medium">${trip.getDeparture().getName()}</span>
                                        </div>
                                        <span class="route-separator">→</span>
                                        <div class="route-point text-sm">
                                            <span class="text-muted">Arrivée</span>
                                            <span class="font-medium">${trip.getDestination().getName()}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>