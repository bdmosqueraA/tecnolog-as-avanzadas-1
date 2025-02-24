package com.trabajo;


public class Curso {
    private int idCurso;
    private String nombre;
    private Programa programa;
    private boolean activo;

    public Curso(int idCurso, String nombre, Programa programa, boolean activo ) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.programa = programa;
        this.activo = activo;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public Programa getPrograma() {
        return programa;
    }

    public boolean getActivo(){
        return activo;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idCurso='" + idCurso +
                ", nombre='" + nombre + '\'' +
                ", programa=" + programa.getNombre() +
                "estado activo=" + activo +
                '}';
    }
}
