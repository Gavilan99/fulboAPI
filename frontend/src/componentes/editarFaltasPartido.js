import React, {useState, useEffect} from "react"
import "../estilos/dropdown.css";


class EditarFaltasPartido extends React.Component{
    constructor(props){
        super(props);
        this.state ={
            idPartido: props.match.params.id,
            idCampeonato: props.match.params.campeonato,
            jugadores: [],
            tarjeta:["amarilla", "roja"], //selectOption
            minuto:"",
            cargando:true,
            inputTipo: "",
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

    agregarFalta() {
        let falta={
            jugador: this.state.inputJugador,
            minuto: this.state.inputMinuto,
            tipo: this.state.inputTipo
        }
        fetch("http://localhost:8080/addFalta?idJugador=" + this.state.inputJugador + "&idPartido=" + this.state.idPartido + "&idCampeonato=" + this.state.idCampeonato,{
            method:"POST", mode: 'cors', body: JSON.stringify(falta), headers: {'Content-Type': 'application/json'}})
            .catch(e =>console.log(e));
    }

    onChangeSetTipo(e){
        this.setState({inputTipo: e.target.value})
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
                        <h2>Tarjeta: 
                            <select id="tarjeta" onChange={this.onChangeSetTipo.bind(this)}>
                                <option >{this.state.tarjeta[0]}</option>
                                <option>{this.state.tarjeta[1]}</option>
                            </select>
                        </h2>

                        <br/>
                        <h2>
                            <button onClick={this.agregarFalta.bind(this)}>Confirmar</button>
                        </h2>
                    </body>
                </div>  
            )
        }
    }
}
export default EditarFaltasPartido;