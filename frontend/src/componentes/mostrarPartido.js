import {Link} from "react-router-dom";

function mostrarPartido(partido, index, id) {
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
    </tr>
      );
  }
}

export default mostrarPartido;
