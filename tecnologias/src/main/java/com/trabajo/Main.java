package com.trabajo;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime fecha = LocalDateTime.now();
        // Datos de ejemplo
        Persona per1 = new Persona(1.0 , "Jose", "Alvarez", "josal@gmail.com");
        Persona per2 = new Persona(2.0, "Santiago", "Perez", "saper@gmail.com");

        Facultad fac1 = new Facultad(1.0, "Ingenieria", per1);
        Programa prog1 = new Programa(1.0, "Sistemas", 10.0, fecha, fac1);

        Profesor prof1 = new Profesor(3.0, "Juan", "Diaz", "judiaz@gmail.com", "Planta");
        Profesor prof2 = new Profesor(4.0, "Ana", "Zapata", "anazap@gmail.com", "Contrato");

        Curso curso1 = new Curso(1.0, "Matematicas", prog1, true);
        Curso curso2 = new Curso(2.0, "Fisica", prog1, true);

        CursoProfesor cp1 = new CursoProfesor(1.0, prof1, 2020, 3,curso1);
        CursoProfesor cp2 = new CursoProfesor(2.0, prof2, 2025, 5,curso2);

        Estudiante est1 = new Estudiante(5.0, "Maria", "Lopez", "marlo@gmail.com", 1235.0, prog1, true, 4.0);
        Estudiante est2 = new Estudiante(6.0, "Pedro", "Pazcal", "pepazc@gmail.com", 5678.0, prog1, false, 1.9);

        Inscripcion ins1 = new Inscripcion(1.0, curso1, 2023, 4, est1);
        Inscripcion ins2 = new Inscripcion(2.0, curso2, 2025, 10, est2);

        CursosInscritos ci = new CursosInscritos();
        ci.guardarInformacion(ins1);
        ci.guardarInformacion(ins2);
        ci.cargarDatos();

        //Datos en la consola
        System.out.println("Persona 1: " + per1.toString() + "\n");
        System.out.println("Facultad 1: " + fac1.toString() + "\n");
        System.out.println("Programa: " + prog1.toString() + "\n");
        System.out.println("Cursos Inscritos: " + ci.toString());
    }
}