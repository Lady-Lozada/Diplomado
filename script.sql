-- DROP DATABASE helpme_iud;

CREATE DATABASE IF NOT EXISTS helpme_iud  DEFAULT CHARACTER SET utf8 ;

USE helpme_iud;

-- Creación tabla roles
CREATE TABLE IF NOT EXISTS roles(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(45),
    PRIMARY KEY(id)
);

-- creación tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(120) NOT NULL,
    enabled TINYINT DEFAULT 1,
    password VARCHAR(250),
    nombre VARCHAR(120) NOT NULL,
    apellido VARCHAR(120),
    fecha_nacimiento DATE,
    red_social TINYINT DEFAULT 0,
    image TEXT,
    PRIMARY KEY(id),
    UNIQUE(username)
);

-- tabla pivote entre roles y usuarios
CREATE TABLE IF NOT EXISTS roles_usuarios(
    usuarios_id INT NOT NULL,
    roles_id INT NOT NULL,
    PRIMARY KEY ( usuarios_id, roles_id),
    FOREIGN KEY(usuarios_id) REFERENCES usuarios (id),
    FOREIGN KEY(roles_id) REFERENCES roles (id)
);

-- creación tabla delitos
CREATE TABLE IF NOT EXISTS delitos(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(300),
    usuarios_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(usuarios_id) REFERENCES usuarios (id)
);

-- creación tabla casos
CREATE TABLE IF NOT EXISTS casos(
    id INT NOT NULL AUTO_INCREMENT,
    fecha_hora DATETIME NOT NULL DEFAULT NOW(),
    latitud FLOAT,
    longitud FLOAT,
    altitud FLOAT,
    visible TINYINT,
    descripcion VARCHAR(250),
    url_map TEXT,
    rmi_map TEXT,
    usuarios_id INT NOT NULL,
    delitos_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(usuarios_id) REFERENCES usuarios (id),
    FOREIGN KEY(delitos_id) REFERENCES delitos (id)
);

-- LLENADO DE TABLAS

-- tabla roles
INSERT INTO roles (nombre, descripcion)
VALUES('ROLE_ADMIN', 'administrador del sistema');

INSERT INTO roles (nombre, descripcion)
VALUES('ROLE_USER', 'Usuario normal que registra casos');

-- creación de usuarios
-- admin 123456
INSERT INTO usuarios (username,enabled, password, nombre, apellido, 
fecha_nacimiento, red_social )
VALUES('lady.lozada@est.iudigital.edu.co',1, '$2a$10$ile3xJvFRsBNSW45ItRwVeFNQbQT04cVnj6YRuQnFh5dSP/WnWDsO',
'Lady', 'Lozada', '1991-11-24',0);

-- normal 123456
INSERT INTO usuarios (username,enabled, password, nombre, apellido, 
fecha_nacimiento, red_social )
VALUES('cesar.mosquera@est.iudigital.edu.co',1, '$2a$10$9ythNK7SWn9bFeRhKFfYU.U.Qiywqyj26Zx4vZkNTn6ally65PbwK',
'Cesar', 'Mosquera', '1990-08-11',0);

-- asignación de roles a los usuarios
-- usuario 1 con rol admin
INSERT INTO roles_usuarios(usuarios_id, roles_id)
VALUES(1, 1);
-- usuario 1 con rol user
INSERT INTO roles_usuarios(usuarios_id, roles_id)
VALUES(1, 2);
-- usuario 1 con rol user
INSERT INTO roles_usuarios(usuarios_id, roles_id)
VALUES(2, 2);


-- Insertar datos tabla delitos
INSERT INTO delitos(nombre, descripcion, usuarios_id)
VALUES ('Hurto' , 'cuando hurtan las pertenencias', 1);

INSERT INTO delitos(nombre, descripcion, usuarios_id)
VALUES ('Acoso sexual' , 'conducta no deseada de naturaleza sexual, que hace que la persona se sienta ofendida, humillada y/o intimidada', 1);