import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../estilos/estiloPagina.css";
import mostrarDatoPartido from "./mostrarDatoPartido";

class MostrarStats extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            user: props.user,
            idPartido: props.match.params.id,
            partido: null,
            datosJugadoresL: [],
            datosJugadoresV: [],
            cargando: true
        }
    }

    convalidar() {
        fetch("http://localhost:8080/validarResultado?idPartido=" + this.state.idPartido + "&idRepresentante=" + this.state.user.id + "&validacion=S", {method:"PUT", mode: 'cors', headers: {'Content-Type': 'application/json'}})
        .then(() => {
            alert("Validado Correctamente!")
            let boton = document.getElementById("botonConvalidar")
            boton.style.display = "none"
        })
        .catch(e => console.log(e))
    }

    componentDidMount() {
        fetch("http://localhost:8080/getPartido?idPartido=" + parseInt(this.state.idPartido))
        .then((response) => response.json())
        .then((part) => {
            console.log(part)
            this.setState({partido: part})
            if (this.state.user.rol === "Representante" && this.state.partido.terminado === 'S') {
                fetch("http://localhost:8080/getRepresentante?idRepresentante=" + this.state.user.id)
                .then((vuelta) => vuelta.json())
                .then((rep) => {
                    if ((rep.club.idClub != this.state.partido.clubLocal.idClub && rep.club.idClub != this.state.partido.clubVisitante.idClub) || (rep.club.idClub === this.state.partido.clubLocal.idClub && this.state.partido.convalidaLocal === "S") || (rep.club.idClub === this.state.partido.clubVisitante.idClub && this.state.partido.convalidaVisitante === "S")) {
                        let boton = document.getElementById("botonConvalidar")
                        boton.style.display = "none"
                    }
                })
            }
            else if (this.state.user.rol === "Representante"){
                let boton = document.getElementById("botonConvalidar")
                boton.style.display = "none"
            }
            console.log(this.state.partido.jugadoresLocales)
            this.state.partido.jugadoresLocales.map(miembro => 
                fetch("http://localhost:8080/getDatoPartidoJugador?idPartido=" + parseInt(this.state.idPartido) + "&idJugador=" + parseInt(miembro.jugador))
                .then((respuesta) => respuesta.json())
                .then((datoJ) =>  {
                    console.log(datoJ)
                    let datosJugL = this.state.datosJugadoresL
                    datoJ.map((dato) => datosJugL.push(dato))
                    let ordenado = false
                    while (!ordenado) {
                        ordenado = true
                        for (var i=0; i < datosJugL.length - 1; i++) {
                            for (var j=i; j < datosJugL.length; j++) {
                                if (datosJugL.at(i).minuto > datosJugL.at(j).minuto) {
                                    let aux = datosJugL.at(i)
                                    datosJugL[i] = datosJugL.at(j)
                                    datosJugL[j] = aux
                                }
                            }
                        }
                        for (var k=0; k < datosJugL.length - 1; k++){
                            if (datosJugL.at(k).minuto > datosJugL.at(k + 1).minuto){
                                ordenado = false
                            }
                        }
                    }
                    this.setState({datosJugadoresL: datosJugL})
                })
                .catch((error) => {
                    console.log(`Hay un error en la llamada: ${error}`);
                })
            )
            this.state.partido.jugadoresVisitantes.map(miembro => 
                fetch("http://localhost:8080/getDatoPartidoJugador?idPartido=" + this.state.idPartido + "&idJugador=" + miembro.jugador)
                .then((respuesta) => respuesta.json())
                .then((datoJ) =>  {
                    console.log(datoJ)
                    let datosJugV = this.state.datosJugadoresV
                    datoJ.map((dato) => datosJugV.push(dato))
                    let ordenado = false
                    while (!ordenado) {
                        ordenado = true
                        for (var i=0; i < datosJugV.length - 1; i++) {
                            for (var j=i; j < datosJugV.length; j++) {
                                if (datosJugV.at(i).minuto > datosJugV.at(j).minuto) {
                                    let aux = datosJugV.at(i)
                                    datosJugV[i] = datosJugV.at(j)
                                    datosJugV[j] = aux
                                }
                            }
                        }
                        for (var k=0; k < datosJugV.length - 1; k++){
                            if (datosJugV.at(k).minuto > datosJugV.at(k + 1).minuto){
                                ordenado = false
                            }
                        }
                    }
                    this.setState({datosJugadoresV: datosJugV})
                })
                .catch((error) => {
                    console.log(`Hay un error en la llamada: ${error}`);
                })
            )
            this.setState({cargando: false})
        })
        .catch((error) => {
            console.log(`Hay un error en la llamada: ${error}`);
        })
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
            return (
                <div>
                    <h2 id="vs">vs</h2>
                    <h2 id="clocal">{this.state.partido.clubLocal.nombre}</h2>
                    <h2 id="glocal">{this.state.partido.golesLocal}</h2>
                    <h2 id="gvisitante">{this.state.partido.golesVisitante}</h2>
                    <h2 id="cvisitante">{this.state.partido.clubVisitante.nombre}</h2>
                    <div class="descripcionLocal">
                        <div class="hechos">
                            <h4>ESTADISTICAS LOCAL</h4>
                            {this.state.datosJugadoresL.map((datoJ) => mostrarDatoPartido(datoJ))}
                        </div>
                    </div>
                    <div class="descripcionVisitante">
                        
                        <div class="hechos">
                            <h4>ESTADISTICAS VISITANTE</h4>
                            {this.state.datosJugadoresV.map((datoJ) => mostrarDatoPartido(datoJ))}
                        </div>
                    </div>
                    {this.state.user.rol === "Representante" ? (
                        <div id="botonConvalidar" onClick={this.convalidar.bind(this)}>
                            <h4>CONVALIDAR</h4>
                        </div>
                    ) : (<div></div>)}
                    <div class = "botonVolver">
                        <Link to={"/campeonato/" + this.state.partido.campeonato}>
                            <div>
                                <h4>VOLVER</h4>
                            </div>
                        </Link>
                    </div>
                    <div>
                        <p id="espacio">_</p>
                    </div>
                    <br/>
                    <br/>
                </div>
            )
        }
    }
}

export default MostrarStats;