package ual.hmis.sesion06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MezclaLinealTest {
    
    @ParameterizedTest
    @CsvSource({
        "'1,2,3,4,5', '6,7,8,9,10', '1,2,3,4,5,6,7,8,9,10'",
        "'1,3,5', '2,4,6', '1,2,3,4,5,6'",
        "'a,c,e,g', 'b,d,f,h', 'a,b,c,d,e,f,g,h'",
        "'1', '2', '1,2'",
        "'', '', ''",
        "'1', '', '1'",
        "'', '1', '1'"
    })
    void testMezclaLineal(String listaA, String listaB, String resultado) {
        List<String> a = listaA.isEmpty() ? List.of() :
            Arrays.stream(listaA.split(",")).map(String::trim).collect(Collectors.toList());

        List<String> b = listaB.isEmpty() ? List.of() :
            Arrays.stream(listaB.split(",")).map(String::trim).collect(Collectors.toList());

        List<String> esperado = resultado.isEmpty() ? List.of() :
            Arrays.stream(resultado.split(",")).map(String::trim).collect(Collectors.toList());

        List<String> obtenido = MezclaLineal.mezclaLineal(a, b);

        assertEquals(esperado, obtenido);
    }
}
