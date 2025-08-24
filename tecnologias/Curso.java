public class Curso {
    private int id;
    private String nombre;
    private Programa programa;

    public Curso(int id, String nombre, Programa programa) {
        this.id = id;
        this.nombre = nombre;
        this.programa = programa;
    }

    public Curso(int id, String nombre) {  // Overload for simplicity
        this(id, nombre, null);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Programa getPrograma() {
        return programa;
    }
}