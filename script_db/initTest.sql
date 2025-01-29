-- Étape 1 : Création d'une table pour les tests
CREATE TABLE test_table (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            age INT NOT NULL,
                            email VARCHAR(150),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Étape 2 : Création du type ENUM pour le classement
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'rank_enum') THEN
CREATE TYPE rank_enum AS ENUM ('LOW', 'MEDIUM', 'HIGH');
END IF;
END $$;

-- Étape 3 : Création de la table Hotel
CREATE TABLE hotel (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       price_per_night INT NOT NULL,
                       latitude DOUBLE PRECISION NOT NULL,
                       longitude DOUBLE PRECISION NOT NULL,
                       beach VARCHAR(255),
                       rating REAL CHECK (rating >= 0 AND rating <= 5),
                       address VARCHAR(255) NOT NULL,
                       island_id INT,
                       rank rank_enum DEFAULT 'MEDIUM'
);

-- Création de la table Activity
CREATE TABLE activity (
                          id INT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);


-- Étape 4 : Insertion de données dans la table Activity
INSERT INTO activity (id, name) VALUES
                                    (1, 'SuperEscalade'),
                                    (2, 'SuperPlongee'),
                                    (3, 'SuperEscalade2');


-- Étape 5 : Insertion de données dans test_table
INSERT INTO test_table (name, age, email) VALUES
                                              ('Alice', 25, 'alice@example.com'),
                                              ('Bob', 30, 'bob@example.com'),
                                              ('Charlie', 22, 'charlie@example.com'),
                                              ('Diana', 28, 'diana@example.com'),
                                              ('Eve', 35, 'eve@example.com')
    ON CONFLICT DO NOTHING;

-- Étape 6 : Insertion de données dans la table Hotel
INSERT INTO hotel (name, price_per_night, latitude, longitude, beach, rating, address, island_id, rank) VALUES
                                                                                                            ('Blue Lagoon Hotel', 120, 25.77427, -80.19366, 'Sunny Beach', 4.5, '123 Ocean Drive, Miami', 1, 'HIGH'),
                                                                                                            ('Sunset Paradise', 150, 36.77830, -119.41790, 'Golden Sands', 4.7, '45 Paradise Road, California', 1, 'HIGH'),
                                                                                                            ('Island Retreat', 180, 21.16190, -86.85150, 'Palm Cove', 4.8, '78 Beachfront Blvd, Cancun', 1, 'MEDIUM'),
                                                                                                            ('Ocean Breeze', 220, -33.86880, 151.20930, 'Bondi Beach', 4.6, '12 Harbour St, Sydney', 1, 'HIGH'),
                                                                                                            ('Coral Reef Hotel', 200, 13.44430, 144.79370, 'Crystal Beach', 4.2, '89 Coral Lane, Guam', 1, 'MEDIUM'),
                                                                                                            ('Tropical Haven', 95, -17.71340, 178.06500, 'Lagoon View', 4.3, '67 Lagoon Rd, Fiji', 1, 'LOW'),
                                                                                                            ('Seaside Escape', 145, 9.74890, -83.75340, 'Sunset Bay', 4.0, '34 Seaview Ave, Costa Rica', 1, 'LOW'),
                                                                                                            ('Palm Paradise Inn', 175, 20.42300, -86.92230, 'Hidden Beach', 4.1, '10 Palm Drive, Cozumel', 1, 'MEDIUM'),
                                                                                                            ('Azure Sands', 210, 35.68950, 139.69170, 'White Shore', 4.9, '22 Azure St, Tokyo', 1, 'HIGH'),
                                                                                                            ('Golden Horizon', 185, 55.75580, 37.61730, 'Golden Bay', 4.4, '77 Horizon Lane, Moscow', 1, 'MEDIUM'),
                                                                                                            ('Lagoon View Lodge', 130, 18.22080, -66.59010, 'Lagoon Bay', 4.2, '11 Lagoon Rd, Puerto Rico', 1, 'MEDIUM'),
                                                                                                            ('Serenity Resort', 200, -22.90680, -43.17290, 'Rio Beach', 4.5, '99 Serenity Ave, Rio de Janeiro', 1, 'HIGH'),
                                                                                                            ('Wave Crest Hotel', 150, -34.60370, -58.38160, 'Wave Beach', 4.1, '5 Crest Lane, Buenos Aires', 1, 'LOW'),
                                                                                                            ('Coastal Bliss', 140, 14.59950, 120.98420, 'Pearl Beach', 4.0, '16 Bliss St, Manila', 1, 'LOW'),
                                                                                                            ('Beachfront Retreat', 190, 1.35210, 103.81980, 'Silky Sands', 4.6, '56 Beachfront Rd, Singapore', 1, 'HIGH'),
                                                                                                            ('Sapphire Shores', 250, 51.50740, -0.12780, 'Sapphire Bay', 4.9, '21 Sapphire St, London', 1, 'HIGH'),
                                                                                                            ('Hidden Pearl Inn', 115, 48.85660, 2.35220, 'Pearl Cove', 4.0, '44 Pearl Rd, Paris', 1, 'LOW'),
                                                                                                            ('Tidewater Resort', 170, 40.71280, -74.00600, 'Rocky Beach', 4.2, '88 Tidewater Rd, New York', 1, 'MEDIUM'),
                                                                                                            ('Crystal Waters Hotel', 230, 37.77490, -122.41940, 'Crystal Bay', 4.8, '99 Crystal Rd, San Francisco', 1, 'HIGH'),
                                                                                                            ('Paradise Cove', 135, 41.90280, 12.49640, 'Paradise Bay', 4.1, '33 Cove St, Rome', 1, 'MEDIUM')
    ON CONFLICT DO NOTHING;
