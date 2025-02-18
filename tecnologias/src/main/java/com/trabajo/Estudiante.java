package com.trabajo;

public class Estudiante extends Persona {
    private Double codigoEstudiante;
    private Programa programa;
    private boolean activo; 
    private Double promedio;

    public Estudiante(Double id, String nombre, String apellido, String email, Double codigoEstudiante, Programa programa,boolean activo, Double promedio) {
        super(id, nombre, apellido, email);
        this.codigoEstudiante = codigoEstudiante;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public Double getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public Programa getPrograma(){
        return programa;
    }

    public boolean getActivo(){
        return activo;
    }

    public Double getPromedio(){
        return promedio;
    }

    public void setCodigoEstudiante(Double codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public void setPrograma(Programa programa){
        this.programa = programa;
    }

    public void setActivo(boolean activo){
        this.activo = activo;
    }

    public void setPromedio(Double promedio){
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "codigoEstudiante='" + codigoEstudiante + '\'' +
                ", programa='" + programa.getNombre() + '\'' +
                ", activo=" + activo + '\'' +
                ", promedio=" + promedio +
                '}';
    }
}