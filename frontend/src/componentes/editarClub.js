import React, {useState, useEffect} from "react"
import filaTablaJugadores from "./filaTablaJugadores"

// .then(() => this.setState({cargando: false}))

class EditarClub extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            club: null,
            id: props.match.params.id,
            editingAdress: false,
            cargando: true,
            tabla: [],
            nuevaDireccion: ""
        }
    }

    cambiarDatos() {
        let clubDummy = this.state.club
        console.log(this.state.club)
        this.setState({editingAdress: false})
        clubDummy.direccion = this.state.nuevaDireccion
        console.log(clubDummy);
        fetch("http://localhost:8080/updateClub", {method: "PUT", mode: 'cors', body: JSON.stringify(clubDummy), headers: {'Content-Type': 'application/json'}})
        .then(() => this.setState({club: clubDummy}))
        .catch(e => console.log(e))
    }

    componentDidMount(){
        fetch("http://localhost:8080/getRepresentante?idRepresentante=" + this.state.id)
        .then(response => response.json())
        .then(data => this.setState({club: data.club}))
        .then(() => 
        {fetch("http://localhost:8080/getJugadoresClub?idClub=" + this.state.club.idClub).then(
            response2 =>response2.json()
        ).then(
            miTabla =>this.setState({tabla: miTabla, cargando: false, nuevaDireccion: this.state.club.direccion})
        )
        .catch(
            e => {console.log(e)})})
        .catch(
            er=> {console.log(er)}
        )
    }

    onChangeSetDireccion(e){
        this.setState({nuevaDireccion: e.target.value})
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
                    <body>
                        <h1>
                            DATOS CLUB: {this.state.club.nombre}    
                        </h1>
                        {!this.state.editingAdress ?(
                        <span>
                            <h2>
                                DIRECCION: {this.state.club.direccion}    <button onClick={() => {this.setState({ editingAdress: true });}}>EDITAR</button>
                            </h2>
                        </span>
                        ):(
                            <div>
                                <h2>
                                    DIRECCION: <input type= "text" value={this.state.nuevaDireccion} placeholder = "Direccion" onChange={this.onChangeSetDireccion.bind(this)}/> 
                                    <button onClick = {this.cambiarDatos.bind(this)}> OK </button>
                                </h2>
                            </div>
                        )}

                        <div id="tablaJugadores">
                            <div class="nombreClub">Tabla {this.state.club.nombre}</div>
                            <br />
                            <table id="jugadores">
                                <thead>
                                    {/*Head fijo*/}
                                    <tr class="cabecera">
                                        <th>#</th>
                                            <th>Jugadores</th>
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

export default EditarClub;