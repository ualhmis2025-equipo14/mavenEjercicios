package ual.hmis.sesion05;

import java.util.ArrayList;
import java.util.List;


class Torneo {
    private String nombre;
    private EDeporte deporte;
    private ECategoriaEquipo categoria;
    private String modalidad;
    private List<Equipo> equipos;
    private String tipo;

    public Torneo(String nombre, EDeporte deporte, ECategoriaEquipo categoria, String modalidad, String tipo) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.categoria = categoria;
        this.modalidad = modalidad;
        this.tipo = tipo;
        this.equipos = new ArrayList<>();
    }

    public void registrarEquipo(Equipo equipo) throws IllegalArgumentException {
        if (!equipo.getCategoria().equals(this.categoria.toString()) || !equipo.getModalidad().equals(this.modalidad.toString())) {
            throw new IllegalArgumentException("El equipo no cumple con la categoría y modalidad del torneo");
        }
        if (!equipos.contains(equipo)) {
            equipos.add(equipo);
        }
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDeporte() {
		return deporte.toString();
	}

	public void setDeporte(EDeporte deporte) {
		this.deporte = deporte;
	}

	public String getCategoriaEquipo() {
		return categoria.toString();
	}

	public void setCategoriaEquipo(ECategoriaEquipo categoria) {
		this.categoria = categoria;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    
}
