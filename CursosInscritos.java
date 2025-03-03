package com.trabajo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursosInscritos implements Servicios {
    private List<Inscripcion> inscripciones = new ArrayList<>();
    private Connection connection;

    public CursosInscritos() {
        conectarBD();
    }

    private void conectarBD() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistema_cursos",
                "usuario",
                "contraseña"
            );
        } catch (SQLException e) {
            System.err.println("Error conectando a BD: " + e.getMessage());
        }
    }

    // CRUD
    public void inscribirCurso(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripciones (estudiante_id, curso_id, ano, semestre) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, inscripcion.getEstudiante().getId());
            stmt.setInt(2, inscripcion.getCurso().getId());
            stmt.setInt(3, inscripcion.getAno());
            stmt.setInt(4, inscripcion.getSemestre());
            stmt.executeUpdate();
            inscripciones.add(inscripcion);
        } catch (SQLException e) {
            System.err.println("Error insertando inscripción: " + e.getMessage());
        }
    }

    public void eliminar(Inscripcion inscripcion) {
        String sql = "DELETE FROM inscripciones WHERE estudiante_id = ? AND curso_id = ? AND ano = ? AND semestre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, inscripcion.getEstudiante().getId());
            stmt.setInt(2, inscripcion.getCurso().getId());
            stmt.setInt(3, inscripcion.getAno());
            stmt.setInt(4, inscripcion.getSemestre());
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                inscripciones.remove(inscripcion);
            }
        } catch (SQLException e) {
            System.err.println("Error eliminando inscripción: " + e.getMessage());
        }
    }

    // Métodos de la interfaz Servicios
    @Override
    public String imprimirPosicion(String posicion) {
        try {
            int index = Integer.parseInt(posicion);
            if (index >= 0 && index < inscripciones.size()) {
                return inscripciones.get(index).toString();
            }
            return "Posición inválida";
        } catch (NumberFormatException e) {
            return "La posición debe ser un número";
        }
    }

    @Override
    public int cantidadActual() {
        return inscripciones.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> listado = new ArrayList<>();
        for (Inscripcion inscripcion : inscripciones) {
            listado.add(inscripcion.toString());
        }
        return listado;
    }

    @Override
    public void guardarInformacion() {
        // Los datos se guardan automáticamente en BD
        System.out.println("Datos persistentes en MySQL");
    }

    @Override
    public void cargarDatos() {
        inscripciones.clear();
        String sql = "SELECT * FROM inscripciones " +
                   "JOIN estudiantes ON estudiante_id = estudiantes.id " +
                   "JOIN cursos ON curso_id = cursos.id " +
                   "JOIN programas ON estudiantes.programa_id = programas.id " +
                   "JOIN facultades ON programas.facultad_id = facultades.id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                // Construcción de objetos desde la BD
                Facultad facultad = new Facultad(
                    rs.getDouble("facultades.id"),
                    rs.getString("facultades.nombre"),
                    null
                );
                
                Programa programa = new Programa(
                    rs.getDouble("programas.id"),
                    rs.getString("programas.nombre"),
                    facultad,
                    rs.getDouble("programas.duracion"),
                    rs.getString("programas.fecha_creacion")
                );
                
                Estudiante estudiante = new Estudiante(
                    rs.getDouble("estudiantes.id"),
                    rs.getString("estudiantes.nombres"),
                    rs.getString("estudiantes.apellidos"),
                    rs.getString("estudiantes.email"),
                    rs.getDouble("estudiantes.programa_id"),
                    programa,
                    rs.getBoolean("estudiantes.activo"),
                    rs.getDouble("estudiantes.promedio")
                );
                
                Curso curso = new Curso(
                    rs.getInt("cursos.id"),
                    rs.getString("cursos.nombre"),
                    programa,
                    rs.getBoolean("cursos.activo")
                );
                
                Inscripcion inscripcion = new Inscripcion(
                    rs.getInt("inscripciones.ano"),
                    rs.getInt("inscripciones.semestre"),
                    curso,
                    estudiante
                );
                
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            System.err.println("Error cargando inscripciones: " + e.getMessage());
        }
    }
}