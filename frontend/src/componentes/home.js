import React, {useState, useEffect} from "react"
import { Switch, Route, Link } from "react-router-dom";
import ManagerDataService from "../servicios/misServicios"
import "../estilos/estiloPagina.css";

class Home extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            user: props.user,
            campeonatos: [],
            cargando: true
        }
    }

    componentDidMount() {
        
        fetch("http://localhost:8080/getCampeonatos") //Buscar Campeonatos
        .then((response) => response.json())
        .then((campeonatosJSON) => {
          this.setState({ campeonatos: campeonatosJSON, cargando: false })
        })
        .catch((error) => {
          console.log(`Hay un error en la llamada: ${error}`);
        });
        console.log(this.state.campeonatos)
        
    //    ManagerDataService.getCampeonatos()
    //    .then(response => {
    //        this.setState({ campeonatos: response.data, cargando: false})
    //    })
    //    .catch(e => {
    //        console.log(e)
    //    })

    }

    render() {
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
export default Home;