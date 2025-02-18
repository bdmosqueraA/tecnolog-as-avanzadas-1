package com.trabajo;

public class Inscripcion {
    private int ano;
    private int semestre;
    private Curso curso;
    private Estudiante estudiante;

    public Inscripcion(int ano, int semestre, Curso curso, Estudiante estudiante){
        this.ano = ano;
        this.semestre = semestre;
        this.curso = curso;
        this.estudiante = estudiante;
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

    public Estudiante getEstudiante(){
        return estudiante;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "año=" + ano + '\'' +
                ", semestre=" + semestre + '\'' +
                ", curso=" + curso.getNombre() + '\'' +
                ", estudiante=" + estudiante.getNombres() +
                '}';
    }

}
