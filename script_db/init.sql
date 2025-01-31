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
                      rating REAL,
                      island_id INT,
                      FOREIGN KEY (island_id) REFERENCES Island(id) ON DELETE SET NULL
);
