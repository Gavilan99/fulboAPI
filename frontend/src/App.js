import React, {useState} from "react";
import { Switch, Route, Link, Router } from "react-router-dom";
import "./estilos/estiloPagina.css";
import Campeonato from "./componentes/campeonato";
import Home from "./componentes/home.js"
import MostrarStats from "./componentes/mostrarStats";
import MisDatos from "./componentes/misDatos.js";
import EditarGolesPartido from "./componentes/editarGolesPartido";
import EditarFaltasPartido from "./componentes/editarFaltasPartido";
import EditarClub from "./componentes/editarClub";

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
      idRol: "",
      club: []
    }, logueado: false
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
   login(e) {
    e.preventDefault()
    var userDummy = this.state.user
     fetch("http://localhost:8080/login?usuario=" + this.state.user.usuario+"&contraseña=" + this.state.user.contraseña)
     .then(response => response.json()).then(datos => {
       if (datos.usuario === "invalido"){
         alert("Usuario o contraseña invalidos")
       }
       else{
         console.log(datos);
        userDummy.usuario = datos.usuario;
        userDummy.contraseña = datos.contraseña;
        userDummy.id = datos.idRol;
        userDummy.rol = datos.rol;
        userDummy.idRol = datos.idRol.toString() + "-" + datos.rol;
        userDummy.log=true;
        console.log(userDummy);
        this.setState({user: userDummy});
       }

     }).catch(e => console.log(e))
   }

   logout() {
    
    var userDummy= this.state.user
    userDummy.log=false
    userDummy.usuario=""
    userDummy.contraseña=""
    this.setState({user: userDummy})
  }
  render(){
  if (!this.state.user.log)   {
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
    else {
      console.log(this.state.user.club)
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
                <Link to={"/miClub/" + this.state.user.id} className="nav-link">
                  Mi Club
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
                  <MostrarStats {...props} user={this.state.user} />
                )}
              />
              <Route
                exact
                path="/goles/:id"
                render={(props) => (
                  <EditarGolesPartido {...props} user={this.state.user} />
                )}
              />
              <Route
                exact
                path="/faltas/:id"
                render={(props) => (
                  <EditarFaltasPartido {...props} user={this.state.user} />
                )}
              />
              {<Route
                exact
                path="/miClub/:id"
                render={(props) => 
                    (
                      <EditarClub {...props} user={this.state.user} />
                    )}
              /> }
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
