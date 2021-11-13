import React, {useState} from "react";
import { Switch, Route, Link, Router } from "react-router-dom";
import "./estilos/estiloPagina.css";
import Campeonato from "./componentes/campeonato";
import Home from "./componentes/home.js"

const App = props => {

  const initialUserState = {
    usuario: "",
    contraseña: "",
    log: false
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
    } else {
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

export default App;
