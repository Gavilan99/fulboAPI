import Pelota from "../estilos/football.png"
import Amarilla from "../estilos/yellow-card.png"
import Roja from "../estilos/red-card.png"

function mostrarDatoPartido(dato) {
    let icono;
    if (dato.tipo === "Gol") {
        icono = Pelota;
    }
    else if (dato.tipo === "Amarilla") {
        icono = Amarilla;
    }
    else if (dato.tipo === "Roja") {
        icono = Roja;
    }
    return (
        <div class="datoPartido">
            <p><img src={icono} height="25px" width="25px" alt="tipo"></img> '{dato.minuto} {dato.apellido}</p>
        </div>
    )
}

export default mostrarDatoPartido;