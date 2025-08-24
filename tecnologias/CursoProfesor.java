public class CursoProfesor {
    private int id;
    private Curso curso;
    private Profesor profesor;

    public CursoProfesor(int id, Curso curso, Profesor profesor) {
        this.id = id;
        this.curso = curso;
        this.profesor = profesor;
    }

    public int getId() {
        return id;
    }

    public Curso getCurso() {
        return curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }
}