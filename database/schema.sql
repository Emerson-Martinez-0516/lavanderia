CREATE TABLE tipo_servicio (
                               id SERIAL PRIMARY KEY,
                               nombre VARCHAR(60) NOT NULL UNIQUE,
                               precio_por_libra NUMERIC(10,2) NOT NULL CHECK (precio_por_libra > 0),
                               activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE orden_lavanderia (
                                  id SERIAL PRIMARY KEY,
                                  cliente VARCHAR(100) NOT NULL,
                                  peso_libras NUMERIC(10,2) NOT NULL CHECK (peso_libras > 0),
                                  total NUMERIC(10,2) NOT NULL,
                                  estado VARCHAR(20) NOT NULL DEFAULT 'RECIBIDA'
                                      CHECK (estado IN ('RECIBIDA', 'EN_PROCESO', 'LISTA', 'ENTREGADA')),
                                  fecha_recepcion DATE NOT NULL DEFAULT CURRENT_DATE,
                                  tipo_servicio_id INT NOT NULL REFERENCES tipo_servicio(id)
);