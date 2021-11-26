import React, {useState, useEffect} from "react"

class editarClub extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            club: props.club,
            editingName: false,
            editingAdress: false,
            cargando: true,
            tabla: []
        }
        this.nuevoNombre="";
        this.nuevaDireccion="";
    }

    cambiarDatos() {
        if (this.nuevoNombre != "")
            this.setState({club: {nombre: this.nuevoNombre}})
        if(this.nuevaDireccion != "")
            this.setState({club: {direccion: this.nuevaDireccion}})
        fetch("http://localhost:8080/updateClub",{method:"PUT", body: JSON.stringify(club)})
    }

    componentDidMount(){
        fetch("http://localhost:8080/getJugadoresClub?idClub=" + this.state.club.idClub).then(
            response =>{response.json()}
        ).then(
            miTabla =>{this.setState({tabla: miTabla})}
        ).catch(
            e => {console.log(e)})
        this.setState({cargando: false})
    }
    //Falta el link de añadir jugador al boton de añadir jugador
    render(){
        if(this.state.cargando){return(
            <div>
                <h1>
                    CARGANDO
                </h1>
            </div>
        )}else{
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
                                {this.state.club.nombre}       <button onClick={() => {this.setState({ editingName: true });}}>EDITAR</button>
                            </h2>
                        </span>
                        ):(
                            <div>
                                <input
                                    type="text"
                                    defaultValue = {this.state.club.nombre}
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
                                {this.state.club.direccion}    <button onClick={() => {this.setState({ editingAdress: true });}}>EDITAR</button>
                            </h2>
                        </span>
                        ):(
                            <div>
                                <input
                                    type="text"
                                    defaultValue = {this.state.club.direccion}
                                    ref={node =>{
                                        this.nuevaDireccion = node;
                                    }}
                                />
                                <button onClick = {() => {this.cambiarDatos ; this.setState({editingAdress: false})}}> OK </button>
                            </div>
                        )}
                        <button onClick="">AÑADIR JUGADOR</button> 

                        <div id="tablaJugadores">
                            <div class="nombreClub">Tabla {this.state.club.nombre}</div>
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
                                    <th class="direccion">Direccion</th>
                                    <th class="mail">E-Mail</th>
                                    <th class="tel">Telefono</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.tabla.map((item) => filaTablaJugadores(item))}
                            </tbody>
                            </table>
                        </div>
                    </body>
                </div>
            )
        }
    }
}

export default editarClub;