package com.trabajo;

public class Main {
    public static void main(String[] args) {
        Persona decano = new Persona(2.0, "Julian", "Perez", "julip@gmail.com");
        Facultad facultad1 = new Facultad(1.0, "Ciencias basicas", decano);
        Programa programa1 = new Programa(1.0, "Ingenieria Sistemas", facultad1, 10.0, "18/2/2025");
        Estudiante estudiante1 = new Estudiante(1.0, "Jose", "Zapata", "jose@gmail.com", 12.0, programa1, true, 4.3);

        
    }
}