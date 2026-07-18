-- =============================================
-- SCRIPT DE BASE DE DATOS - VetSystem
-- Universidad Tecnológica del Perú
-- Programación Orientada a Objetos
-- =============================================

CREATE DATABASE IF NOT EXISTS vetsystem;
USE vetsystem;

-- TABLAS
CREATE TABLE dueno (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(100) NOT NULL,
    apellido    VARCHAR(100) NOT NULL,
    dni         VARCHAR(20)  UNIQUE,
    telefono    VARCHAR(20),
    direccion   VARCHAR(150)
);

CREATE TABLE paciente (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(100) NOT NULL,
    especie     VARCHAR(50),
    raza        VARCHAR(50),
    edad        INT,
    peso        DOUBLE,
    id_dueno    INT,
    FOREIGN KEY (id_dueno) REFERENCES dueno(id)
);

CREATE TABLE veterinario (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    nombre          VARCHAR(100) NOT NULL,
    especialidad    VARCHAR(100),
    telefono        VARCHAR(20)
);

CREATE TABLE cita (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    fecha           DATE,
    hora            TIME,
    estado          ENUM('PENDIENTE','ATENDIDA','CANCELADA') DEFAULT 'PENDIENTE',
    id_paciente     INT,
    id_veterinario  INT,
    FOREIGN KEY (id_paciente)    REFERENCES paciente(id),
    FOREIGN KEY (id_veterinario) REFERENCES veterinario(id)
);

CREATE TABLE consulta (
    id              INT PRIMARY KEY AUTO_INCREMENT,
    fecha           DATE,
    motivo          VARCHAR(200),
    diagnostico     TEXT,
    tratamiento     TEXT,
    id_paciente     INT,
    id_veterinario  INT,
    FOREIGN KEY (id_paciente)    REFERENCES paciente(id),
    FOREIGN KEY (id_veterinario) REFERENCES veterinario(id)
);

CREATE TABLE medicamento (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    nombre      VARCHAR(100) NOT NULL,
    dosis       VARCHAR(100),
    stock       INT DEFAULT 0
);

CREATE TABLE usuario (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    username    VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(255) NOT NULL,
    rol         ENUM('ADMIN','VETERINARIO','RECEPCIONISTA')
);

-- DATOS DE PRUEBA
INSERT INTO dueno (nombre, apellido, dni, telefono, direccion) VALUES
('Carlos', 'García', '45123456', '987654321', 'Av. Lima 123'),
('María', 'López', '46234567', '976543210', 'Jr. Cusco 456'),
('Pedro', 'Martínez', '47345678', '965432109', 'Calle Trujillo 789');

INSERT INTO veterinario (nombre, especialidad, telefono) VALUES
('Dr. Juan Pérez', 'Medicina General', '944332211'),
('Dra. Ana Torres', 'Cirugía Animal', '933221100');

INSERT INTO paciente (nombre, especie, raza, edad, peso, id_dueno) VALUES
('Rex', 'Perro', 'Labrador', 3, 25.5, 1),
('Michi', 'Gato', 'Siamés', 2, 4.2, 2),
('Perico', 'Ave', 'Periquito', 1, 0.3, 3),
('Luna', 'Perro', 'Beagle', 5, 12.0, 1);

INSERT INTO usuario (username, password, rol) VALUES
('admin', '1234', 'ADMIN'),
('drjuan', '1234', 'VETERINARIO'),
('recep01', '1234', 'RECEPCIONISTA');

INSERT INTO cita (fecha, hora, estado, id_paciente, id_veterinario) VALUES
('2026-04-20', '09:00:00', 'ATENDIDA', 1, 1),
('2026-04-21', '10:30:00', 'PENDIENTE', 2, 2),
('2026-04-22', '11:00:00', 'PENDIENTE', 3, 1);

INSERT INTO consulta (fecha, motivo, diagnostico, tratamiento, id_paciente, id_veterinario) VALUES
('2026-04-20', 'Control rutinario', 'Paciente sano', 'Vitaminas mensual', 1, 1),
('2026-04-21', 'Fiebre y decaimiento', 'Infección leve', 'Antibiótico 5 días', 2, 2);

INSERT INTO medicamento (nombre, dosis, stock) VALUES
('Amoxicilina', '500mg cada 8h', 50),
('Vitamina C', '250mg diario', 100),
('Antiparasitario', '1 tableta mensual', 75);