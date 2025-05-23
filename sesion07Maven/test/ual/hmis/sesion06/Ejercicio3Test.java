package ual.hmis.sesion06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Ejercicio3Test {
    
    @CsvSource({
        "'1234', 'password demasiado corto'",        // longitud < 5
        "'12345678', '********'",                    // longitud entre 5 y 8 (inclusive)
        "'123456789', '*********'",                  // longitud 9
        "'1234567890', '**********'",                // longitud 10
        "'12345678901', '***********'",              // longitud 11
        "'123456789012', '************'",            // longitud 12
        "'1234567890123456789012345678901234567', '************'",  // longitud entre 12 y 40 (inclusive)
        "'12345678901234567890123456789012345678901', 'password demasiado largo'"  // longitud > 40
    })
    @ParameterizedTest(name = "{index} => Con password ({0}) sale {1}")
    void testEnmascarar_parametrized(String password, String result) {
        // Arrange
        Ejercicio3 e3 = new Ejercicio3();
        // Act
        // Assert
        assertEquals(result, e3.enmascarar(password));
    }
}
