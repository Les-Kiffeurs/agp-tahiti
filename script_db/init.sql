CREATE TABLE Island (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL
);

CREATE TABLE Hotel (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       pricePerNight INT NOT NULL,
                       latitude FLOAT NOT NULL,
                       longitude FLOAT NOT NULL,
                       beach VARCHAR(255),
                       rating FLOAT,
                       address VARCHAR(255) NOT NULL,
                       islandId INT,
                       FOREIGN KEY (islandId) REFERENCES Island(id)
);

CREATE TABLE Site (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      type VARCHAR(255) NOT NULL,
                      price INT NOT NULL,
                      latitude FLOAT NOT NULL,
                      longitude FLOAT NOT NULL,
                      intensity INT,
                      address VARCHAR(255) NOT NULL,
                      rating INT,
                      islandId INT,
                      FOREIGN KEY (islandId) REFERENCES Island(id)
);


INSERT INTO Site (name, type, price, latitude, longitude, intensity, address, rating, islandId) VALUES
                                                                                                    ('Les 3 Cascades de Faarumai', 'Natural Waterfall', 50, -17.5391, -149.4307, 5, 'Tiarei, Tahiti', 5, ISLAND_ID_VALUE),
                                                                                                    ('Rainbow Park', 'Animal Park & Adventure', 1500, -17.5466, -149.5517, 2, 'Pirae, Tahiti', 4, ISLAND_ID_VALUE),
                                                                                                    ('Algotherm Spa', 'Spa', 5000, -17.5586, -149.6137, 3, 'InterContinental Tahiti Resort & Spa', 5, ISLAND_ID_VALUE),
                                                                                                    ('L\'Aquarium aux épaves', 'Diving Site', 5000, -17.5595, -149.6062, 3, 'Faa\'a, Tahiti', 5, ISLAND_ID_VALUE),
                                                                                                    ('Atioropa’a', 'Public Garden', 50, -17.5312, -149.4776, 1, 'Location not provided', 3, ISLAND_ID_VALUE);
