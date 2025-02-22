package com.trabajo;

import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicios {
    private List<CursoProfesor> asignaciones = new ArrayList<>();

    public void inscribir(CursoProfesor asignacion) {
        asignaciones.add(asignacion);
        System.out.println("Profesor asignado: " + asignacion.getId());
    }

    @Override
    public void inscribir(Inscripcion inscripcion) {
        throw new UnsupportedOperationException("Método no válido para esta clase");
    }

    @Override
    public void eliminar(Inscripcion inscripcion) {
        throw new UnsupportedOperationException("Método no válido para esta clase");
    }

    public void eliminar(CursoProfesor asignacion) {
        asignaciones.removeIf(a -> a.getId().equals(asignacion.getId()));
        System.out.println("Asignación eliminada: " + asignacion.getId());
    }

    @Override
    public void actualizar(Inscripcion inscripcion) {
        throw new UnsupportedOperationException("Método no válido para esta clase");
    }

    public void actualizar(CursoProfesor asignacion) {
        eliminar(asignacion);
        inscribir(asignacion);
        System.out.println("Asignación actualizada: " + asignacion.getId());
    }

    @Override
    public void guardarInformacion() {
        // Lógica para guardar en archivo/BD
        System.out.println("Datos de asignaciones guardados");
    }

    @Override
    public List<Inscripcion> cargarDatos() {
        throw new UnsupportedOperationException("Método no válido para esta clase");
    }

    public List<CursoProfesor> cargarAsignaciones() {
        // Lógica para cargar desde archivo/BD
        System.out.println("Datos de asignaciones cargados");
        return new ArrayList<>(asignaciones);
    }
}
