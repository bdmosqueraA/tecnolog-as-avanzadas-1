package com.trabajo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CursosInscritos implements Servicios {
    private List<Inscripcion> inscripciones;

    public CursosInscritos(List<Inscripcion> inscripciones){
        this.inscripciones = new ArrayList<>(inscripciones);
    }

    public void inscribirCurso(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
        System.out.println("Inscripción realizada: " + inscripcion.toString());
    }

    public void eliminar(Inscripcion inscripcion) {
        inscripciones.removeIf(i -> 
            i.getEstudiante().equals(inscripcion.getEstudiante()) &&
            i.getCurso().equals(inscripcion.getCurso()) &&
            i.getAno() == inscripcion.getAno() &&
            i.getSemestre() == inscripcion.getSemestre()
        );
        System.out.println("Inscripción eliminada");
    }

    public void actualizar(Inscripcion inscripcion) {
        eliminar(inscripcion);
        inscribirCurso(inscripcion);
        System.out.println("Inscripción actualizada: " + inscripcion);
    }

    public void guardarInformacion() {
        try (BufferedWriter escritura = new BufferedWriter(new FileWriter("inscripciones.txt"))) {
            for (Inscripcion inscripcion : inscripciones) {
                escritura.write(inscripcion.getEstudiante().getNombres() + "," +
                             inscripcion.getCurso().getNombre() + "," +
                             inscripcion.getAno() + "," +
                             inscripcion.getSemestre());
                escritura.newLine();
            }
            System.out.println("Datos de inscripciones guardados en inscripciones.txt");
        } catch (IOException e) {
            System.err.println("Error al guardar la información: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Inscripcion inscripcion : inscripciones) {
            sb.append(inscripcion.toString()).append("\n");
        }

        return sb.toString();
    }

    public void cargarDatos() {
        inscripciones.clear();

        try (BufferedReader lector = new BufferedReader(new FileReader("inscripciones.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 10) { 
                    System.err.println("Error en formato de línea: " + linea);
                    continue;
                }
    
                String nombreEstudiante = datos[0].trim();
                String apellidoEstudiante = datos[1].trim();
                Double idEstudiante = Double.parseDouble(datos[2].trim());
                String emailEstudiante = datos[3].trim();
                Double idPrograma = Double.parseDouble(datos[4].trim());
                String nombrePrograma = datos[5].trim();
                String nombreFacultad = datos[6].trim();
                String nombreCurso = datos[7].trim();
                int ano = Integer.parseInt(datos[8].trim());
                int semestre = Integer.parseInt(datos[9].trim());
    
                Facultad facultad = new Facultad(idPrograma, nombreFacultad, null);
                Programa programa = new Programa(idPrograma, nombrePrograma, facultad, 5.0, "2025-02-02");
                Estudiante estudiante = new Estudiante(idEstudiante, nombreEstudiante, apellidoEstudiante, emailEstudiante, idEstudiante, programa, true, 4.0);
                Curso curso = new Curso(idPrograma.intValue(), nombreCurso, programa, true);
    
                Inscripcion inscripcion = new Inscripcion(ano, semestre, curso, estudiante);
                inscripciones.add(inscripcion);
            }
            System.out.println("Datos de inscripciones cargados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo inscripciones.txt: " + e.getMessage());
        }
    }

    @Override
    public String imprimirPosicion(String posicion) {
        try {
            int codigoPosicion = Integer.parseInt(posicion);
            if (codigoPosicion >= 0 && codigoPosicion < inscripciones.size()) {
                return inscripciones.get(codigoPosicion).toString();
            } else {
                return "Posicion fuera de rango.";
            }
        } catch (NumberFormatException e) {
            return "Error: La posición debe ser un numero  valido.";
        }
    }

    @Override
    public int cantidadActual(){
        return inscripciones.size();
    }

    @Override
    public List<String> imprimirListado(){
        List<String> listado = new ArrayList<>();
        for (Inscripcion inscripcion : inscripciones) {
            listado.add(inscripcion.toString());
        }
        return listado;
    }
}