package com.trabajo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InscripcionesPersonas {
    
    private List<Persona> personas;
    
    private static final String nombreArchivo = "personas.txt";

    public InscripcionesPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void inscribirPersona(Persona persona) {
        if (!personas.contains(persona)) {
            personas.add(persona);
            guardarInformacion(persona);
        }
    }
    
    public void eliminarPersona(Persona persona) {
        if (personas.remove(persona)) {
            guardarTodo();
        }
    }

    public void actualizarPersona(Persona persona) {
        int index = personas.indexOf(persona);
        if (index != -1) {
            personas.set(index, persona);
            guardarTodo();
        }
    }

    public void guardarInformacion(Persona persona) {
        guardarTodo();
    }

    public void cargarDatos() {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            personas.clear();
            while ((linea = reader.readLine()) != null) {
                String[] parts = linea.split(";");
                if (parts.length >= 3) {
                    Persona p = new Persona(Double.parseDouble(parts[0]), parts[1], parts[2], parts[3]);
                    personas.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarTodo() {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            for (Persona p : personas) {
                writer.write(p.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
