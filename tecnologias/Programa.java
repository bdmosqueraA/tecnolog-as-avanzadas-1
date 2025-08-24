public class Programa {
    private int id;
    private String nombre;
    private Facultad facultad;

    public Programa(int id, String nombre, Facultad facultad) {
        this.id = id;
        this.nombre = nombre;
        this.facultad = facultad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Facultad getFacultad() {
        return facultad;
    }
}