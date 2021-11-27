import {Link} from "react-router-dom";

function mostrarPartido(partido, index, user) {
  const {id, rol} = user.idRol.split("-")
  if (index % 2 === 0) {
    return (
      <tr class="fpar">
        {console.log(partido)}
        {console.log(index)}
        <td class="equipoL">{partido.clubLocal.nombre}</td>
        <td class="golesL">{partido.golesLocal}</td>
        <td class="golesV">{partido.golesVisitante}</td>
        <td class="equipoV">{partido.clubVisitante.nombre}</td>
        <td class="accesodatos">
            <Link to={"/partido/" + partido.idPartido}>
                <i class="fas fa-info-circle"></i>
            </Link>
        </td>
        <td class="accesogoles">
            <Link to={"/goles/" + partido.idPartido}>
              <i class="fa-regular fa-futbol"></i>
            </Link>
        </td>
        <td class="accesoFaltas">
            <Link to={"/faltas/" + partido.idPartido + "-" + partido.campeonato}>
            <i class="far fa-hand-paper"></i>
            </Link>
        </td>
        <td class="accesoCargaMiembro">
            <Link to={"/cargaMiembros/" + partido.idPartido}>
            <i class="fas fa-user-plus"></i>
            </Link>
        </td>
      </tr>
    );
  } else {
      return(
    <tr class="fimpar">
      {console.log(partido)}
      {console.log(index)}
      <td class="equipoL">{partido.clubLocal.nombre}</td>
      <td class="golesL">{partido.golesLocal}</td>
      <td class="golesV">{partido.golesVisitante}</td>
      <td class="equipoV">{partido.clubVisitante.nombre}</td>
      <td class="accesodatos">
            <Link to={"/partido/" + partido.idPartido}>
                <i class="fas fa-info-circle"></i>
            </Link>
      </td>
      <td class="accesogoles">
            <Link to={"/goles/" + partido.idPartido}>
              <i class="fa-regular fa-futbol"></i>
            </Link>
        </td>
        <td class="accesoFaltas">
            <Link to={"/faltas/" + partido.idPartido + "-"+ partido.campeonato}>
            <i class="far fa-hand-paper"></i>
            </Link>
        </td>
        <td class="accesoCargaMiembro">
            <Link to={"/cargaMiembros/" + partido.idPartido}>
            <i class="fas fa-user-plus"></i>
            </Link>
        </td>
    </tr>
      );
  }
}

export default mostrarPartido;
