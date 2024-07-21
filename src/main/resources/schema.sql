CREATE TABLE capital_cities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    latitude DECIMAL(8, 4) NOT NULL,
    longitude DECIMAL(8, 4) NOT NULL,
    airport_code VARCHAR(10) NOT NULL
);
