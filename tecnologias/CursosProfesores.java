import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicio {
    private List<Inscripcion> lista = new ArrayList<>();

    public void inscribirCurso(Inscripcion inscripcion) {
        lista.add(inscripcion);
    }

    public void eliminar(Inscripcion inscripcion) {
        lista.remove(inscripcion);
    }

    public void actualizar(Inscripcion inscripcion) {
        for (Inscripcion i : lista) {
            if (i.getId() == inscripcion.getId()) {
                i.setNota(inscripcion.getNota());
                break;
            }
        }
    }

    public void guardarInformacion(Inscripcion inscripcion) {
        actualizar(inscripcion);
        if (!lista.contains(inscripcion)) {
            lista.add(inscripcion);
        }
    }

    @Override
    public void cargarDatos() {
        // In-memory, no load needed
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CursosProfesores:\n");
        for (Inscripcion i : lista) {
            sb.append("ID: ").append(i.getId()).append(", Estudiante: ").append(i.getEstudiante().getNombre())
              .append(", Curso: ").append(i.getCursoProfesor().getCurso().getNombre())
              .append(", Nota: ").append(i.getNota()).append("\n");
        }
        return sb.toString();
    }

    // Getter for lista (used in Main for elimination/update)
    public List<Inscripcion> getLista() {
        return lista;
    }
}