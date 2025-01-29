CREATE TABLE Island (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE Hotel (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       price_per_night INT NOT NULL,
                       latitude REAL NOT NULL,
                       longitude REAL NOT NULL,
                       beach VARCHAR(255),
                       rating REAL,
                       address VARCHAR(255) NOT NULL,
                       island_id INT,
                       FOREIGN KEY (island_id) REFERENCES Island(id) ON DELETE SET NULL
);

CREATE TABLE Site (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      type VARCHAR(255) NOT NULL,
                      price INT NOT NULL,
                      latitude REAL NOT NULL,
                      longitude REAL NOT NULL,
                      intensity INT,
                      address VARCHAR(255) NOT NULL,
                      rating INT,
                      island_id INT,
                      FOREIGN KEY (island_id) REFERENCES Island(id) ON DELETE SET NULL
);


WITH inserted_island AS (
    INSERT INTO Island (name) VALUES ('Tahiti') RETURNING id
)

INSERT INTO Site (name, type, price, latitude, longitude, intensity, address, rating, island_id)
SELECT 'Les 3 Cascades de Faarumai', 'Natural Waterfall', 50, -17.5391, -149.4307, 5, 'Tiarei, Tahiti', 5, id FROM inserted_island
UNION ALL
SELECT 'Rainbow Park', 'Animal Park & Adventure', 1500, -17.5466, -149.5517, 2, 'Pirae, Tahiti', 4, id FROM inserted_island
UNION ALL
SELECT 'Algotherm Spa', 'Spa', 5000, -17.5586, -149.6137, 3, 'InterContinental Tahiti Resort & Spa', 5, id FROM inserted_island
UNION ALL
SELECT "L'Aquarium aux épaves", 'Diving Site', 5000, -17.5595, -149.6062, 3, "Faa'a, Tahiti", 5, id FROM inserted_island
UNION ALL
SELECT 'Atioropa’a', 'Public Garden', 50, -17.5312, -149.4776, 1, 'Location not provided', 3, id FROM inserted_island;
