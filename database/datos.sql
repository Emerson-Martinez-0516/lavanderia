INSERT INTO tipo_servicio (nombre, precio_por_libra, activo) VALUES
    ('Lavado normal', 8.00, TRUE),
    ('Lavado y planchado', 12.50, TRUE),
    ('Lavado en seco', 20.00, TRUE),
    ('Edredones y cobijas', 15.00, TRUE),
    ('Solo secado', 5.00, TRUE);

INSERT INTO orden_lavanderia (cliente, peso_libras, total, estado, fecha_recepcion, tipo_servicio_id) VALUES
    ('Ana López', 10.00, 80.00, 'RECIBIDA', CURRENT_DATE, 1),
    ('Carlos Pérez', 6.50, 81.25, 'EN_PROCESO', CURRENT_DATE, 2),
    ('María Gómez', 4.00, 80.00, 'LISTA', CURRENT_DATE, 3),
    ('José Ramírez', 12.00, 180.00, 'LISTA', CURRENT_DATE, 4),
    ('Lucía Castillo', 8.00, 40.00, 'ENTREGADA', CURRENT_DATE - 1, 5),
    ('Ana López', 5.00, 40.00, 'ENTREGADA', CURRENT_DATE - 2, 1),
    ('Pedro Morales', 9.00, 112.50, 'EN_PROCESO', CURRENT_DATE, 2),
    ('Sofía Herrera', 3.00, 60.00, 'RECIBIDA', CURRENT_DATE, 3),
    ('Luis Méndez', 7.00, 56.00, 'LISTA', CURRENT_DATE - 1, 1),
    ('Carmen Ruiz', 11.00, 165.00, 'ENTREGADA', CURRENT_DATE - 3, 4),
    ('Carlos Pérez', 6.00, 30.00, 'RECIBIDA', CURRENT_DATE, 5),
    ('Diana Flores', 2.50, 31.25, 'EN_PROCESO', CURRENT_DATE, 2);