import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CursosInscritos implements Servicio {
    private List<Inscripcion> lista = new ArrayList<>();
    private Connection conn;

    public CursosInscritos() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/cursos_inscritos", "sa", "");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS INSCRIPCION (id INT PRIMARY KEY, estudianteId INT, cursoProfesorId INT, nota DOUBLE)");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inscribirCurso(Inscripcion inscripcion) {
        lista.add(inscripcion);
        guardarInformacion(inscripcion);
    }

    public void eliminar(Inscripcion inscripcion) {
        lista.remove(inscripcion);
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM INSCRIPCION WHERE id = ?");
            pstmt.setInt(1, inscripcion.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Inscripcion inscripcion) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE INSCRIPCION SET nota = ? WHERE id = ?");
            pstmt.setDouble(1, inscripcion.getNota());
            pstmt.setInt(2, inscripcion.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Update in list as well
        for (Inscripcion i : lista) {
            if (i.getId() == inscripcion.getId()) {
                i.setNota(inscripcion.getNota());
                break;
            }
        }
    }

    public void guardarInformacion(Inscripcion inscripcion) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                "MERGE INTO INSCRIPCION (id, estudianteId, cursoProfesorId, nota) VALUES (?, ?, ?, ?)"
            );
            pstmt.setInt(1, inscripcion.getId());
            pstmt.setInt(2, inscripcion.getEstudiante().getId());
            pstmt.setInt(3, inscripcion.getCursoProfesor().getId());
            pstmt.setDouble(4, inscripcion.getNota());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarDatos() {
        // Note: For this method, we overload it in main with maps, but for interface, empty
    }

    public void cargarDatos(Map<Integer, Estudiante> estudiantesMap, Map<Integer, CursoProfesor> cursoProfesoresMap) {
        lista.clear();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM INSCRIPCION");
            while (rs.next()) {
                int id = rs.getInt("id");
                int estudianteId = rs.getInt("estudianteId");
                int cursoProfesorId = rs.getInt("cursoProfesorId");
                double nota = rs.getDouble("nota");
                Estudiante estudiante = estudiantesMap.get(estudianteId);
                CursoProfesor cursoProfesor = cursoProfesoresMap.get(cursoProfesorId);
                if (estudiante != null && cursoProfesor != null) {
                    Inscripcion inscripcion = new Inscripcion(id, estudiante, cursoProfesor, nota);
                    lista.add(inscripcion);
                } else {
                    System.out.println("Advertencia: No se pudo cargar inscripcion ID " + id + " (objetos no encontrados)");
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Inscripcion> getLista() {
        return lista;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CursosInscritos:\n");
        for (Inscripcion i : lista) {
            sb.append("ID: ").append(i.getId()).append(", Estudiante: ").append(i.getEstudiante().getNombre())
              .append(", Curso: ").append(i.getCursoProfesor().getCurso().getNombre())
              .append(", Profesor: ").append(i.getCursoProfesor().getProfesor().getNombre())
              .append(", Nota: ").append(i.getNota()).append("\n");
        }
        return sb.toString();
    }
}