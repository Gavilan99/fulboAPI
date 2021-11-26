import React from "react";
import {Link} from "react-router-dom";

class MisDatos extends React.Component {

    constructor (props){
        super(props);
        console.log("Componente de datos cargado")
        const [idVar, rolVar] = props.match.params.id.split("-");
        console.log("Id var:" +idVar)
        console.log("Rol var:" +rolVar)

        this.state ={
            id: parseInt(idVar),
            rol: rolVar,
            persona: null,
            cargando: true
        }
        console.log("Id:" +this.state.id)
        console.log("Rol:" +this.state.rol)

        this.onClickDireccion = this.onClickDireccion.bind(this);
        this.onClickMail = this.onClickMail.bind(this);
        this.onClickTelefono = this.onClickTelefono.bind(this);
        this.onClickNombre = this.onClickNombre.bind(this);
        this.onClickDocumento = this.onClickDocumento.bind(this);
    }

    componentDidMount(){
        console.log(this.state.rol === "Jugador")
        if (this.state.rol === "Jugador"){
        fetch("http://localhost:8080/getJugador?idJugador="+ this.state.id)
        .then(response => response.json())
        .then(data => {this.setState({persona: data, cargando: false})})
        .then(() => console.log(this.state.persona))
        .catch(e=>console.log(e))
    }
    else if (this.state.rol === "Representante") {
        fetch("http://localhost:8080/getRepresentante?idRepresentante="+ this.state.id)
        .then(response => response.json())
        .then(data => {this.setState({persona: data, cargando: false})})
        .then(() => console.log(this.state.persona))
        .catch(e=>console.log(e))

    }
    else {
        this.setState({cargando: false})
    }

        //ESTO ANDA
    }


    onClickDireccion(evento){
        var editable = document.getElementById("direccion").isContentEditable;
        console.log(editable)
        console.log(this.state.persona)
        if (editable){
            document.getElementById("direccion").contentEditable = false;
            const direccion= document.getElementById("direccion").textContent
            
            var jugadorDummy = this.state.persona
            jugadorDummy.direccion= direccion;
            fetch("http://localhost:8080/updateJugador",{method:"PUT",mode: 'cors', body: JSON.stringify(jugadorDummy), headers: {'Content-Type': 'application/json'}})
            .then(()=> this.setState({persona: jugadorDummy}))
            .catch(e => console.log(e))
        }
        else{
            document.getElementById("direccion").contentEditable = true;
        }

    }

    onClickMail(evento){
        var editable = document.getElementById("mail").isContentEditable;
        console.log(editable)
        console.log(this.state.persona)
        if (editable){
            document.getElementById("mail").contentEditable = false;
            const mail= document.getElementById("mail").textContent
            
            var jugadorDummy = this.state.persona
            jugadorDummy.mail= mail;
            fetch("http://localhost:8080/updateJugador",{method:"PUT",mode: 'cors', body: JSON.stringify(jugadorDummy), headers: {'Content-Type': 'application/json'}})
            .then(()=> this.setState({persona: jugadorDummy}))
            .catch(e => console.log(e))
        }
        else{
            document.getElementById("mail").contentEditable = true;
        }

    }

    onClickTelefono(evento){
        var editable = document.getElementById("telefono").isContentEditable;
        console.log(editable)
        console.log(this.state.persona)
        if (editable){
            document.getElementById("telefono").contentEditable = false;
            const telefono= document.getElementById("telefono").textContent
            
            var jugadorDummy = this.state.persona
            jugadorDummy.telefono= telefono;
            fetch("http://localhost:8080/updateJugador",{method:"PUT",mode: 'cors', body: JSON.stringify(jugadorDummy), headers: {'Content-Type': 'application/json'}})
            .then(()=> this.setState({persona: jugadorDummy}))
            .catch(e => console.log(e))
        }
        else{
            document.getElementById("telefono").contentEditable = true;
        }

    }

