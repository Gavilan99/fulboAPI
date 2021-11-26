import React, {useState, useEffect} from "react"
import "../estilos/dropdown.css";
import Dropdown from "./hola.js"

class EditarGolesPartido extends React.Component{
    constructor(props){
        super(props);
        this.state ={
            idPartido: props.match.params.id,
            idJugador:0,
            jugadoresLocal: [],
            jugadoresVisitante: [],
            sentido:["a favor", "en contra"], //selectOption
            minuto:""
        }
    }

    componentDidMount(){
        fetch("/getMiembrosLocales?idPartido="+ this.state.idPartido)
        .then(response => response.json())
        .then(data => this.setState({jugadoresLocal: data}))
        .catch(e => console.log(e))
        fetch("/getMiembrosVisitantes?idPartido="+ this.state.idPartido)
        .then(response => response.json())
        .then(data => this.setState({jugadoresVisitante: data}))
        .catch(e => console.log(e))
    }

    agregarGol() {
        let gol={
            jugador: 0,
            partido: 0,
            minuto: 0,
            sentido: ""
        }
        fetch("http://localhost:8080/addGol?idJugador=" + this.state.idJugador + "&idPartido=" + this.state.idPartido,{
            method:"POST", mode: 'cors', body: JSON.stringify(gol), headers: {'Content-Type': 'application/json'}})
            .catch(e =>console.log(e));
    }


    render(){
        
        this.state.sentido.map((item, i) => {
		return (
			<option key={i} value={this.state.sentido[i]}></option>)}, this);
        return(
            <div>
                <body>
                    <h1>Cargar Gol</h1>
                    <h3>Minuto: <input type= "text"/> </h3>
                    <h3>Sentido</h3>
                    <select>
                        <option>{this.state.sentido[0]}</option>
                        <option>{this.state.sentido[1]}</option>
                    </select>

                    <br/>
                    <button onClick={this.agregarGol.bind(this)}>Confirmar</button>
                </body>
            </div>
        )
    }
}
export default EditarGolesPartido;