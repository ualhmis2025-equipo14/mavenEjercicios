package ual.hmis.sesion05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




class TorneoTest {

    @ParameterizedTest
     @CsvSource({
            "Tigres, Juvenil, Masculino",
            
    })
    void testRegistrarEquipoCorrectamente(String nombre, String categoria, String modalidad) {
        Torneo torneo = new Torneo("Liga Juvenil",EDeporte.Futbol, ECategoriaEquipo.Juvenil, "Masculino", "Liga");

        Entrenador entrenador = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 3, 10));
        ECategoriaEquipo categoriaEquipo=ECategoriaEquipo.valueOf(categoria);
        Equipo equipo = new Equipo(nombre, categoriaEquipo, modalidad, entrenador);

        torneo.registrarEquipo(equipo);

        assertEquals(1, torneo.getEquipos().size());
    }

    @ParameterizedTest
    @CsvSource({
            "Tigres, Juvenil, Masculino",
            
    })
    void testNoRegistrarEquipoDeDiferenteCategoria(String nombre, String categoria, String modalidad) {
        Torneo torneo = new Torneo("Liga Juvenil", EDeporte.Futbol, ECategoriaEquipo.Cadete, "Masculino", "Liga");
         ECategoriaEquipo categoriaEquipo=ECategoriaEquipo.valueOf(categoria);
        Entrenador entrenador = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 3, 10));
        EGenero modalidadEquipo=EGenero.valueOf(modalidad);
        Equipo equipo = new Equipo(nombre,categoriaEquipo, modalidadEquipo.toString(), entrenador);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }

    @ParameterizedTest
    @CsvSource({
            "Leonas, Juvenil, Femenino",
            
    })
    void testNoRegistrarEquipoDeDiferenteModalidad(String nombre, String categoria, String modalidad) {
        Torneo torneo = new Torneo("Liga Juvenil", EDeporte.Futbol,ECategoriaEquipo.Juvenil, "Masculino", "Liga");

        Entrenador entrenador = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 3, 10));
       ECategoriaEquipo categoriaEquipo=ECategoriaEquipo.valueOf(categoria);
       EGenero modalidadEquipo=EGenero.valueOf(modalidad);
        Equipo equipo = new Equipo(nombre, categoriaEquipo, modalidadEquipo.toString(), entrenador);

        assertThrows(IllegalArgumentException.class, () -> torneo.registrarEquipo(equipo));
    }
     @ParameterizedTest
    @CsvSource({
        "Liga Escolar, Futbol, Infantil, Masculino, Liga",
        "Torneo Verano, Baloncesto, Cadete, Femenino, Copa"
    })
    void testGettersYSettersTorneo(String nombre, String deporte, String categoria, String modalidad, String tipo) {
        // Crear torneo con valores iniciales
        Torneo torneo = new Torneo("Temporal", EDeporte.Futbol, ECategoriaEquipo.Juvenil, "Mixto", "Liga");

        // Setear nuevos valores
        torneo.setNombre(nombre);
        torneo.setDeporte(EDeporte.valueOf(deporte));
        torneo.setCategoriaEquipo(ECategoriaEquipo.valueOf(categoria));
        torneo.setModalidad(modalidad);
        torneo.setTipo(tipo);

        // Crear lista de equipos simulada
        Entrenador entrenador = new Entrenador("EntrenadorX", EGenero.Masculino, LocalDate.of(1980, 1, 1));
        Equipo equipo = new Equipo("Tigres", ECategoriaEquipo.valueOf(categoria), modalidad, entrenador);
        List<Equipo> listaEquipos = new ArrayList<>();
        listaEquipos.add(equipo);
        torneo.setEquipos(listaEquipos);

        // Validaciones de los getters
        assertEquals(nombre, torneo.getNombre());
        assertEquals(deporte, torneo.getDeporte());
        assertEquals(categoria, torneo.getCategoriaEquipo());
        assertEquals(modalidad, torneo.getModalidad());
        assertEquals(tipo, torneo.getTipo());
        assertEquals(1, torneo.getEquipos().size());
        assertEquals("Tigres", torneo.getEquipos().get(0).getNombre());
    }
    @ParameterizedTest
    @CsvSource({
        "Tigres, Juvenil, Masculino"
    })
    void testNoRegistrarEquipoDuplicado(String nombre, String categoria, String modalidad) {
        Torneo torneo = new Torneo("Liga Juvenil", EDeporte.Futbol, ECategoriaEquipo.valueOf(categoria), modalidad, "Liga");

        Entrenador entrenador = new Entrenador("Carlos", EGenero.Masculino, LocalDate.of(1980, 3, 10));
        Equipo equipo = new Equipo(nombre, ECategoriaEquipo.valueOf(categoria), modalidad, entrenador);

        // Registrar una vez
        torneo.registrarEquipo(equipo);
        // Registrar otra vez el mismo equipo
        torneo.registrarEquipo(equipo);

        // Solo se debe haber registrado una vez
        assertEquals(1, torneo.getEquipos().size());
    }
}
