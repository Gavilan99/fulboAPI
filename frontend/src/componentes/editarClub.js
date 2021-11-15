import React, {useState, useEffect} from "react"

class edicionCampeonato extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            club: props.club,
            editingName: false,
            editingAdress: false,
        }
        this.nuevoNombre="";
        this.nuevaDireccion="";
    }

    cambiarDatos() {
        if (this.nuevoNombre != "")
            club.nombre=this.nuevoNombre;
        if(this.nuevaDireccion != "")
            club.nombre=this.nuevaDireccion;
        fetch("http://localhost:8080/updateClub",{method:"PUT", body: JSON.stringify(club)})
    }

    //Falta el link de añadir jugador al boton de añadir jugador
    render(){
        return(
            <div>
                <head>
                    <h1>
                        Datos club: 
                    </h1>
                </head>
                <body>
                    {this.state.editingName ?(
                    <span>
                        <h2>
                            {club.nombre}       <button onClick={() => {this.setState({ editingName: true });}}>EDITAR</button>
                        </h2>
                    </span>
                    ):(
                        <div>
                            <input
                                type="text"
                                defaultValue = {club.nombre}
                                ref={node =>{
                                    this.nuevoNombre = node;
                                }}
                            />
                            <button onClick = {() => {this.cambiarDatos ; this.setState({editingName: false})}}> OK </button>
                        </div>

                    )}
                    {this.state.editingAdress ?(
                    <span>
                        <h2>
                            {club.direccion}    <button onClick={() => {this.setState({ editingAdress: true });}}>EDITAR</button>
                        </h2>
                    </span>
                    ):(
                        <div>
                            <input
                                type="text"
                                defaultValue = {club.direccion}
                                ref={node =>{
                                    this.nuevaDireccion = node;
                                }}
                            />
                            <button onClick = {() => {this.cambiarDatos ; this.setState({editingAdress: false})}}> OK </button>
                        </div>
                    )}
                    <button onClick="">AÑADIR JUGADOR</button> 

                    <div id="tablaJugadores">
                        <div class="nombreClub">Tabla {club.nombre}</div>
                        <br />
                        <table id="jugadores">
                        <thead>
                            {/*Head fijo*/}
                            <tr class="cabecera">
                            <th>#</th>
                                <th>Jugadores</th>
                                <th class="nombre">Nombre</th>
                                <th class="apellido">Apellido</th>
                                <th class="tipoDni">Tipo DNI</th>
                                <th class="dni">DNI</th>
                                <th class="fechaNac">Fecha Nacimiento</th>
                                <th class="cat">Categoria</th>
                                <th class="estado">Hablitado</th>
                                <th class="mail">E-Mail</th>
                                <th class="tel">Telefono</th>
                            </tr>
                        </thead>
                        <tbody>
                            {tabla.map((item, index) => filaTablaJugadores(item, club))}
                        </tbody>
                        </table>
                    </div>
                </body>
            </div>
        )
    }
}