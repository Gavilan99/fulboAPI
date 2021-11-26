import React, {useState, useEffect} from "react"

class golesPartido extends React.Component{
    constructos(props){
        super(props)
        this.state ={
            idJugador:0,
            idPartido: props.match.params.id,
            goles=0,
            sentido=["a favor", "en contra"], //selectOption
            minuto=""
        }
    }

    agregarGol() {
        let gol={
            jugador: 0,
            partido: 0,
            minuto: 0,
            sentido: ""
        }
        fetch("http://localhost:8080/addGol?idJugador=" + this.state.idJugador + "&idPartido=" + this.state.idPartido,{
            method:"POST", mode: 'cors', body: JSON.stringify(gol), headers: {'Content-Type': 'application/json'}}).catch
    }

    render(){
        const { sentido } = this.state;

	    let sentidoList = sentido.length > 0
		&& sentido.map((item, i) => {
		return (
			<option key={i} value={sentido[i]}></option>
		)
	}, this);
        return(
            <div>
                <body>
                    <h1>
                        JUGADOR 
                        <input> GOL </input>
                        <input> MINUTO </input>
                        <select> SENTIDO {sentidoList} </select>
                    </h1>
                </body>
            </div>
        )
    }
}