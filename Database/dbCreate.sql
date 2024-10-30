-------------------------------------------------------
-- Crear lab1bda
-- Database: lab1bda
-------------------------------------------------------
DROP DATABASE IF EXISTS "lab1bda";

CREATE DATABASE "lab1bda"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Chile.1252'
    LC_CTYPE = 'Spanish_Chile.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

\c "lab1bda";  -- Cambia a la base de datos creada

-------------------------------------------------------
-- Table "categoria"
-------------------------------------------------------
CREATE TABLE IF NOT EXISTS "categoria" (
    "id_categoria" SERIAL PRIMARY KEY,
    "nombre" VARCHAR(100) NOT NULL
);

-------------------------------------------------------
-- Table "producto"
-------------------------------------------------------
CREATE TABLE IF NOT EXISTS "producto" (
    "id_producto" SERIAL PRIMARY KEY,
    "nombre" VARCHAR(255) NOT NULL,
    "descripcion" TEXT,
    "precio" DECIMAL(10, 2) NOT NULL,
    "stock" INT NOT NULL,
    "estado" VARCHAR(50) NOT NULL CHECK ("estado" IN ('disponible', 'agotado')),
    "id_categoria" INTEGER,
    CONSTRAINT "fk_categoria"
        FOREIGN KEY ("id_categoria")
        REFERENCES "categoria" ("id_categoria")
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-------------------------------------------------------
-- Table "cliente"
-------------------------------------------------------
CREATE TABLE IF NOT EXISTS "cliente" (
    "id_cliente" SERIAL PRIMARY KEY,
    "nombre" VARCHAR(255) NOT NULL,
    "direccion" VARCHAR(255),
    "email" VARCHAR(100) UNIQUE,
    "telefono" VARCHAR(20)
);

-------------------------------------------------------
-- Table "orden"
-------------------------------------------------------
CREATE TABLE IF NOT EXISTS "orden" (
    "id_orden" SERIAL PRIMARY KEY,
    "fecha_orden" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "estado" VARCHAR(50) NOT NULL CHECK ("estado" IN ('pendiente', 'pagada', 'enviada')),
    "id_cliente" INTEGER,
    "total" DECIMAL(10, 2) NOT NULL,
    CONSTRAINT "fk_cliente"
        FOREIGN KEY ("id_cliente")
        REFERENCES "cliente" ("id_cliente")
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-------------------------------------------------------
-- Table "detalle_orden"
-------------------------------------------------------
CREATE TABLE IF NOT EXISTS "detalle_orden" (
    "id_detalle" SERIAL PRIMARY KEY,
    "id_orden" INTEGER,
    "id_producto" INTEGER,
    "cantidad" INT NOT NULL,
    "precio_unitario" DECIMAL(10, 2) NOT NULL,
    CONSTRAINT "fk_orden"
        FOREIGN KEY ("id_orden")
        REFERENCES "orden" ("id_orden")
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT "fk_producto"
        FOREIGN KEY ("id_producto")
        REFERENCES "producto" ("id_producto")
        ON DELETE CASCADE
        ON UPDATE CASCADE
);