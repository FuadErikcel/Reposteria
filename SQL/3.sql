SELECT * FROM usuarios
SELECT * FROM personal


CREATE TABLE usuarios(
	idUsuario char(13) PRIMARY KEY,
	password varchar(80),
	tipoUsuario int,
FOREIGN KEY (idUsuario) REFERENCES personal(idpersonal)
)