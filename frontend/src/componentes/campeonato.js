import React, { useState, useEffect } from "react";
import "../estilos/estiloPagina.css";
import tabla from "./tabla";
import mostrarPartido from "./mostrarPartido";

class Campeonato extends React.Component {
  constructor(props) {
    super(props);
    console.log("id: " + props.match.params.id);
    this.state = {
      idCampeonato: props.match.params.id,
      campeonato: null,
      tabla: [],
      partidos: [],
      cantFechas: [],
      fechaActual: 1,
      cargando: true,
    };
  }
  //id (int), descripcion (str), fechaInicio (Date), fechaFin (Date), estado (str), clubes (array de json)
  //clubes[i]: id (int), nombre (str), direccion (str)

  onChangeBuscarFecha(e) {
    this.setState({fechaActual: parseInt(e.target.value)})
  }

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
              this.setState({ tabla: tablasJSON });
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
              this.setState({ tabla: tablasJSON });
            })
            .catch((error) => {
              console.log(`Hay un error en la llamada: ${error}`);
            });
        }
        fetch(
          "http://localhost:8080/getPartidosCampeonato?idCampeonato="+
            this.state.idCampeonato
        )
          .then((response) => response.json())
          .then((partidosJSON) => {
            console.log(partidosJSON);
            this.setState({ partidos: partidosJSON })
            fetch("http://localhost:8080/getCantidadFechas?idCampeonato="+
                    this.state.idCampeonato
            )
            .then((response) => response.json())
            .then((cant) => {
              let arrFechas = [];
              for (var i=1; i<=cant; i++){
                arrFechas.push(i);
              }
              console.log(arrFechas)
              this.setState({cantFechas: arrFechas, cargando: false});
            })
            .catch((error) => {
              console.log(`Hay un error en la llamada: ${error}`);
            })
          })
          .catch((error) => {
            console.log(`Hay un error en la llamada: ${error}`);
          });
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
          <div class="tablas">
            {tabla(this.state.campeonato, this.state.tabla, 0)}
          </div>
          <div class="fixture">
            <div class="fechaSelectores">
              Fecha: 
              <select onChange={this.onChangeBuscarFecha.bind(this)}>
                 {this.state.cantFechas.map(fecha => {
                    return (
                      <option value={fecha}> {fecha} </option>
                    )
                  })}
                </select>
            </div>
            <table class="tablafixture">
                <tbody>
                  <tr class="diafecha">{this.state.partidos.filter((partido) => partido.nroFecha === this.state.fechaActual).at(0).fechaPartido}</tr>
                  {this.state.partidos
                  .filter((partido) => partido.nroFecha === this.state.fechaActual)
                  .map((part, index) => 
                    mostrarPartido(part, index)
                  )}
                </tbody>
            </table>
          </div>
        </div> 
      );
    } else {
      return (
        <div>
          <div class="titulo">{this.state.campeonato.descripcion}</div>
          <div class="tablas">
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
