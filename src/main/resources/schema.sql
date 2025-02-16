-- schema.sql

-- Crear la base de datos (si no existe)
CREATE DATABASE IF NOT EXISTS productdb;

-- Usar la base de datos
USE productdb;

-- Crear la tabla 'products'
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Identificador único del producto
    name VARCHAR(255) NOT NULL,            -- Nombre del producto
    description TEXT,                      -- Descripción del producto
    price DECIMAL(10, 2) NOT NULL,         -- Precio del producto (hasta 10 dígitos, 2 decimales)
    quantity INT NOT NULL,                 -- Cantidad disponible del producto
    CONSTRAINT chk_price CHECK (price >= 0),  -- Restricción: el precio no puede ser negativo
    CONSTRAINT chk_quantity CHECK (quantity >= 0)  -- Restricción: la cantidad no puede ser negativa
);

-- Insertar datos de prueba (opcional)
INSERT INTO products (name, description, price, quantity) VALUES
('Laptop', 'High-performance laptop', 1200.00, 10),
('Smartphone', 'Latest model smartphone', 800.00, 20),
('Tablet', 'Lightweight and portable tablet', 400.00, 15),
('Headphones', 'Noise-cancelling headphones', 150.00, 30),
('Smartwatch', 'Fitness and health tracking smartwatch', 250.00, 25);
