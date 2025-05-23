package ual.hmis.sesion05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class EquipoTest {

    @ParameterizedTest
    @CsvSource({
        "Leones, Infantil, Masculino",
        "Panteras, Cadete, Femenino"
    })
    void testSettersYGetters(String nombre, String categoria, String modalidad) {
        Entrenador entrenador = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 1, 1));
        Entrenador segundo = new Entrenador("Laura", EGenero.Femenino, LocalDate.of(1985, 5, 5));
        Equipo equipo = new Equipo("Temporal", ECategoriaEquipo.Juvenil, "Mixto", entrenador);

        equipo.setNombre(nombre);
        equipo.setModalidad(modalidad);
        equipo.setCategoria(ECategoriaEquipo.valueOf(categoria));
        equipo.setEntrenador(entrenador);
        equipo.setSegundoEntrenador(segundo);
        List<Jugador> lista = new ArrayList<>();
        equipo.setJugadores(lista);

        assertEquals(nombre, equipo.getNombre());
        assertEquals(modalidad, equipo.getModalidad());
        assertEquals(categoria, equipo.getCategoria());
        assertEquals(entrenador, equipo.getEntrenador());
        assertEquals(segundo, equipo.getSegundoEntrenador());
        assertEquals(lista, equipo.getJugadores());
    }

    @Test
    void testConstructorInvalido() {
        Entrenador entrenador = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Equipo("", ECategoriaEquipo.Juvenil, "Masculino", entrenador));
        assertThrows(IllegalArgumentException.class, () -> new Equipo(null, ECategoriaEquipo.Juvenil, "Masculino", entrenador));
        assertThrows(IllegalArgumentException.class, () -> new Equipo("Tigres", null, "Masculino", entrenador));
        assertThrows(IllegalArgumentException.class, () -> new Equipo("Tigres", ECategoriaEquipo.Juvenil, null, entrenador));
        assertThrows(IllegalArgumentException.class, () -> new Equipo("Tigres", ECategoriaEquipo.Juvenil, "Masculino", null));
    }

    @ParameterizedTest
    @CsvSource({
        "Jose, Masculino, 2010-05-20, Juvenil, Masculino",  // ✅ Válido
        "Luis, Masculino, 2015-05-10, Juvenil, Masculino",  // ❌ Categoría diferente
        "Ana, Femenino, 2010-05-20, Juvenil, Masculino"     // ❌ Modalidad diferente
    })
    void testAgregarJugador(String nombre, String genero, String fechaNacimiento, String categoria, String modalidad) {
        EGenero generoJugador = EGenero.valueOf(genero);
        LocalDate fecha = LocalDate.parse(fechaNacimiento);
        Jugador jugador = new Jugador(nombre, generoJugador, fecha);

        Entrenador entrenador = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 1, 1));
        Equipo equipo = new Equipo("Tigres", ECategoriaEquipo.valueOf(categoria), modalidad, entrenador);

        equipo.agregarJugador(jugador);
        equipo.agregarJugador(jugador);  // Segundo intento (repetido)

        if (jugador.getCategoria().equalsIgnoreCase(categoria) && jugador.getGenero().equalsIgnoreCase(modalidad)) {
            assertEquals(1, equipo.getJugadores().size());
        } else {
            assertEquals(0, equipo.getJugadores().size());
        }
    }

    @Test
    void testAsignarSegundoEntrenador() {
        Entrenador entrenador1 = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 1, 1));
        Entrenador entrenador2 = new Entrenador("Laura", EGenero.Femenino, LocalDate.of(1985, 5, 5));
        Equipo equipo = new Equipo("Tigres", ECategoriaEquipo.Juvenil, "Masculino", entrenador1);

        equipo.asignarSegundoEntrenador(entrenador2);

        assertEquals(entrenador2, equipo.getSegundoEntrenador());
    }

    @Test
    void testEqualsYToString() {
        Entrenador entrenador = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 1, 1));
        Equipo eq1 = new Equipo("Tigres", ECategoriaEquipo.Juvenil, "Masculino", entrenador);
        Equipo eq2 = new Equipo("Tigres", ECategoriaEquipo.Juvenil, "Masculino", entrenador);
        Equipo eq3 = new Equipo("Leones", ECategoriaEquipo.Juvenil, "Masculino", entrenador);
        Equipo eq4 = new Equipo("Tigres", ECategoriaEquipo.Infantil, "Masculino", entrenador);
        Equipo eq5 = new Equipo("Tigres", ECategoriaEquipo.Juvenil, "Femenino", entrenador);

        assertEquals(eq1, eq2);
        assertNotEquals(eq1, eq3);
        assertNotEquals(eq1, eq4);
        assertNotEquals(eq1, eq5);
        assertNotEquals(eq1, null);
        assertNotEquals(eq1, "No es equipo");

        assertTrue(eq1.toString().contains("nombre=Tigres"));
        assertTrue(eq1.toString().contains("categoria=Juvenil"));
    }
}
