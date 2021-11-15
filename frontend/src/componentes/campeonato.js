import React, { useState, useEffect } from "react";
import { Switch, Route, Link, Router } from "react-router-dom";
import "../estilos/estiloPagina.css";
import tabla from "./tabla";
import EdicionCampeonato from "./EdicionCampeonato";

class Campeonato extends React.Component {
  constructor(props) {
    super(props);
    console.log("id: " + props.match.params.id);
    this.state = {
      idCampeonato: props.match.params.id,
      campeonato: null,
      tabla: [],
      cargando: true,
    };
  }
  //id (int), descripcion (str), fechaInicio (Date), fechaFin (Date), estado (str), clubes (array de json)
  //clubes[i]: id (int), nombre (str), direccion (str)

  componentDidMount() {
    fetch(
      "http://localhost:8080/getCampeonato?idCampeonato=" +
        this.state.idCampeonato
    )
      .then((response) => response.json())
      .then((camp) => {
        console.log(camp);
        this.setState({ campeonato: camp });
        if (this.state.campeonato.tieneZonas === "s") {
          fetch(
            "http://localhost:8080/getTablaPorZonas?idCampeonato=" +
              this.state.idCampeonato
          ) //Busco resultados
            .then((response) => response.json())
            .then((tablasJSON) => {
              console.log(tablasJSON);
              this.setState({ tabla: tablasJSON, cargando: false });
            })
            .catch((error) => {
              console.log(`Hay un error en la llamada: ${error}`);
            });
        } else {
          fetch(
            "http://localhost:8080/getTablaPos?idCampeonato=" +
              this.state.idCampeonato
          ) //Busco resultados
            .then((response) => response.json())
            .then((tablasJSON) => {
              console.log(tablasJSON);
              this.setState({ tabla: tablasJSON, cargando: false });
            })
            .catch((error) => {
              console.log(`Hay un error en la llamada: ${error}`);
            });
        }
      })
      .catch((error) => {
        console.log(`Hay un error en la llamada: ${error}`);
      });
  }

  cambiarEstado() {
    console.log(this.state)
    fetch("http://localhost:8080/updateCampeonato",{method:"PUT", body: JSON.stringify(this.state.campeonato)}).then(
        response => {this.state.campeonato.estado == "activo" ? (this.setState({campeonato:{estado: "inactivo"}})) : (this.setState({campeonato:{estado: "activo"}}))}
    ).catch(e => {console.log(e)})
  }

  render() {
    if (this.state.cargando) {
      return (
        <div>
          <h2>Cargando...</h2>
        </div>
      );
    } else if (this.state.campeonato.tieneZonas === "n") {
      return (
        <div class="datosCampeonato">
          <div class="titulo">{this.state.campeonato.descripcion}</div>
          <div class="estado">{this.state.campeonato.estado} <button onClick={() => {this.cambiarEstado()}}>Editar Campeonato</button></div> 
          <div id="tablaseccion">
            {tabla(this.state.campeonato, this.state.tabla, 0)}
          </div>
        </div>
      );
    } else {
      return (
        <div class="datosCampeonato">
          <div class="titulo">{this.state.campeonato.descripcion}</div>
          <div>
              <button onClick={() => {EdicionCampeonato(this.state.campeonato)}} class="botonEditar">
                Editar campeonato
                </button>
          </div>
          <div id="tablaseccion">
            {this.state.tabla.map((zona, index) =>
              tabla(this.state.campeonato, zona, index + 1)
            )}
          </div>
        </div>
      );
    }
  }
}

export default Campeonato;
