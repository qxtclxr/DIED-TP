INSERT INTO sucursal (idsucursal, nombre, horarioapertura, horariocierre, estado, tipo) VALUES 
    (100001,'Esperanza', '08:00:00', '18:00:00', 'OPERATIVA', 'COMERCIAL'),
	(100002,'Rafaela', '08:00:00', '18:00:00', 'OPERATIVA', 'COMERCIAL'),
	(100003,'Santa Fe', '08:00:00', '18:00:00', 'OPERATIVA', 'COMERCIAL'),
	(100004,'Parana', '08:00:00', '18:00:00', 'OPERATIVA', 'COMERCIAL'),
    (100005,'Centro', '09:30:00', '20:00:00', 'OPERATIVA', 'SUMIDERO'),
    (100006,'Puerto', '07:00:00', '16:30:00', 'OPERATIVA', 'FUENTE');
 
 INSERT INTO ruta(idruta,origen,destino,duracion,capacidadmaxima,estado) VALUES
	(100001,100006, 100003,120, 500, 'OPERATIVA'),
	(100002,100001, 100002, 120, 500,'OPERATIVA'),
	(100003,100002, 100001, 120, 500,'OPERATIVA'),
	(100004,100001, 100003, 120, 500,'OPERATIVA'),
	(100005,100003, 100001, 120, 500,'OPERATIVA'),
	(100006,100003, 100004, 120, 500, 'OPERATIVA'),
	(100007,100004, 100005, 120, 500,'OPERATIVA'),
	(100008,100002, 100005, 120, 500, 'OPERATIVA');
	
INSERT INTO producto (idproducto,nombre, descripcion, preciounitario, pesokg ) VALUES
	(100001,'Lavarropas', 'Lavarropas de carga frontal de 8 kg', 500.50, 70),
    (100002,'Televisor', 'Televisor LED de 55 pulgadas', 799.99, 25),
    (100003,'Aspiradora', 'Aspiradora inalambrica con filtro HEPA', 159.95, 4.5);	


INSERT INTO stock(idproducto, idsucursal, cantidad) VALUES
	(100001, 100001, 50),
	(100001, 100002, 25),
	(100002, 100003, 10),
	(100002, 100004, 12),
	(100003, 100005, 17),
	(100003, 100006, 14);	
	
INSERT INTO ordendeprovision (idorden,sucursalDestino, fecha, tiempomaximo, estadoorden) VALUES
	(100001,100004, '2023-08-04', 3, 'EN_PROCESO'),
	(100002,100005, '2023-08-03', 3, 'PENDIENTE');

INSERT INTO detalleorden (idorden, idproducto, cantidad) VALUES
	(100001, 100001 , 10),
	(100002, 100001 , 10);
	
INSERT INTO caminos (idorden, idruta, step) VALUES
	(100001, 100008, 1),
	(100001, 100004, 2);

