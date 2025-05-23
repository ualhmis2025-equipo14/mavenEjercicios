package ual.hmis.sesion06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public final class ContadorDePalabras {

    private static final Path INPUT = Paths.get("datos", "entrada.txt");

    private final List<String> palabras = new ArrayList<>();
    private final Map<String, Integer> conteo = new HashMap<>();

    public ContadorDePalabras() throws IOException {
        if (!Files.exists(INPUT)) return;
        try (Stream<String> lineas = Files.lines(INPUT, StandardCharsets.UTF_8)) {
            lineas.forEach(this::procesar);
        }
    }

    private void procesar(String l) {
        for (String t : l.toLowerCase(Locale.ROOT).split("[^\\p{Alnum}]+")) {
            if (t.isBlank()) continue;
            palabras.add(t);
            conteo.merge(t, 1, Integer::sum);
        }
    }

    public List<String> palabrasOrdenAlfabetico() {
        List<String> c = new ArrayList<>(palabras);
        c.sort(Comparator.naturalOrder());
        return c;
    }

    public List<String> palabrasOrdenPorFrecuencia() {
        List<String> u = new ArrayList<>(conteo.keySet());
        u.sort(Comparator.<String>comparingInt(conteo::get).reversed()
                         .thenComparing(Comparator.naturalOrder()));
        return u;
    }
}
