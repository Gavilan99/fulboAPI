import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../estilos/estiloPagina.css";


class CrearCampeonato extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            campeonato: null,
            descripcion: "",
            fechaIni: null,
            fechaFin: null,
            opZonas: [0, 2, 3, 4, 5, 6, 7, 8, 9],
            zonas: 0,
            categoria: 0,
            confirmar: false,
            cargando: true
        }
    }

    onChangeSaveDescripcion(e) {
        this.setState({descripcion: e.target.value})
    }

    onChangeSaveFechaIni(e) {
        this.setState({fechaIni: e.target.value})
    }

    onChangeSaveFechaFin(e) {
        this.setState({fechaFin: e.target.value})
    }

    onChangeSaveCategoria(e) {
        this.setState({categoria: parseInt(e.target.value)})
    }

    onChangeSaveCantZonas(e) {
        this.setState({zonas: parseInt(e.target.value)})
    }

    confirmar() {
        var ini = this.state.fechaIni.split("-")
        var fin = this.state.fechaFin.split("-")
        var camp = {
            descripcion: this.state.descripcion,
            fechaInicio: new Date(parseInt(ini.at(2)), parseInt(ini.at(1) -1), parseInt(ini.at(0))),
            fechaFin: new Date(parseInt(fin.at(2)), parseInt(fin.at(1) -1), parseInt(fin.at(0))),
            estado: "activo"
        }
        fetch("http://localhost:8080/addCampeonato", {method:"POST", mode: 'cors', body: JSON.stringify(camp), headers: {'Content-Type': 'application/json'}})
        .then(() => {
            let elem = document.getElementById("muestroConf");
            elem.style.display = "block";
        })
        .catch(e => {
            console.log(e)
        })
        
    }

    render() {
        return(
            <div>
                <h2>Crear Campeonato</h2>
                <div id="campDatos">
                    <p>Nombre del Campeonato </p>
                    <input
                        type="text"
                        placeholder="Descripcion"
                        value={this.state.descripcion}
                        onChange={this.onChangeSaveDescripcion.bind(this)}
                    />
                    <p>Fecha de Inicio </p>
                    <input
                        type="text"
                        placeholder="Fecha inicio"
                        value={this.state.fechaIni}
                        onChange={this.onChangeSaveFechaIni.bind(this)}
                    />
                    <p>Fecha de Finalizacion </p>
                    <input
                        type="text"
                        placeholder="Fecha fin"
                        value={this.state.fechaFin}
                        onChange={this.onChangeSaveFechaFin.bind(this)}
                    />
                    <p>Categoria </p>
                    <input
                        type="text"
                        placeholder="Categoria"
                        value={this.state.categoria}
                        onChange={this.onChangeSaveCategoria.bind(this)}
                    />
                </div>
                <div id="cantZonas">
                    <p>Cantidad de Zonas </p>
                    <select onChange={this.onChangeSaveCantZonas.bind(this)}>
                        {this.state.opZonas.map((cant) => {
                            return(
                                <option value={cant}>{cant}</option>
                            )})}
                    </select>
                </div>
                <div id="btnConf">
                    <button onClick={this.confirmar.bind(this)}>Confirmar</button>
                </div>
                <div id="muestroConf">
                    <Link to={"/crearCampeonatoClubes/" + this.state.zonas.toString() + "-" + this.state.categoria.toString()}>
                        <div id="botonContinuar">
                            Continuar
                        </div>
                    </Link>
                </div>
            </div>
        )
    }
}

export default CrearCampeonato;