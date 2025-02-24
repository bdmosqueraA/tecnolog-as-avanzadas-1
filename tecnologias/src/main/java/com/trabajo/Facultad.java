package com.trabajo;

public class Facultad {
    private Double idFacultad;
    private String nombre;
    private Persona decano;

    public Facultad(Double idFacultad, String nombre, Persona decano){
        this.idFacultad = idFacultad;
        this.nombre = nombre;
        this.decano = decano;
    }

    public Double getIdFacultad(){
        return idFacultad;
    }

    public String getNombre(){
        return nombre;
    }

    public Persona getDecano(){
        return decano;
    }

    public void setIdFacultad(Double idFacultad){
        this.idFacultad = idFacultad;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setDecano(Persona decano){
        this.decano = decano;
    }

    @Override
    public String toString() {
        return "Facultad{" +
                "idFacultad='" + idFacultad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", decano=" + decano.getNombres() +
                '}';
    }
}
