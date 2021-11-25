create table usuarios (
	usuario varchar(100),
	contraseña varchar(100),
	rol varchar(100),
	idRol int,
	constraint pk_usuarios primary key (usuario)
)

insert into usuarios values ('chiquitapia', 'Barracas22', 'Administrador', 1)
insert into usuarios values ('jlpappi', 'elpapipapi35', 'Jugador', 10)
insert into usuarios values ('randaluz', 'Andalucia58', 'Representante', 1)
