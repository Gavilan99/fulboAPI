import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../estilos/estiloPagina.css";

class AgregarJugadorPartido extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            jugadores: [],
            inscriptos: [],
            idPartido: props.match.params.partidoClub,
            cargando: true,
            cantAmarillas: 3,
            idRepresentante: this.props.user.id,
            user: this.props.user,
            club: null
        }
        console.log(this.state.user)
    }
    
    agregarJugador(indice, jugador) { //tabla que empieza vacia, saca de jugadores pone en inscriptos
        let jugadoresDummy = this.state.jugadores;
        jugadoresDummy.splice(indice, 1)
        this.setState({jugadores: jugadoresDummy})
        let inscriptosDummy = this.state.inscriptos;
        inscriptosDummy.push(jugador)
        this.setState({inscriptos: inscriptosDummy})
    }

    sacarJugador(indice, jugador) { //tabla con jugadores
        let inscriptosDummy = this.state.inscriptos;
        inscriptosDummy.splice(indice, 1)
        this.setState({inscriptos: inscriptosDummy})
        let jugadoresDummy = this.state.jugadores;
        jugadoresDummy.push(jugador)
        this.setState({jugadores: jugadoresDummy})
    }

    confirmar() {
        console.log(this.state.idPartido)
        console.log(this.state.inscriptos.at(0).idJugador)
        console.log(this.state.cantAmarillas)
        for (var i=0; i<this.state.inscriptos.length; i++){
            fetch("http://localhost:8080/addJugadorLista?idPartido="+ this.state.idPartido + "&idJugador=" + this.state.inscriptos.at(i).idJugador + "&cantAmarillas=" + this.state.cantAmarillas, {method:"POST", mode: 'cors', headers: {'Content-Type': 'application/json'}})
            .catch(e => console.log(e))
        }
        let boton = document.getElementById("btnVolver")
        boton.style.display = "block"
    }

    componentDidMount() {
        if (this.state.cargando) {
                console.log(this.state.idRepresentante)
                console.log(this.state.idPartido)
                fetch("http://localhost:8080/getRepresentante?idRepresentante=" + this.state.idRepresentante)
                .then(response => response.json())
                .then(datos => this.setState({club: datos.club}))
                .then(() =>
                fetch("http://localhost:8080/getJugadoresClubNoEnPartido?idPartido=" + this.state.idPartido +"&idClub=" + this.state.club.idClub)
                .then(response => response.json())
                .then((data) => {
                    this.setState({jugadores: data, cargando: false})
                })
                .catch(e => console.log(e)))
                .catch(e => console.log(e))
            } 
        }


    render() {
        if (this.state.cargando) {
            return (
                <div>
                    <h2>Cargando...</h2>
                </div>
            );
        }
        else {
            return(
                <div>
                    <h2>Carga de jugadores al partido</h2>
                    <span>
                    <table id="tablaJugadores">
                        <thead>
                            <caption><h3>Jugadores para el partido</h3></caption>
                            <tr><th>Nombre</th><th>Agregar</th></tr>
                        </thead>
                        <tbody>

                        {this.state.inscriptos.map((jugador, index) => {
                                        if (index % 2 === 0){
                                            return(
                                                <tr class="fpar">
                                                    <td>{jugador.nombre}</td>
                                                    <td><button onClick={() => this.sacarJugador(index, jugador)}>Sacar</button></td>
                                                </tr>
                                            )
                                        }
                                        else {
                                            return(
                                                <tr class="fimpar">
                                                    <td>{jugador.nombre}</td>
                                                    <td><button onClick={() => this.sacarJugador(index, jugador)}>Sacar</button></td>
                                                </tr>
                                            )
                                        }
                                    })}

                        </tbody>

                    </table>

                    </span>
                    
                    <span> 
                    <table>
                            <thead>
                                <caption><h3>Jugadores del club</h3></caption>
                                <tr><th>Nombre</th></tr>
                            </thead>
                            <tbody>
                            <tbody>
                                    {this.state.jugadores.map((jugador, index) => {
                                        console.log(jugador)
                                        if (index % 2 === 0) {
                                            return(
                                                <tr class="fpar">
                                                    <td>{jugador.nombre}</td>
                                                    <td><button onClick={() => this.agregarJugador(index, jugador)}>Agregar</button></td>
                                                </tr>
                                            )
                                        }
                                        else {
                                            return(
                                                <tr class="fimpar">
                                                    <td>{jugador.nombre}</td>
                                                    <td><button onClick={() => this.agregarJugador(index, jugador)}>Agregar</button></td>
                                                </tr>
                                            )
                                        }
                                    })}
                                </tbody>


                            </tbody>

                    </table>
                </span>
                
                <div id="btnConfirmar">
                        <button onClick={this.confirmar.bind(this)}>Confirmar</button>
                    </div>
                <div id="btnVolver">
                    <Link to={"/home"}>
                        <div>
                            Volver
                        </div>
                    </Link>
                </div>
                
                </div>
            );
        }
    }
}

export default AgregarJugadorPartido;