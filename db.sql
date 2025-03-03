CREATE DATABASE IF NOT EXISTS sistema_cursos;
USE sistema_cursos;

CREATE TABLE IF NOT EXISTS profesores (
    id DOUBLE PRIMARY KEY,
    nombres VARCHAR(255),
    apellidos VARCHAR(255),
    email VARCHAR(255),
    tipo_contrato VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cursos (
    id INT PRIMARY KEY,
    nombre VARCHAR(255),
    programa_id INT,
    activo BOOLEAN
);

CREATE TABLE IF NOT EXISTS asignaciones_cursos_profesores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    profesor_id DOUBLE,
    curso_id INT,
    ano INT,
    semestre INT,
    FOREIGN KEY (profesor_id) REFERENCES profesores(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

CREATE TABLE IF NOT EXISTS facultades (
    id DOUBLE PRIMARY KEY,
    nombre VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS programas (
    id DOUBLE PRIMARY KEY,
    nombre VARCHAR(255),
    facultad_id DOUBLE,
    duracion DECIMAL(3,1),
    fecha_creacion DATE,
    FOREIGN KEY (facultad_id) REFERENCES facultades(id)
);

CREATE TABLE IF NOT EXISTS estudiantes (
    id DOUBLE PRIMARY KEY,
    nombres VARCHAR(255),
    apellidos VARCHAR(255),
    email VARCHAR(255),
    programa_id DOUBLE,
    activo BOOLEAN,
    promedio DECIMAL(3,2),
    FOREIGN KEY (programa_id) REFERENCES programas(id)
);

CREATE TABLE IF NOT EXISTS inscripciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id DOUBLE,
    curso_id INT,
    ano INT,
    semestre INT,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);