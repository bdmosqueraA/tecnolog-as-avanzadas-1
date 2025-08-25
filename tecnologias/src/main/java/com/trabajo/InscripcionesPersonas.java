package com.trabajo;

import java.util.ArrayList;
import java.util.List;

public class InscripcionesPersonas {
    private List<Persona> personas;

    public InscripcionesPersonas(List<Persona> personas){
        personas = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InscripcionesPersonas:\n");
        for (Persona pe : personas) {
            sb.append("ID: ").append(pe.getId()).append(", Nombre: ").append(pe.getNombre()).append(", Apellidos: ")
                .append(pe.getApellidos()).append(", email: ").append(pe.getEmail()).append("\n");
        }
        return sb.toString();
    }
}