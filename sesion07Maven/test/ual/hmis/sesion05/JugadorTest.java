package ual.hmis.sesion05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

// Restricciones en los equipos (jugadores de la misma categoría y modalidad)

class JugadorTest {

    @ParameterizedTest
    @CsvSource({
            "Carlos, Masculino, 2015-05-10", // Infantil
            "Luis, Masculino, 2011-03-15", // Cadete
            "Ana, Femenino, 2008-08-22", // Juvenil
            "Pedro, Masculino, 2006-01-30", // Junior
            "Marta, Femenino, 1998-06-05" // Absoluta
    })
    void testCategoriaPorEdad(String nombre, String genero, String fechaNacimiento) {
        EGenero generoJugador = EGenero.valueOf(genero);
        LocalDate fechaNacimientoDate = LocalDate.parse(fechaNacimiento);
        Jugador jugador = new Jugador(nombre, generoJugador, fechaNacimientoDate);
       
        assertEquals(jugador.getCategoria(), getCategoriaPorEdad(jugador));

    }

    @ParameterizedTest
    @CsvSource({
            ", Masculino, 2010-01-01", // Infantil
            "Pedro, Masculino,null" // Junior
    })
    void testCreacionJugadorInvalido(String nombre, String genero, String fechaNacimiento) {
        EGenero generoJugador = EGenero.valueOf(genero);
        
        LocalDate fechaNacimientoDate;
       if(!"null".equals(fechaNacimiento)){
            fechaNacimientoDate = LocalDate.parse(fechaNacimiento);
             assertThrows(IllegalArgumentException.class, () -> new Jugador(nombre, generoJugador, fechaNacimientoDate));
        }else{
            assertThrows(IllegalArgumentException.class, () -> new Jugador(nombre, generoJugador, null));
        }
        
       
    }

//-----------------------METODOS AUXILIARES PARA REALIZAR LAS PRUEBAS

private static String getCategoriaPorEdad(Jugador jugador){
    int edad=jugador.calcularEdad();
    if (edad < 12) return "Infantil";
    else if (edad < 15) return "Cadete";
    else if (edad < 18) return "Juvenil";
    else if (edad < 21) return "Junior";
    else return "Absoluto";
    
}


}
