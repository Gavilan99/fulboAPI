import React, {useState} from "react";
import { Switch, Route, Link, Router } from "react-router-dom";
import "./estilos/estiloPagina.css";
import Campeonato from "./componentes/campeonato";
import Home from "./componentes/home.js"
import MostrarStats from "./componentes/mostrarStats";
import CrearCampeonato from "./componentes/crearCampeonato";
import CrearCampeonatoClubes from "./componentes/crearCampeonatoClubes";

const App = props => {

  const initialUserState = {
    usuario: "",
    contraseña: "",
    rol: "Administrador",
    id: 1,
    log: false,
    idRol: "1" + "-" + "Administrador"
  }

  const [user, setUser] = useState(initialUserState)

  function handleInputChange(e) {
    const {name, value} = e.target
    setUser({...user, [name]: value})
  }

  /*crea funciones de login y logout*/
  function login() {
    setUser({...user, log: true})
  }

  function logout() {
    setUser({...user, log: false})
  }

  if (!user.log) {
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
                  value={user.usuario}
                  onChange={handleInputChange}
                ></input>
              </div>
              <div id="contraseña">
                <label>Contraseña: </label>
                <input
                  type="text"
                  name="contraseña"
                  value={user.contraseña}
                  onChange={handleInputChange}
                ></input>
              </div>
              <div id="boton-login">
                <button onClick={login}>Login</button>
              </div>
            </form>
          </div>
        </div>
      );
    } else{
      if (user.rol === "Jugador") {
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
                  <Link to={"/misDatos"} className="nav-link">
                    Mis Datos
                  </Link>
                </li>

                <li id="nav-item">
                  <a
                    onClick={logout}
                    className="nav-link"
                    style={{ cursor: "pointer" }}
                  >
                    Logout {user.usuario}
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
                    <Home {...props} user={user} />
                  )}
                />
                {/* <Route
                  path="/misDatos"
                  render={(props) => (
                    <MisDatos {...props} user={this.state.user} />
                  )}
                /> */}
                <Route
                  exact
                  path="/campeonato/:id"
                  render={(props) => (
                    <Campeonato {...props} user={user} />
                  )}
                />
                <Route
                  exact
                  path="/partido/:id"
                  render={(props) => (
                    <MostrarStats {...props} user={user} />
                  )}
                />
              </Switch>
            </div>
          </div>
        );
      }
      else if (user.rol === "Administrador") {
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
                  <Link to={"/crearCampeonato"} className="nav-link">
                    Crear Campeonato
                  </Link>
                </li>

                <li id="nav-item">
                  <a
                    onClick={logout}
                    className="nav-link"
                    style={{ cursor: "pointer" }}
                  >
                    Logout {user.usuario}
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
                    <Home {...props} user={user} />
                  )}
                />
                 <Route
                  exact
                  path="/crearCampeonato"
                  render={(props) => (
                    <CrearCampeonato {...props} user={user} />
                  )}
                />
                <Route
                  exact
                  path="/crearCampeonatoClubes/:zonas"
                  render={(props) => (
                    <CrearCampeonatoClubes {...props} user={user} />
                  )}
                /> 
                <Route
                  exact
                  path="/campeonato/:id"
                  render={(props) => (
                    <Campeonato {...props} user={user} />
                  )}
                />
                <Route
                  exact
                  path="/partido/:id"
                  render={(props) => (
                    <MostrarStats {...props} user={user} />
                  )}
                />
              </Switch>
            </div>
          </div>
        );
      }
    }
}

export default App;
