import React from "react";
import "../estilos/estiloPagina.css";

function filaTabla(fila, campeonato, posicion) {
  if (posicion % 2 === 0) {
    return (
      <tr class="par">
        <td>{posicion}</td>
        <td align="left">
          {campeonato.inscriptos.filter((club) => club.idClub === fila[0]).at(0).nombre}
        </td>
        <td>{fila[1]}</td>                         
        <td>{fila[2]}</td>             
        <td>{fila[3]}</td>
        <td>{fila[4]}</td>
        <td>{fila[5]}</td>
        <td>{fila[6]}</td>
        <td>{fila[7]}</td>
        <td>{fila[8]}</td>
        <td>{fila[9]}</td>
      </tr>
    );
  } else {
    return (
      <tr class="impar">
        <td>{posicion}</td>
        <td align="left">
          {campeonato.inscriptos.filter((club) => club.idClub === fila[0]).at(0).nombre}
        </td>
        <td>{fila[1]}</td>
        <td>{fila[2]}</td>
        <td>{fila[3]}</td>
        <td>{fila[4]}</td>
        <td>{fila[5]}</td>
        <td>{fila[6]}</td>
        <td>{fila[7]}</td>
        <td>{fila[8]}</td>
        <td>{fila[9]}</td>
      </tr>
    );
  }
}

export default filaTabla;
