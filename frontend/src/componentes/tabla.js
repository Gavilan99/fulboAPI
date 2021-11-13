import React from "react";
import filaTabla from "./filaTabla";
import "../estilos/estiloPagina.css";

function tabla(campeonato, tabla, zona) {
  if (zona === 0) {
    return (
      <div id="tablapts">
        <div class="titulotabla">Tabla {campeonato.descripcion}</div>
        <br />
        <table id="posiciones">
          <thead>
            {/*Head fijo*/}
            <tr class="cabecera">
              <th>#</th>
              <th>Equipo</th>
              <th class="pts">PJ</th>
              <th class="pj">PG</th>
              <th class="pg">PE</th>
              <th class="pe">PP</th>
              <th class="pp">GF</th>
              <th class="gf">GC</th>
              <th class="gc">DIF</th>
              <th class="dg">Pts</th>
              <th class="prom">Prom</th>
            </tr>
          </thead>
          <tbody>
            {tabla.map((item, index) => filaTabla(item, campeonato, index + 1))}
          </tbody>
        </table>
      </div>
    );
  } else {
    return (
      <div id="tablapts">
        <div class="titulotabla">Zona {zona}</div>
        <br />
        <table id="posiciones">
          <thead>
            <tr class="cabecera">
              <th>#</th>
              <th>Equipo</th>
              <th class="pts">PJ</th>
              <th class="pj">PG</th>
              <th class="pg">PE</th>
              <th class="pe">PP</th>
              <th class="pp">GF</th>
              <th class="gf">GC</th>
              <th class="gc">DIF</th>
              <th class="dg">Pts</th>
              <th class="prom">Prom</th>
            </tr>
          </thead>
          <tbody>
            {tabla.map((item, index) => filaTabla(item, campeonato, index + 1))}
          </tbody>
        </table>
        <br/>
      </div>
    );
  }
}

export default tabla;
