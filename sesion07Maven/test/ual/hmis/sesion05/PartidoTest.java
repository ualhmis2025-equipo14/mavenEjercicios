package ual.hmis.sesion05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

// Registro de equipos en torneos con validación de categoría y modalidad

class PartidoTest {


    @ParameterizedTest
    @CsvSource({
            "Tigres, Juvenil, Masculino",
            "Leones, Cadete, Femenino",
           
    })
    void testRegistrarResultado(String nombre, String categoria, String modalidad) {
        Entrenador entrenador1 = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 3, 10));
        Entrenador entrenador2 = new Entrenador("Ana", EGenero.Femenino, LocalDate.of(1985, 6, 20));
        ECategoriaEquipo categoriaEquipo=ECategoriaEquipo.valueOf(categoria);
        Equipo equipo1 = new Equipo(nombre, categoriaEquipo, "Masculino", entrenador1);
        Equipo equipo2 = new Equipo("Leones", ECategoriaEquipo.Juvenil, "Masculino", entrenador2);

        Partido partido = new Partido(equipo1, equipo2);
        partido.registrarResultado(2, 1);

        assertEquals(2, partido.getGolesEquipo1());
        assertEquals(1, partido.getGolesEquipo2());
    }
    @ParameterizedTest
    @CsvSource({
        "Tigres, Leones, 2, 3",
        "Panteras, Jaguares, 1, 0"
    })
    void testGettersYSetters(String nombreEquipo1, String nombreEquipo2, int goles1, int goles2) {
        // Crear entrenadores
        Entrenador entrenador1 = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 1, 1));
        Entrenador entrenador2 = new Entrenador("Laura", EGenero.Femenino, LocalDate.of(1985, 5, 5));

        // Crear equipos
        Equipo equipo1 = new Equipo(nombreEquipo1, ECategoriaEquipo.Juvenil, "Masculino", entrenador1);
        Equipo equipo2 = new Equipo(nombreEquipo2, ECategoriaEquipo.Juvenil, "Masculino", entrenador2);

        // Crear partido
        Partido partido = new Partido(equipo1, equipo2);

        // Usar setters para cambiar valores
        partido.setEquipo1(equipo1);
        partido.setEquipo2(equipo2);
        partido.setGolesEquipo1(goles1);
        partido.setGolesEquipo2(goles2);

        // Verificar getters
        assertEquals(equipo1, partido.getEquipo1());
        assertEquals(equipo2, partido.getEquipo2());
        assertEquals(goles1, partido.getGolesEquipo1());
        assertEquals(goles2, partido.getGolesEquipo2());
    }
}
