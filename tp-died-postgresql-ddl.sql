
CREATE TABLE sucursal (
idsucursal		SERIAL	PRIMARY KEY,
nombre 			VARCHAR,
horarioapertura	TIME,
horariocierre 	TIME,
estado			VARCHAR	CHECK (estado IN ('OPERATIVA','NO_OPERATIVA')),
tipo			VARCHAR	CHECK (tipo IN ('COMERCIAL','FUENTE','SUMIDERO'))
);

CREATE TABLE ruta (
idruta			SERIAL	PRIMARY KEY,
origen			INTEGER,
destino			INTEGER,
duracion		INTEGER,
capacidadmaxima	FLOAT,
estado			VARCHAR CHECK (estado IN ('OPERATIVA','NO_OPERATIVA'))
);

CREATE TABLE producto (
idproducto		SERIAL	PRIMARY KEY,
nombre			VARCHAR,
descripcion		VARCHAR,
preciounitario	FLOAT,
pesokg			FLOAT
);

CREATE TABLE stock (
idproducto	INTEGER,
idsucursal	INTEGER,
cantidad	INTEGER,
PRIMARY KEY (idproducto, idsucursal)
);

CREATE TABLE ordendeprovision (
idorden			SERIAL PRIMARY KEY,
sucursalDestino	INTEGER,
fecha			TIMESTAMP,
tiempomaximo	INTEGER,
estadoorden		VARCHAR	CHECK (estadoorden IN ('PENDIENTE','EN_PROCESO'))
);

CREATE TABLE detalleorden (
idorden		INTEGER,
idproducto	INTEGER,
cantidad	INTEGER,
PRIMARY KEY (idorden, idproducto)
);

ALTER TABLE ordendeprovision
ADD FOREIGN KEY (sucursalDestino) REFERENCES sucursal (idsucursal) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ruta
ADD FOREIGN KEY (origen) REFERENCES sucursal (idsucursal) ON DELETE CASCADE ON UPDATE CASCADE,
ADD FOREIGN KEY (destino) REFERENCES sucursal (idsucursal) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE detalleorden
ADD FOREIGN KEY (idproducto) REFERENCES producto (idproducto) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE detalleorden
ADD FOREIGN KEY (idorden) REFERENCES ordendeprovision (idorden) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE stock
ADD FOREIGN KEY (idproducto) REFERENCES producto (idproducto) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE stock
ADD FOREIGN KEY (idsucursal) REFERENCES sucursal (idsucursal) ON DELETE CASCADE ON UPDATE CASCADE;