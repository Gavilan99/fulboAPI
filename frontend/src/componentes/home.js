import React, {useState, useEffect} from "react"
import { Switch, Route, Link } from "react-router-dom";
import "../estilos/estiloPagina.css";

class Home extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            user: props.user,
            campeonatos: [],
            cargando: true,
        }
    }

    componentDidMount() {
        if (this.state.user.rol === "Administrador") {
            fetch("http://localhost:8080/getCampeonatos") //Buscar Campeonatos
            .then((response) => response.json())
            .then((campeonatosJSON) => {
            this.setState({ campeonatos: campeonatosJSON, cargando: false })
            })
            .catch((error) => {
            console.log(`Hay un error en la llamada: ${error}`);
            });
            console.log(this.state.campeonatos)
        }
        else if (this.state.user.rol === "Jugador") {
            fetch("http://localhost:8080/getCampeonatosJugador?idJugador=" + this.state.user.id) 
            .then((response) => response.json())
            .then((campeonatosJSON) => {
            this.setState({ campeonatos: campeonatosJSON, cargando: false })
            })
            .catch((error) => {
            console.log(`Hay un error en la llamada: ${error}`);
            });
            console.log(this.state.campeonatos)
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
        else if (this.state.user.rol === "Jugador") {
            return (
                <div>
                    <h2>Mis Campeonatos</h2>
                    <div class="container">
                        {this.state.campeonatos.map(camp => {
                            return (
                            <Link to={"/campeonato/" + camp.idCampeonato}>
                                <div class = "rect-campeonato">
                                    <h4>{camp.descripcion}</h4>
                                </div>
                            </Link>
                            )
                        })}
                    </div>
                    <br/>
                    <br/>
                </div>
            )
        }
        else if (this.state.user.rol === "Administrador") {
            return (
                <div>
                    <h2>Mis Campeonatos</h2>
                    <div class="container">
                        {this.state.campeonatos.map(camp => {
                            return (
                            <Link to={"/campeonato/" + camp.idCampeonato}>
                                <div class = "rect-campeonato">
                                    <h4>{camp.descripcion}</h4>
                                </div>
                            </Link>
                            )
                        })}
                    </div>
                    <br/>
                    <br/>
                </div>
            )
        }
    }

}
export default Home;