package com.trabajo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicios {
    private List<CursoProfesor> asignaciones = new ArrayList<>();
    private List<Profesor> listaProfesores = new ArrayList<>();
    private List<Curso> listaCursos = new ArrayList<>();

    public void inscribir(CursoProfesor asignacion) {
        asignaciones.add(asignacion);
        System.out.println("Profesor asignado: " + asignacion.toString());
    }

    public void eliminar(CursoProfesor asignacion) {
        asignaciones.removeIf(a ->
            a.getProfesor().equals(asignacion.getProfesor()) &&
            a.getCurso().equals(asignacion.getCurso()) &&
            a.getAno() == asignacion.getAno() &&
            a.getSemestre() == asignacion.getSemestre()
        );
        System.out.println("Asignación eliminada");
    }

    public void actualizar(CursoProfesor asignacion) {
        eliminar(asignacion);
        inscribir(asignacion);
        System.out.println("Asignación actualizada: " + asignacion);
    }

    public void guardarInformacion() {
        try (BufferedWriter escritura = new BufferedWriter(new FileWriter("asignaciones.txt"))) {
            for (CursoProfesor asignacion : asignaciones) {
                escritura.write(asignacion.getProfesor().getNombres() + "," +
                             asignacion.getCurso().getNombre() + "," +
                             asignacion.getAno() + "," +
                             asignacion.getSemestre());
                escritura.newLine();
            }
            System.out.println("Asignaciones guardadas en archivo.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public void cargarDatos() {
        asignaciones.clear();
    
        try (BufferedReader lector = new BufferedReader(new FileReader("asignaciones.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 7) { 
                    System.err.println("Error en formato de línea: " + linea);
                    continue;
                }
    
                String nombreProfesor = datos[0].trim();
                String apellidoProfesor = datos[1].trim();
                Double idProfesor = Double.parseDouble(datos[2].trim());
                String emailProfesor = datos[3].trim();
                Double idCurso = Double.parseDouble(datos[4].trim());
                String nombreCurso = datos[5].trim();
                int ano = Integer.parseInt(datos[6].trim());
                int semestre = Integer.parseInt(datos[7].trim());
    
                // Buscar en memoria o crear un nuevo profesor

                Profesor profesor = new Profesor(idProfesor, nombreProfesor, apellidoProfesor, emailProfesor, "Tiempo Completo");
                listaProfesores.add(profesor);
    
                Curso curso = new Curso(idCurso.intValue(), nombreCurso, null, true);
                listaCursos.add(curso);
    
                CursoProfesor asignacion = new CursoProfesor(profesor, ano, semestre, curso);
                asignaciones.add(asignacion);
            }
            System.out.println("Datos de asignaciones cargados correctamente.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo asignaciones.txt: " + e.getMessage());
        }
    }

    @Override
    public String imprimirPosicion(String posicion) {
        try {
            int index = Integer.parseInt(posicion);
            if (index >= 0 && index < asignaciones.size()) {
                return asignaciones.get(index).toString();
            } else {
                return "Error: Posición fuera de rango. Ingrese un número entre 0 y " + (asignaciones.size() - 1);
            }
        } catch (NumberFormatException e) {
            return "Error: La posición debe ser un número entero válido.";
        }
    }

    @Override
    public int cantidadActual() {
        return asignaciones.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> listado = new ArrayList<>();
        for (CursoProfesor asignacion : asignaciones) {
            listado.add(asignacion.toString());
        }
        return listado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CursoProfesor asignacion : asignaciones) {
            sb.append(asignacion.toString()).append("\n");
        }
        return sb.toString();
    }
}
