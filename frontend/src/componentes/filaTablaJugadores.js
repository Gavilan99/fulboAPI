import React from "react";
import "../estilos/estiloPagina.css";

function filaTabla(fila) {
      return (
        <tr>
          <td>{fila[3]}</td>                         
          <td>{fila[4]}</td>             
          <td>{fila[1]}</td>
          <td>{fila[2]}</td>
          <td>{fila[4]}</td>
          <td>{fila[6]}</td>
          <td>{fila[8]}</td>
          <td>{fila[9]}</td>
          <td>{fila[10]}</td>
          <td>{fila[11]}</td>
        </tr>
      );
    }