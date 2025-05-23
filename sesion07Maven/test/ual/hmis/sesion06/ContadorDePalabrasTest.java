package ual.hmis.sesion06;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ContadorDePalabrasTest {

    private static final Path INPUT = Path.of("datos", "entrada.txt");

    @BeforeEach
    void setup() throws IOException {
        Files.createDirectories(INPUT.getParent());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(INPUT);
    }

    @ParameterizedTest
    @MethodSource("casos")
    void prueba(String contenido, List<String> alfa, List<String> freq) throws Exception {
        if (contenido != null) Files.writeString(INPUT, contenido);
        ContadorDePalabras cp = new ContadorDePalabras();
        assertEquals(alfa,  cp.palabrasOrdenAlfabetico());
        assertEquals(freq, cp.palabrasOrdenPorFrecuencia());
    }

    private static Stream<Arguments> casos() {
        return Stream.of(
                // fichero inexistente
                Arguments.of(null,
                             List.of(),
                             List.of()),
                // fichero vacío
                Arguments.of("",
                             List.of(),
                             List.of()),
                // palabra duplicada
                Arguments.of("Hola hola",
                             List.of("hola", "hola"),
                             List.of("hola")),
                // duplicados + empate de frecuencia
                Arguments.of("a, b; a; c c ",
                             List.of("a", "a", "b", "c", "c"),
                             List.of("a", "c", "b")),
                // espacios iniciales para cubrir t.isBlank()
                Arguments.of("   A",
                             List.of("a"),
                             List.of("a"))
        );
    }
}
