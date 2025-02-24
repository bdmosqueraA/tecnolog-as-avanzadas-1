package com.trabajo;

public class Programa {
    private Double idPrograma;
    private String nombre;
    private Facultad facultad;
    private Double duracion;
    private String registro;

    public Programa(Double idPrograma, String nombre, Facultad facultad, Double duracion, String registro) {
        this.idPrograma = idPrograma;
        this.nombre = nombre;
        this.facultad = facultad;
        this.duracion = duracion;
        this.registro = registro;
    }

    public Double getIdPrograma() {
        return idPrograma;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getDuracion() {
        return duracion;
    }

    public String getRegistro() {
        return registro;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    @Override
    public String toString() {
        return "Programa{" +
                "idPrograma='" + idPrograma +
                ", nombre='" + nombre + '\'' +
                ", facultad=" + facultad.getNombre() +
                ", duracion=" + duracion +
                ", registro='" + registro + '\'' +
                '}';
    }
}
