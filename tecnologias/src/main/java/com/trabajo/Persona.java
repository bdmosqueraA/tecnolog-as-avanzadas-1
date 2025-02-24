package com.trabajo;

public class Persona {
    private Double id;           
    private String nombres;
    private String apellidos;
    private String email;

    public Persona(Double id, String nombres, String apellidos, String email) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }

    public Double getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos=" + apellidos + '\'' +
                ", email=" + email +
                '}';
    }
}

