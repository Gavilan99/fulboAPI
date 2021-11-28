import React from "react";
import "../estilos/estiloPagina.css";

function filaTablaJugadores(fila) {
      return (
        <tr>
          <td>{fila.idJugador}</td>                         
          <td>{fila.nombre}</td>             
          <td>{fila.tipoDocumento}</td>
          <td>{fila.numeroDocumento}</td>
          <td>{fila.fechaNacimiento}</td>
          <td>{fila.categoria}</td>
          <td>{fila.habilitado}</td>
          <td>{fila.direccion}</td>
          <td>{fila.mail}</td>
          <td>{fila.telefono}</td>
        </tr>
      );
    }

export default filaTablaJugadores