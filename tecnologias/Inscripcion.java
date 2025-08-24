public class Inscripcion {
    private int id;
    private Estudiante estudiante;
    private CursoProfesor cursoProfesor;
    private double nota;

    public Inscripcion(int id, Estudiante estudiante, CursoProfesor cursoProfesor, double nota) {
        this.id = id;
        this.estudiante = estudiante;
        this.cursoProfesor = cursoProfesor;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public CursoProfesor getCursoProfesor() {
        return cursoProfesor;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}