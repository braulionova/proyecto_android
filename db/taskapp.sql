
-- Table: categoria
CREATE TABLE categoria (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descripcion TEXT
);

-- Table: tarea
CREATE TABLE tarea (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT,
    fecha NUMERIC,
    usuario_creador_id INTEGER,
    usuario_asignado_id INTEGER,
    estado TEXT DEFAULT 'PENDIENTE',
    descripcion TEXT,
    categoria_id INTEGER
);

-- Table: usuario
CREATE TABLE usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT,
    email TEXT
);

-- Table: usuario_categoria
CREATE TABLE usuario_categoria (
    usuario_id INTEGER,
     categoria_id INTEGER
);
