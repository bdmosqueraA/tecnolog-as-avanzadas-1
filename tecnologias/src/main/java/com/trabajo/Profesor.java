package com.trabajo;

public class Profesor extends Persona {
    
    private String tipoContrato;

    public Profesor(Double id, String nombre, String apellido, String email, String tipoContrato) {
        super(id, nombre, apellido, email);
        this.tipoContrato = tipoContrato;
    }

    public String getTipoContrato(){
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "tipoContrato='" + getTipoContrato() +
                '}';
    }
}
