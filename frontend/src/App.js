import React, {useState} from "react";
import { Switch, Route, Link, Router } from "react-router-dom";
import "./estilos/estiloPagina.css";
import Campeonato from "./componentes/campeonato";
import Home from "./componentes/home.js"
import MostrarStats from "./componentes/mostrarStats";
import MisDatos from "./componentes/misDatos.js";

class App extends React.Component {
  constructor(props){
    super(props)
    this.state ={
    user: {
      usuario: "",
      contraseña: "",
      rol: "Jugador",
      id: 0,
      log: false,
      idRol: ""
    }, cargando: false
  }

  this.handleInputChange = this.handleInputChange.bind(this)
  this.login = this.login.bind(this)
  this.logout = this.logout.bind(this)

}


   handleInputChange(e) {
    const {name, value} = e.target
    var userDummy = this.state.user
    userDummy[name]= value
    this.setState({user: userDummy})
  }



  /*crea funciones de login y logout*/
   login() {
     var userDummy = this.state.user 
     fetch("http://localhost:8080/login?usuario=" + this.state.user.usuario+"&contraseña=" + this.state.user.contraseña)
     .then(response => response.json())
     .then(result => {
       if (result.usuario ==="invalido"){
         alert("Usuario o contraseña incorrectos")
       }
       else{
       userDummy.rol = result.rol;
       userDummy.id=result.idRol;
      userDummy.usuario=result.usuario;
      userDummy.contraseña= result.contraseña;
      userDummy.idRol= result.idRol.toString() + "-" + result.rol;
      console.log(result);
      userDummy.log=true;
      this.setState({user: userDummy});this.setState({})
    }})
     .catch(e => console.log(e))

  }

   logout() {
    
    var userDummy= this.state.user
    userDummy.log=false
    userDummy.usuario=""
    userDummy.contraseña=""
    this.setState({user: userDummy})
  }


  render(){
  if (!this.state.user.log && !this.state.cargando)   {
    return (
        <div>
          <h1>Campeonato Manager</h1>

          <div id="login">
            <form>
              <div id="usuario">
                <label>Usuario: </label>
                <input
                  type="text"
                  name="usuario"
                  
                  onChange={this.handleInputChange}
                ></input>
              </div>
              <div id="contraseña">
                <label>Contraseña: </label>
                <input
                  type="text"
                  name="contraseña"
                  onChange={this.handleInputChange}
                ></input>
              </div>
              <div id="boton-login">
                <button onClick={this.login}>Login</button>
              </div>
            </form>
          </div>
        </div>
      );
    }
    else if (this.state.cargando){
      return (<div>Cargando...</div>)
    }
    else {
      console.log(this.state.user)
      return (
        <div>
          <header>
            <h1>Campeonato Manager</h1>
          </header>
          <nav className="navbar navbar-expand navbar-dark " id="navbar">
            <div className="navbar-nav mr-auto">
              <li id="nav-item">
                <Link to={"/home"} className="nav-link">
                  Home
                </Link>
              </li>

              <li id="nav-item">
                <Link to={"/misDatos/" + this.state.user.idRol} className="nav-link">
                  Mis Datos
                </Link>
              </li>

              <li id="nav-item">
                <a
                  onClick={this.logout}
                  className="nav-link"
                  style={{ cursor: "pointer" }}
                >
                  Logout {this.state.user.usuario}
                </a>
              </li>
            </div>
          </nav>

          <div>
            <Switch>
              <Route
                exact
                path={["/", "/home"]}
                render={(props) => (
                  <Home {...props} user={this.state.user} />
                )}
              />
              { <Route
                exact
                path="/misDatos/:id"
                render={(props) => 
                    (
                      <MisDatos {...props} user={this.state.user} />
                    )}
              /> }
              <Route
                exact
                path="/campeonato/:id"
                render={(props) => (
                  <Campeonato {...props} user={this.state.user} />
                )}
              />
              <Route
                exact
                path="/partido/:id"
                render={(props) => (
                  <MostrarStats {...props} user={this.stateuser} />
                )}
              />
            </Switch>
          </div>

        <footer>
          <br/>
          <p>Campeonato Manager Copyright &copy; 2021 - Grupo 7</p>
          <br/>
        </footer>
        </div>
      );
    }
  }
}

export default App;
