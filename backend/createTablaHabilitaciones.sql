create table habilitaciones (
	idJugador int not null,
	idCampeonato int not null,
	habilitado varchar(50),
	constraint pk_habilitaciones primary key (idJugador, idCampeonato),
	constraint fk_habilitaciones_jugadores foreign key (idJugador) references jugadores (idJugador),
	constraint fk_habilitaciones_campeonatos foreign key (idCampeonato) references campeonatos (idCampeonato)
)
alter table jugadores add habilitado varchar(15), direccion varchar(100), mail varchar(50), telefono varchar(50)

--En la tabla jugadores se agregaron las columnas: habilitado (varchar(15)), direccion (varchar(100)), mail (varchar(50)), telefono (varchar(50))
