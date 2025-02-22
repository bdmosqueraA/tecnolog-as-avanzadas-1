package com.trabajo;

public class Main {
    public static void main(String[] args) {
        // Crear objetos
        Estudiante est1 = new Estudiante("E001", "Ana", "ana@uni.edu", "Ingeniería");
        Profesor prof1 = new Profesor("P001", "Carlos", "carlos@uni.edu", "Matemáticas");
        Curso curso1 = new Curso("C101", "Álgebra", 4);

        // Gestionar inscripciones
        CursosInscritos gestionInscripciones = new CursosInscritos();
        Inscripcion insc1 = new Inscripcion("I001", est1, curso1, "2023-10-01");
        gestionInscripciones.inscribir(insc1);

        // Gestionar asignaciones
        CursosProfesores gestionAsignaciones = new CursosProfesores();
        CursoProfesor asign1 = new CursoProfesor("A001", prof1, curso1);
        gestionAsignaciones.inscribir(asign1);
    }
}