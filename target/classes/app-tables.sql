-- Crear tabla 'usuario'
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasenia VARCHAR(100) NOT NULL,
    nivel VARCHAR(20) NOT NULL
);

-- Insertar usuario
INSERT INTO usuarios (usuario, email, contrasenia, nivel)
VALUES 
('user', 'email@example.com', 'password123', 'Intermedio');

-- Consultar usuarios
SELECT * FROM usuarios;
