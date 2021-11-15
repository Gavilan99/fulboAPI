import React from "react";
import "../estilos/estiloPagina.css";

function filaTabla(fila, club) {
      return (
        <tr>
          <td>{club.nombre}</td>
          <td align="left">
            {club.jugadores.filter((club) => club.idClub === fila[0]).at(0).nombre}
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
          <td>{fila[10]}</td>
          <td>{fila[11]}</td>
          <td>{fila[12]}</td>
          <td>{fila[13]}</td>                         
          <td>{fila[14]}</td>             
          <td>{fila[15]}</td>
          <td>{fila[16]}</td>
          <td>{fila[17]}</td>
          <td>{fila[18]}</td>
          <td>{fila[19]}</td>
          <td>{fila[20]}</td>
          <td>{fila[21]}</td>
          <td>{fila[22]}</td>
          <td>{fila[23]}</td>
        </tr>
      );
    }