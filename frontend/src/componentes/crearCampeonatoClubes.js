import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../estilos/estiloPagina.css";

class CrearCampeonatoClubes extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            cantZonas: parseInt(props.match.params.zonas.split("-").at(0)),
            categoria: parseInt(props.match.params.zonas.split("-").at(1)),
            clubes: [],
            participantes: null,
            zonas: [],
            controlZona: 1,
            idCampeonato: 0,
            cargando: true
        }
    }

    agregarClubParticipante(indice, club) {
        let clubesDummy = this.state.clubes;
        clubesDummy.splice(indice, 1)
        this.setState({clubes: clubesDummy})
        let participantesDummy = this.state.participantes;
        if (this.state.cantZonas === 0) { 
            participantesDummy.push(club)
        }
        else {
            participantesDummy[this.state.controlZona - 1].push(club)
        }
        this.setState({participantes: participantesDummy})
        console.log(this.state.participantes)
    }

    sacarClubParticipante(indice, club) {
        let participantesDummy = this.state.participantes;
        participantesDummy.splice(indice, 1)
        this.setState({participantes: participantesDummy})
        let clubesDummy = this.state.clubes;
        clubesDummy.push(club)
        this.setState({clubes: clubesDummy})
    }

    sacarClubParticipanteZona(indice, club, zona) {
        let participantesDummy = this.state.participantes;
        participantesDummy.at(zona-1).splice(indice, 1)
        this.setState({participantes: participantesDummy})
        let clubesDummy = this.state.clubes;
        clubesDummy.push(club)
        this.setState({clubes: clubesDummy})
    }

    onChangeSaveZonas(e) {
        this.setState({controlZona: parseInt(e.target.value)})
    }

    confirmar() {
        if (this.state.cantZonas === 0){
            for (var i=0; i<this.state.participantes.length; i++){
                fetch("http://localhost:8080/inscribirClub?idClub=" + this.state.participantes.at(i).idClub + "&idCampeonato=" + this.state.idCampeonato, {method:"PUT", mode: 'cors', headers: {'Content-Type': 'application/json'}})
                .catch(e => console.log(e))
            }
            fetch("http://localhost:8080/addPartidos?idCampeonato=" + this.state.idCampeonato + "&categoria=" + this.state.categoria, {method:"POST", mode: 'cors', headers: {'Content-Type': 'application/json'}})
            .catch(e => console.log(e))
        }
        else {
            for (var i=0; i<this.state.participantes.length; i++){
                for (var j=0; j<this.state.participantes[i].length; j++){
                    fetch("http://localhost:8080/inscribirClub?idClub=" + this.state.participantes.at(i).at(j).idClub + "&idCampeonato=" + this.state.idCampeonato, {method:"PUT", mode: 'cors', headers: {'Content-Type': 'application/json'}})
                    .catch(e => console.log(e))
                }
            }
            let clubZonas = [[], [], [], [], [], [], [], [], []]
            for(var i=0; i < this.state.participantes.length; i++) {
                for (var j=0; j < this.state.participantes.at(i).length; j++) {
                    clubZonas[i].push(this.state.participantes.at(i).at(j).idClub)
                }
            }
            console.log(clubZonas)
            fetch("http://localhost:8080/addPartidosZonas?idCampeonato=" + this.state.idCampeonato + "&categoria=" + this.state.categoria, {method:"POST", mode: 'cors', body: JSON.stringify(clubZonas), headers: {'Content-Type': 'application/json'}})
            .catch(e => console.log(e))
        }
        let boton = document.getElementById("btnVolver")
        boton.style.display = "block"
    }

    componentDidMount() {
        if (this.state.cargando && this.state.cantZonas > 0) {
            let zones = []
            for (var i=0; i <= this.state.cantZonas; i++) {
                zones.push(i)
            }
            let participantesDummy = [[], [], [], [], [], [], [], [], []] //Poner el fill en Array(1) y manipular None
            this.setState({participantes: participantesDummy, zonas: zones})
            console.log(this.state.participantes)
            console.log(participantesDummy)
        }
        else if (this.state.cantZonas === 0) {
            this.setState({participantes: []})
        }
        if (this.state.cargando) {
            fetch("http://localhost:8080/getCampeonatos")
            .then((response) => response.json())
            .then((camps) => {
                let miCamp = 0;
                for (var i=0; i<camps.length; i++){
                    if (camps.at(i).idCampeonato > miCamp) {
                        miCamp = camps.at(i).idCampeonato
                    }
                }
                this.setState({idCampeonato: miCamp})
                console.log(this.state.idCampeonato)
                fetch("http://localhost:8080/getClubes")
                .then((response) => response.json())
                .then((clubs) => {
                    this.setState({clubes: clubs, cargando: false})
                })
                .catch(e => console.log(e))
            })
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
        else if (this.state.cantZonas === 0) {
            return(
                <div>
                    <h2>Carga de Clubes</h2>
                    <div id="participantes">
                        <h4>Participantes</h4>
                        <div class="tablaScroll">
                            <table>
                                <tbody>
                                    {this.state.participantes.map((club, index) => {
                                        if (index % 2 === 0){
                                            return(
                                                <tr class="fpar">
                                                    <td>{club.nombre}</td>
                                                    <td><button onClick={() => this.sacarClubParticipante(index, club)}>Sacar</button></td>
                                                </tr>
                                            )
                                        }
                                        else {
                                            return(
                                                <tr class="fimpar">
                                                    <td>{club.nombre}</td>
                                                    <td><button onClick={() => this.sacarClubParticipante(index, club)}>Sacar</button></td>
                                                </tr>
                                            )
                                        }
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="listaClubes">
                        <h4>Clubes</h4>
                        <div class="tablaScroll">
                            <table>
                                <tbody>
                                    {this.state.clubes.map((club, index) => {
                                        if (index % 2 === 0) {
                                            return(
                                                <tr class="fpar">
                                                    <td>{club.nombre}</td>
                                                    <td><button onClick={() => this.agregarClubParticipante(index, club)}>Agregar</button></td>
                                                </tr>
                                            )
                                        }
                                        else {
                                            return(
                                                <tr class="fimpar">
                                                    <td>{club.nombre}</td>
                                                    <td><button onClick={() => this.agregarClubParticipante(index, club)}>Agregar</button></td>
                                                </tr>
                                            )
                                        }
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
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
        else {
            return(
                <div>
                    <h2>Carga de Clubes</h2>
                    <div id="participantes">
                        <h4>Participantes</h4>
                        {this.state.zonas.map((zona) => {
                            if (zona > 0) {
                            return(
                                <div class="tablaScroll">
                                    <h6>Zona {zona}</h6>
                                    <table>
                                        <tbody>
                                            {this.state.participantes.at(zona - 1).map((club, index) => {
                                                if (index % 2 === 0){
                                                    return(
                                                        <tr class="fpar">
                                                            <td>{club.nombre}</td>
                                                            <td><button onClick={() => this.sacarClubParticipanteZona(index, club, zona)}>Sacar</button></td>
                                                        </tr>
                                                    )
                                                }
                                                else {
                                                    return(
                                                        <tr class="fimpar">
                                                            <td>{club.nombre}</td>
                                                            <td><button onClick={() => this.sacarClubParticipanteZona(index, club, zona)}>Sacar</button></td>
                                                        </tr>
                                                    )
                                                }
                                            })}
                                        </tbody>
                                    </table>
                                </div>
                            )}
                        })} 
                    </div>
                    <div id="listaClubes">
                        <h4>Clubes</h4>
                        <div class="tablaScroll">
                            <table>
                                <tbody>
                                    {this.state.clubes.map((club, index) => {
                                        if (index % 2 === 0) {
                                            return(
                                                <tr class="fpar">
                                                    <td>{club.nombre}</td>
                                                    <td>
                                                        <select onChange={this.onChangeSaveZonas.bind(this)}>
                                                            {this.state.zonas.map((zona) => {
                                                                return(
                                                                    <option value={zona}>{zona}</option>
                                                                )})}
                                                        </select>
                                                    </td>
                                                    <td><button onClick={() => this.agregarClubParticipante(index, club)}>Agregar</button></td>
                                                </tr>
                                            )
                                        }
                                        else {
                                            return(
                                                <tr class="fimpar">
                                                    <td>{club.nombre}</td>
                                                    <td>
                                                        <select onChange={this.onChangeSaveZonas.bind(this)}>
                                                            {this.state.zonas.map((zona) => {
                                                                return(
                                                                    <option value={zona}>{zona}</option>
                                                                )})}
                                                        </select>
                                                    </td>
                                                    <td><button onClick={() => this.agregarClubParticipante(index, club)}>Agregar</button></td>
                                                </tr>
                                            )
                                        }
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
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

export default CrearCampeonatoClubes;