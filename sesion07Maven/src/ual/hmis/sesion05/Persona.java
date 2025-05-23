package ual.hmis.sesion05;

import java.time.LocalDate;


// Clase Persona para jugadores y entrenadores
class Persona {
    private String nombre;
    private EGenero genero;
    private LocalDate fechaNacimiento;

    public Persona(String nombre, EGenero genero, LocalDate fechaNacimiento) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        this.nombre = nombre;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() { return nombre; }
    public String getGenero() { return genero.toString(); }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public int calcularEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }
    
    
}
