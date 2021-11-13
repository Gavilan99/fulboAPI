import React, { useState, useEffect } from "react";
import "../estilos/estiloPagina.css";
import tabla from "./tabla";

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

  render() {
    if (this.state.cargando) {
      return (
        <div>
          <h2>Cargando...</h2>
        </div>
      );
    } else if (this.state.campeonato.tieneZonas === "n") {
      return (
        <div>
          <div class="titulo">{this.state.campeonato.descripcion}</div>
          <div id="tablaseccion">
            {tabla(this.state.campeonato, this.state.tabla, 0)}
          </div>
        </div>
      );
    } else {
      return (
        <div>
          <div class="titulo">{this.state.campeonato.descripcion}</div>
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
