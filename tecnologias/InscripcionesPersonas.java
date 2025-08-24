import java.util.ArrayList;
import java.util.List;

public class InscripcionesPersonas implements Servicio {
    private List<CursoProfesor> lista = new ArrayList<>();

    public void inscribir(CursoProfesor cursoProfesor) {
        lista.add(cursoProfesor);
    }

    public void guardarInformacion(CursoProfesor cursoProfesor) {
        if (!lista.contains(cursoProfesor)) {
            lista.add(cursoProfesor);
        }
        // For in-memory, no further action
    }

    @Override
    public void cargarDatos() {
        // In-memory, no load needed
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InscripcionesPersonas:\n");
        for (CursoProfesor cp : lista) {
            sb.append("ID: ").append(cp.getId()).append(", Curso: ").append(cp.getCurso().getNombre())
              .append(", Profesor: ").append(cp.getProfesor().getNombre()).append("\n");
        }
        return sb.toString();
    }
}