    onClickNombre(evento){
        var editable = document.getElementById("nombre").isContentEditable;
        console.log(editable)
        console.log(this.state.persona)
        if (editable){
            document.getElementById("nombre").contentEditable = false;
            const nombre= document.getElementById("nombre").textContent
            
            var representanteDummy = this.state.persona
            representanteDummy.nombre= nombre;
            fetch("http://localhost:8080/updateRepresentante",{method:"PUT",mode: 'cors', body: JSON.stringify(representanteDummy), headers: {'Content-Type': 'application/json'}})
            .then(()=> this.setState({persona: representanteDummy}))
            .catch(e => console.log(e))
        }
        else{
            document.getElementById("nombre").contentEditable = true;
        }

    }

    onClickDocumento(evento){
        var editable = document.getElementById("documento").isContentEditable;
        console.log(editable)
        console.log(this.state.persona)
        if (editable){
            document.getElementById("documento").contentEditable = false;
            const documento= document.getElementById("documento").textContent
            
            var representanteDummy = this.state.persona
            representanteDummy.documento= documento;
            fetch("http://localhost:8080/updateRepresentante",{method:"PUT",mode: 'cors', body: JSON.stringify(representanteDummy), headers: {'Content-Type': 'application/json'}})
            .then(()=> this.setState({persona: representanteDummy}))
            .catch(e => console.log(e))
        }
        else{
            document.getElementById("documento").contentEditable = true;
        }

    }

    render(){
        if (!this.state.cargando){
            if (this.state.rol === "Jugador"){
            return(
                <div>
                        <h2>Mis Datos</h2>
                    <br/>
                    <br/>
                    <h4>Nombre y Apellido: {this.state.persona.nombre} {this.state.persona.apellido}</h4>
                    <h4>Categoria: {this.state.persona.categoria}</h4>
                    <h4>
                        <span>Direccion: </span> <span id="direccion" contentEditable="false">{this.state.persona.direccion} </span> 
                    <button onClick={this.onClickDireccion}> Editar </button> </h4>
                    
                    <h4>Club: {this.state.persona.club.nombre}</h4>
                    <h4>Fecha de Nacimiento: {this.state.persona.fechaNacimiento}</h4>
                    <h4>
                        <span>Mail: </span> <span id="mail" contentEditable="false">{this.state.persona.mail} </span> 
                    <button onClick={this.onClickMail}> Editar </button> </h4>

                    <h4>Documento: {this.state.persona.tipoDocumento} {this.state.persona.numeroDocumento}</h4>
                    <h4>
                        <span>Telefono: </span> <span id="telefono" contentEditable="false">{this.state.persona.telefono} </span> 
                    <button onClick={this.onClickTelefono}> Editar </button> </h4>
                    <div class = "botonVolver">
                        <Link to={"/home"}>
                            <div>
                                <h4>VOLVER</h4>
                            </div>
                        </Link>
                    </div>
                </div>
            )
        }
        else if (this.state.rol === "Representante"){
            return (
                <div>
                        <h2>Mis Datos</h2>
                    <br/>
                    <br/>
                    <h4><span>Nombre: </span> <span id="nombre" contentEditable="false">{this.state.persona.nombre} </span> 
                    <button onClick={this.onClickNombre}> Editar </button> </h4>

                    <h4><span>Documento: </span> <span id="documento" contentEditable="false">{this.state.persona.documento} </span> 
                    <button onClick={this.onClickDocumento}> Editar </button> </h4>

                    <h4>Legajo : {this.state.persona.legajo} </h4>
                    <h4>Club: {this.state.persona.club.nombre}</h4>
                    <div class = "botonVolver">
                        <Link to={"/home"}>
                            <div>
                                <h4>VOLVER</h4>
                            </div>
                        </Link>
                    </div>

                </div>

            )
        }
        else{
            return(<div><h1>Perfil del administrador                    
                <div class = "botonVolver">
            <Link to={"/home"}>
                <div>
                    <h4>VOLVER</h4>
                </div>
            </Link>
        </div> </h1></div>)
        }
    }
    else{
        return (<div>cargando...</div>)
    }
    }
}
export default MisDatos;