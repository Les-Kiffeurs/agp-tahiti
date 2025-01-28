-- Script SQL pour initialiser la base de données PostgreSQL

-- Étape 1 : Création d'une table pour les tests
CREATE TABLE test_table (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            age INT NOT NULL,
                            email VARCHAR(150),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Étape 2 : Insertion de 5 lignes de données dans la table
INSERT INTO test_table (name, age, email) VALUES
                                              ('Alice', 25, 'alice@example.com'),
                                              ('Bob', 30, 'bob@example.com'),
                                              ('Charlie', 22, 'charlie@example.com'),
                                              ('Diana', 28, 'diana@example.com'),
                                              ('Eve', 35, 'eve@example.com');

-- Étape 3 : Vérification des données insérées
SELECT * FROM test_table;
