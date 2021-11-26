import React, {useState, useEffect} from "react"
import "../estilos/dropdown.css";
import Dropdown from "./hola.js"

class EditarGolesPartido extends React.Component{
    constructor(props){
        super(props);
        this.state ={
            idPartido: props.match.params.id,
            jugadores: [],
            sentido:["a favor", "en contra"], //selectOption
            minuto:"",
            cargando:true,
            inputSentido: "",
            inputJugador: "",
            inputMinuto: ""
        }
    }



    componentDidMount(){
        fetch("http://localhost:8080/getJugadoresPartido?idPartido="+ this.state.idPartido)
        .then(response => response.json())
        .then(data => {this.setState({jugadores: data});this.setState({cargando: false});console.log(data)})
        .catch(e=> console.log(e))
    }

    agregarGol() {
        let gol={
            jugador: this.state.inputJugador,
            minuto: this.state.inputMinuto,
            sentido: this.state.inputSentido
        }
        fetch("http://localhost:8080/addGol?idJugador=" + this.state.inputJugador + "&idPartido=" + this.state.idPartido,{
            method:"POST", mode: 'cors', body: JSON.stringify(gol), headers: {'Content-Type': 'application/json'}})
            .catch(e =>console.log(e));
    }

    onChangeSetSentido(e){
        this.setState({inputSentido: e.target.value})
    }
    onChangeSetJugador(e){
        this.setState({inputJugador: e.target.value})

    }

    onChangeSetMinuto(e){
        this.setState({inputMinuto: e.target.value})
    }

    render(){
        if(this.state.cargando){
            return(
                <div>
                    CARGANDO...
                </div>
            )
        }
        else{
            return(
                <div>
                    <body>
                        <h1>Cargar Gol</h1>
                        <h2>
                            Jugador: 
                            <select id="jugador" onChange={this.onChangeSetJugador.bind(this)}>
                            {this.state.jugadores.map((jugador) => (
                                    <option value={jugador.idJugador}> {jugador.nombre}</option>
                                    ))}
                            </select>
                        </h2>
                        <h2>Minuto: <input type= "number" min="0" max="130" placeholder="0" onChange={this.onChangeSetMinuto.bind(this)}/> </h2>
                        <h2>Sentido: 
                            <select id="sentido" onChange={this.onChangeSetSentido.bind(this)}>
                                <option >{this.state.sentido[0]}</option>
                                <option>{this.state.sentido[1]}</option>
                            </select>
                        </h2>

                        <br/>
                        <h2>
                            <button onClick={this.agregarGol.bind(this)}>Confirmar</button>
                        </h2>
                    </body>
                </div>  
            )
        }
    }
}
export default EditarGolesPartido;