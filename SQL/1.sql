SELECT * FROM producto
SELECT * FROM persona
SELECT * FROM personal
SELECT * FROM cliente
SELECT * FROM inventario
SELECT * FROM Factura
SELECT * FROM facturaCliente
SELECT * FROM facturaProducto


CREATE TABLE Factura (
    idFactura char(6) PRIMARY KEY (idFactura),
    fechaVenta date
)

CREATE TABLE facturaCliente(
	idFacturaC char(6),
	idclientef char(13),
	PRIMARY KEY (idFacturaC),
	FOREIGN KEY (idFacturaC) REFERENCES Factura(idFactura),
	FOREIGN KEY (idClienteF) REFERENCES Cliente(idCliente)
)

CREATE TABLE facturaProduc(
	cantidad int,
	idFacturaP char(6),
	idProductoF char(13),
	PRIMARY KEY (idFacturaP, idProductoF),
	FOREIGN KEY (idFacturaP) REFERENCES Factura(idFactura),
	FOREIGN KEY (idProductoF) REFERENCES Producto(idProducto)
)

CREATE TABLE inventario(
	idIngrediente char(6) PRIMARY KEY,
	precioIngrediente int,
	nombreIngrediente varchar(50),
	cantDisponible int,
	fechavencimiento date
)

CREATE TABLE persona (
idpersona char (13) primary key,
nombre varchar (50),
correo varchar (25),
contacto char  (9)
);

CREATE TABLE cliente (
idcliente char (13) primary key,
direccion varchar (20)
);

CREATE TABLE personal (
idpersonal char (13) primary key,
salario integer,
Idpuestopersonal char (13),
FOREIGN KEY (idpersonal) REFERENCES persona(idpersona),
FOREIGN KEY (Idpuestopersonal) REFERENCES puesto(Idpuestopersonal)
);

CREATE TABLE puesto(
Idpuestopersonal char (13) primary key,
n_puesto varchar (20)
);

CREATE TABLE Egresos(
	idEgresos char(2)
);

Create Table Devoluciones(
	motivo text
);

CREATE TABLE ProductoVencido(
	fechadevencimiento date
);

