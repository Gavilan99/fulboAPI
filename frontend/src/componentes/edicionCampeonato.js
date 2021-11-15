import React, {useState, useEffect} from "react"
import { Switch, Route, Link } from "react-router-dom";

class edicionCampeonato extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            campeonato: props.campeonato
        }
    }

    cambiarEstado() {
        fetch("http://localhost:8080/updateCampeonato",{method:"PUT", body: JSON.stringify(campeonato)}).then(
            response => {response.ok ? (campeonato.estado == "activo" ? (campeonato.estado = "inactivo") : campeonato.estado = "activo"):(console.log("holis"))}
        )
    }


   render(){
       return( 
            <div>
                <h1>
                    {this.nombre}
                    {this.campState}      <button onClick={this.cambiarEstado()}>{campeonato.estado}</button>
                </h1>
            </div>
       )
   }

   //Si no anda, componentDidUpdate

}

export default edicionCampeonato;
