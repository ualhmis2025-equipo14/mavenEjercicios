package ual.hmis.sesion05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class GestorTorneosTest {

    @ParameterizedTest
    @CsvSource({
        "Liga Primavera, Futbol, Juvenil, Masculino, Liga",
        "Copa Escolar, Baloncesto, Infantil, Femenino, Copa"
    })
    void testCrearYObtenerTorneos(String nombre, String deporte, String categoria, String modalidad, String tipo) {
        GestorTorneos gestor = new GestorTorneos();

        EDeporte deporteEnum = EDeporte.valueOf(deporte);
        ECategoriaEquipo categoriaEnum = ECategoriaEquipo.valueOf(categoria);

        gestor.crearTorneo(nombre, deporteEnum, categoriaEnum, modalidad, tipo);

        List<Torneo> torneos = gestor.getTorneos();

        assertEquals(1, torneos.size());
        Torneo torneo = torneos.get(0);
        assertEquals(nombre, torneo.getNombre());
        assertEquals(deporteEnum.toString(), torneo.getDeporte());
        assertEquals(modalidad, torneo.getModalidad());
        assertEquals(tipo, torneo.getTipo());
    }
}
