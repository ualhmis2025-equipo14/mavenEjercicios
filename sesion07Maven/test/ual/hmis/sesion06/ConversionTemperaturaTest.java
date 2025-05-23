package ual.hmis.sesion06;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ConversionTemperaturaTest {

    private final ConversionTemperatura ct = new ConversionTemperatura();

    @ParameterizedTest
    @MethodSource("casos")
    void prueba(double temp, String from, String to, double esperado) {
        double res = ct.convertTemperature(temp, from, to);
        if (Double.isNaN(esperado)) {
            assertTrue(Double.isNaN(res));
        } else {
            assertEquals(esperado, res, 1e-9);
        }
    }

    private static Stream<Arguments> casos() {
        return Stream.of(
            // conversiones válidas
            Arguments.of(0.0,      "Celsius",    "Fahrenheit", 32.0),
            Arguments.of(0.0,      "Celsius",    "Kelvin",     273.15),
            Arguments.of(25.0,     "Celsius",    "Celsius",    25.0),
            Arguments.of(32.0,     "Fahrenheit", "Celsius",     0.0),
            Arguments.of(32.0,     "Fahrenheit", "Kelvin",    273.15),
            Arguments.of(100.0,    "Fahrenheit", "Fahrenheit",100.0),
            Arguments.of(273.15,   "Kelvin",     "Celsius",     0.0),
            Arguments.of(273.15,   "Kelvin",     "Fahrenheit", 32.0),
            Arguments.of(300.0,    "Kelvin",     "Kelvin",    300.0),

            // unidades no válidas (default principal + sub‑default)
            Arguments.of(0.0,      "Rankine",    "Celsius",   Double.NaN),
            Arguments.of(0.0,      "Celsius",    "Rankine",   Double.NaN),
            Arguments.of(0.0,      "Fahrenheit", "Rankine",   Double.NaN),
            Arguments.of(0.0,      "Kelvin",     "Rankine",   Double.NaN),

            // nulos para cubrir la rama del if inicial
            Arguments.of(0.0,      null,         "Celsius",   Double.NaN),
            Arguments.of(0.0,      "Celsius",    null,        Double.NaN)
        );
    }
}
