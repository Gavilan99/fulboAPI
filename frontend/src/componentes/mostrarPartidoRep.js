import {Link} from "react-router-dom";

function mostrarPartidoRep(partido, index, representante) {
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
        {/*REPRESENTANTE DE ALGUNO DE LOS EQUIPOS, SI EL PARTIDO NO ESTA TERMINADO*/}
        {partido.terminado === 'N' && (representante.club.idClub === partido.clubLocal.idClub || representante.club.idClub === partido.clubVisitante.idClub) ? (
          <td class="accesoCargaMiembro">
            <Link to={"/cargaMiembros/" + partido.idPartido}>
              <i class="fas fa-user-plus"></i>
            </Link>
          </td>
        ) : (<td></td>)}
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
        {/*REPRESENTANTE DE ALGUNO DE LOS EQUIPOS, SI EL PARTIDO NO ESTA TERMINADO*/}
        {partido.terminado === 'N' && (representante.club.idClub === partido.clubLocal.idClub || representante.club.idClub === partido.clubVisitante.idClub) ? (
          <td class="accesoCargaMiembro">
            <Link to={"/cargaMiembros/" + partido.idPartido}>
              <i class="fas fa-user-plus"></i>
            </Link>
          </td>
        ) : (<td></td>)}
    </tr>
      );
  }
}

export default mostrarPartidoRep;