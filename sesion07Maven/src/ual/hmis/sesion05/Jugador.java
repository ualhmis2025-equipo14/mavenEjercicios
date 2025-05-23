package ual.hmis.sesion05;

import java.time.LocalDate;

class Jugador extends Persona {
    private ECategoriaJugador categoria;

    public Jugador(String nombre, EGenero genero, LocalDate fechaNacimiento) {
        super(nombre, genero, fechaNacimiento);
        this.categoria = determinarCategoria(calcularEdad());
    }

    private ECategoriaJugador determinarCategoria(int edad) {
        if (edad < 12) return ECategoriaJugador.Infantil;
        else if (edad < 15) return ECategoriaJugador.Cadete;
        else if (edad < 18) return ECategoriaJugador.Juvenil;
        else if (edad < 21) return ECategoriaJugador.Junior;
        else return ECategoriaJugador.Absoluto;
       
    }

    public String getCategoria() { return categoria.toString(); }
}
