package com.trabajo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CursosInscritos cursosInscritos = new CursosInscritos(new ArrayList<>());
        
        Persona decano = new Persona(2.0, "Julian", "Perez", "julip@gmail.com");
        Facultad facultad1 = new Facultad(1.0, "Ciencias basicas", decano);
        Programa programa1 = new Programa(1.0, "Ingenieria Sistemas", facultad1, 10.0, "18/2/2025");
        Estudiante estudiante1 = new Estudiante(1.0, "Jose", "Zapata", "jose@gmail.com", 12.0, programa1, true, 4.3);
        Profesor profesor1 = new Profesor(1.0, "Mario", "Lopez", "mario@gmail.com", "Catedratico");
        Curso curso1 = new Curso(1, "Programacion orientada a objetos", programa1, true);

        System.out.println(profesor1.toString());
        
        Inscripcion inscripcion1 = new Inscripcion(2024, 1, curso1, estudiante1);

        cursosInscritos.inscribirCurso(inscripcion1);

        cursosInscritos.guardarInformacion();
    }
}