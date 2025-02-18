package com.trabajo;

public class CursoProfesor {
    private Profesor profesor;
    private int ano;
    private int semestre;
    private Curso curso;

    public CursoProfesor(Profesor profesor, int ano, int semestre, Curso curso){
        this.profesor = profesor;
        this.ano = ano;
        this.semestre = semestre;
        this.curso = curso;
    }

    public Profesor getProfesor(){
        return profesor;
    }

    public int getAno(){
        return ano;
    }

    public int getSemestre(){
        return semestre;
    }

    public Curso getCurso(){
        return curso;
    }

    public void setProfesor(Profesor profesor){
        this.profesor = profesor;
    }

    public void setAno(int ano){
        this.ano = ano;
    }

    public void setSemestre(int semestre){
        this.semestre = semestre;
    }

    public void setCurso(Curso curso){
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "CursoProfesor{" +
                "Profesor='" + profesor.getNombres() + '\'' +
                ", Año='" + ano + '\'' +
                ", Semestre=" + semestre + '\'' +
                ", Curso=" + curso.getNombre() +
                '}';
    }
}
