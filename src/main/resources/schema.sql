-- Crear la base de datos (si no existe)
CREATE DATABASE IF NOT EXISTS productdb;

-- Usar la base de datos
USE productdb;

CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS brands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS suppliers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    contact_name VARCHAR(255) NOT NULL,
    contact_email VARCHAR(255) NOT NULL,
    contact_phone VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    category_id BIGINT NOT NULL,
    brand_id BIGINT NOT NULL,
    supplier_id BIGINT NOT NULL,
    CONSTRAINT chk_price CHECK (price >= 0),
    CONSTRAINT chk_quantity CHECK (quantity >= 0),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT,
    CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE RESTRICT,
    CONSTRAINT fk_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE RESTRICT,
    CONSTRAINT unique_product UNIQUE (name, brand_id, category_id)
);


-- Insertar categorías
INSERT INTO categories (name, description) VALUES
('Electrónica', 'Dispositivos electrónicos y gadgets'),
('Ropa', 'Prendas de vestir para todas las edades'),
('Alimentos', 'Productos comestibles y bebidas'),
('Hogar', 'Productos para el hogar y la cocina'),
('Deportes', 'Equipamiento y ropa deportiva');

-- Insertar marcas
INSERT INTO brands (name, description) VALUES
('Samsung', 'Marca líder en tecnología'),
('Nike', 'Marca reconocida en ropa y calzado deportivo'),
('Adidas', 'Marca de ropa y accesorios deportivos'),
('LG', 'Fabricante de electrodomésticos y tecnología'),
('Nestlé', 'Empresa multinacional de alimentos y bebidas');

-- Insertar proveedores
INSERT INTO suppliers (name, contact_name, contact_email, contact_phone) VALUES
('Tech Distribuidores', 'Carlos Pérez', 'carlos@techdistrib.com', '3012345678'),
('Moda S.A.', 'Ana Gómez', 'ana@modasa.com', '3112345678'),
('Food Corp', 'Pedro Ramírez', 'pedro@foodcorp.com', '3212345678'),
('Hogar Express', 'Laura Torres', 'laura@hogarexpress.com', '3312345678'),
('Deporte Global', 'Javier Mendoza', 'javier@deporteglobal.com', '3412345678');

-- Insertar productos
INSERT INTO products (name, description, price, quantity, category_id, brand_id, supplier_id) VALUES
('Smartphone Galaxy S21', 'Teléfono Samsung con pantalla AMOLED y 128GB de almacenamiento', 850.00, 15, 1, 1, 1),
('Zapatillas Air Max', 'Calzado deportivo de alto rendimiento', 120.00, 50, 2, 2, 2),
('Leche en polvo Nestlé', 'Leche en polvo fortificada de 800g', 10.50, 100, 3, 5, 3),
('Aspiradora LG Turbo', 'Aspiradora con filtro HEPA y succión potente', 250.00, 10, 4, 4, 4),
('Balón de fútbol Adidas', 'Balón profesional aprobado por FIFA', 40.00, 30, 5, 3, 5);


