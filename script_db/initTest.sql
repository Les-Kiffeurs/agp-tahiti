-- Script SQL pour initialiser la base de données PostgreSQL

-- Étape 1 : Création d'une table pour les tests
CREATE TABLE test_table (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            age INT NOT NULL,
                            email VARCHAR(150),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Étape 2 : Création de la table Hotel
CREATE TABLE hotel (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       pricePerNight INT NOT NULL,
                       latitude NUMERIC(9, 6) NOT NULL,
                       longitude NUMERIC(9, 6) NOT NULL,
                       beach VARCHAR(255),
                       rating REAL,
                       address VARCHAR(255) NOT NULL,
                       islandId INT
);

-- Étape 3 : Insertion de 5 lignes de données dans test_table
INSERT INTO test_table (name, age, email) VALUES
                                              ('Alice', 25, 'alice@example.com'),
                                              ('Bob', 30, 'bob@example.com'),
                                              ('Charlie', 22, 'charlie@example.com'),
                                              ('Diana', 28, 'diana@example.com'),
                                              ('Eve', 35, 'eve@example.com');

-- Étape 4 : Insertion de données dans la table Hotel
INSERT INTO hotel (name, pricePerNight, latitude, longitude, beach, rating, address, islandId) VALUES
                                                                                                   ('Blue Lagoon Hotel', 120, 25.77427, -80.19366, 'Sunny Beach', 4.5, '123 Ocean Drive, Miami', NULL),
                                                                                                   ('Sunset Paradise', 150, 36.77830, -119.41790, 'Golden Sands', 4.7, '45 Paradise Road, California', NULL),
                                                                                                   ('Island Retreat', 180, 21.16190, -86.85150, 'Palm Cove', 4.8, '78 Beachfront Blvd, Cancun', NULL),
                                                                                                   ('Ocean Breeze', 220, -33.86880, 151.20930, 'Bondi Beach', 4.6, '12 Harbour St, Sydney', NULL),
                                                                                                   ('Coral Reef Hotel', 200, 13.44430, 144.79370, 'Crystal Beach', 4.2, '89 Coral Lane, Guam', NULL),
                                                                                                   ('Tropical Haven', 95, -17.71340, 178.06500, 'Lagoon View', 4.3, '67 Lagoon Rd, Fiji', NULL),
                                                                                                   ('Seaside Escape', 145, 9.74890, -83.75340, 'Sunset Bay', 4.0, '34 Seaview Ave, Costa Rica', NULL),
                                                                                                   ('Palm Paradise Inn', 175, 20.42300, -86.92230, 'Hidden Beach', 4.1, '10 Palm Drive, Cozumel', NULL),
                                                                                                   ('Azure Sands', 210, 35.68950, 139.69170, 'White Shore', 4.9, '22 Azure St, Tokyo', NULL),
                                                                                                   ('Golden Horizon', 185, 55.75580, 37.61730, 'Golden Bay', 4.4, '77 Horizon Lane, Moscow', NULL),
                                                                                                   ('Lagoon View Lodge', 130, 18.22080, -66.59010, 'Lagoon Bay', 4.2, '11 Lagoon Rd, Puerto Rico', NULL),
                                                                                                   ('Serenity Resort', 200, -22.90680, -43.17290, 'Rio Beach', 4.5, '99 Serenity Ave, Rio de Janeiro', NULL),
                                                                                                   ('Wave Crest Hotel', 150, -34.60370, -58.38160, 'Wave Beach', 4.1, '5 Crest Lane, Buenos Aires', NULL),
                                                                                                   ('Coastal Bliss', 140, 14.59950, 120.98420, 'Pearl Beach', 4.0, '16 Bliss St, Manila', NULL),
                                                                                                   ('Beachfront Retreat', 190, 1.35210, 103.81980, 'Silky Sands', 4.6, '56 Beachfront Rd, Singapore', NULL),
                                                                                                   ('Sapphire Shores', 250, 51.50740, -0.12780, 'Sapphire Bay', 4.9, '21 Sapphire St, London', NULL),
                                                                                                   ('Hidden Pearl Inn', 115, 48.85660, 2.35220, 'Pearl Cove', 4.0, '44 Pearl Rd, Paris', NULL),
                                                                                                   ('Tidewater Resort', 170, 40.71280, -74.00600, 'Rocky Beach', 4.2, '88 Tidewater Rd, New York', NULL),
                                                                                                   ('Crystal Waters Hotel', 230, 37.77490, -122.41940, 'Crystal Bay', 4.8, '99 Crystal Rd, San Francisco', NULL),
                                                                                                   ('Paradise Cove', 135, 41.90280, 12.49640, 'Paradise Bay', 4.1, '33 Cove St, Rome', NULL);
