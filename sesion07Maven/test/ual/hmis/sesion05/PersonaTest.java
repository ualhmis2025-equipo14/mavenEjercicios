package ual.hmis.sesion05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

class PersonaTest {

    @ParameterizedTest
    @CsvSource({
        "Laura, Femenino, 2000-01-01",
        "Carlos, Masculino, 1995-05-10"
    })
    void testCrearPersonaValida(String nombre, String genero, String fechaNacimiento) {
        EGenero generoPersona = EGenero.valueOf(genero);
        LocalDate fechaNacimientoDate = LocalDate.parse(fechaNacimiento);
        Persona persona = new Persona(nombre, generoPersona, fechaNacimientoDate);

        assertEquals(nombre, persona.getNombre());
        assertEquals(genero, persona.getGenero());
        assertEquals(fechaNacimientoDate, persona.getFechaNacimiento());
        assertTrue(persona.calcularEdad() >= 0); // Solo comprobamos que calcula edad sin errores
    }

    @ParameterizedTest
    @CsvSource({
        "'', Femenino, 2000-01-01",  // Nombre vacío
        "null, Femenino, 2000-01-01" // Nombre null
    })
    void testNombreInvalido(String nombre, String genero, String fechaNacimiento) {
        EGenero generoPersona = EGenero.valueOf(genero);
        LocalDate fechaNacimientoDate = LocalDate.parse(fechaNacimiento);

        if ("null".equals(nombre)) {
            assertThrows(IllegalArgumentException.class, () ->
                new Persona(null, generoPersona, fechaNacimientoDate));
        } else {
            assertThrows(IllegalArgumentException.class, () ->
                new Persona(nombre, generoPersona, fechaNacimientoDate));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "Laura, Femenino, null"
    })
    void testFechaNacimientoNula(String nombre, String genero, String fechaNacimiento) {
        EGenero generoPersona = EGenero.valueOf(genero);
        assertThrows(IllegalArgumentException.class, () ->
            new Persona(nombre, generoPersona, null));
    }
}
