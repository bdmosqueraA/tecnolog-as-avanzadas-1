package com.trabajo;

import java.util.ArrayList;
import java.util.List;

public class CursosInscritos implements Servicios {
    private List<Inscripcion> inscripciones = new ArrayList<>();

    @Override
    public void inscribir(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
        System.out.println("Inscripción realizada: " + inscripcion.getId());
    }

    @Override
    public void eliminar(Inscripcion inscripcion) {
        inscripciones.removeIf(i -> i.getId().equals(inscripcion.getId()));
        System.out.println("Inscripción eliminada: " + inscripcion.getId());
    }

    @Override
    public void actualizar(Inscripcion inscripcion) {
        eliminar(inscripcion);
        inscribir(inscripcion);
        System.out.println("Inscripción actualizada: " + inscripcion.getId());
    }

    @Override
    public void guardarInformacion() {
        // Lógica para guardar en archivo/BD
        System.out.println("Datos de inscripciones guardados");
    }

    @Override
    public List<Inscripcion> cargarDatos() {
        // Lógica para cargar desde archivo/BD
        System.out.println("Datos de inscripciones cargados");
        return new ArrayList<>(inscripciones);
    }
}