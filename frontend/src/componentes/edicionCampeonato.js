import React, {useState, useEffect} from "react"
import { Switch, Route, Link } from "react-router-dom";

function EdicionCampeonato(campeonato){
    let estado = campeonato.estado

    function cambiarEstado() {
        fetch("http://localhost:8080/updateCampeonato",{method:"PUT", body: JSON.stringify(campeonato)}).then(
            response => {campeonato.estado == "activo" ? (estado= "inactivo") : (estado= "activo")}
        ).catch(e => {console.log(e)})
        console.log(estado)
    }

    return( 
        <div>
            <h1>
                {campeonato.nombre}
                {estado}      <button onClick={cambiarEstado}>{estado}</button>
            </h1>
        </div>
    )
    


}

export default EdicionCampeonato;
