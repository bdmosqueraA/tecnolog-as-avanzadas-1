package com.trabajo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicios {
    private List<CursoProfesor> asignaciones = new ArrayList<>();
    private Connection connection;

    public CursosProfesores() {
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
    public void inscribir(CursoProfesor asignacion) {
        String sql = "INSERT INTO asignaciones_cursos_profesores (profesor_id, curso_id, ano, semestre) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, asignacion.getProfesor().getId());
            stmt.setInt(2, asignacion.getCurso().getId());
            stmt.setInt(3, asignacion.getAno());
            stmt.setInt(4, asignacion.getSemestre());
            stmt.executeUpdate();
            asignaciones.add(asignacion);
        } catch (SQLException e) {
            System.err.println("Error insertando asignación: " + e.getMessage());
        }
    }

    public void eliminar(CursoProfesor asignacion) {
        String sql = "DELETE FROM asignaciones_cursos_profesores WHERE profesor_id = ? AND curso_id = ? AND ano = ? AND semestre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, asignacion.getProfesor().getId());
            stmt.setInt(2, asignacion.getCurso().getId());
            stmt.setInt(3, asignacion.getAno());
            stmt.setInt(4, asignacion.getSemestre());
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                asignaciones.remove(asignacion);
            }
        } catch (SQLException e) {
            System.err.println("Error eliminando asignación: " + e.getMessage());
        }
    }

    public void actualizar(CursoProfesor viejaAsignacion, CursoProfesor nuevaAsignacion) {
        String sql = "UPDATE asignaciones_cursos_profesores SET profesor_id = ?, curso_id = ?, ano = ?, semestre = ? " +
                   "WHERE profesor_id = ? AND curso_id = ? AND ano = ? AND semestre = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Nuevos valores
            stmt.setDouble(1, nuevaAsignacion.getProfesor().getId());
            stmt.setInt(2, nuevaAsignacion.getCurso().getId());
            stmt.setInt(3, nuevaAsignacion.getAno());
            stmt.setInt(4, nuevaAsignacion.getSemestre());
            
            // Valores antiguos para WHERE
            stmt.setDouble(5, viejaAsignacion.getProfesor().getId());
            stmt.setInt(6, viejaAsignacion.getCurso().getId());
            stmt.setInt(7, viejaAsignacion.getAno());
            stmt.setInt(8, viejaAsignacion.getSemestre());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                asignaciones.remove(viejaAsignacion);
                asignaciones.add(nuevaAsignacion);
            }
        } catch (SQLException e) {
            System.err.println("Error actualizando asignación: " + e.getMessage());
        }
    }

    // Métodos de la interfaz Servicios
    @Override
    public String imprimirPosicion(String posicion) {
        try {
            int index = Integer.parseInt(posicion);
            if (index >= 0 && index < asignaciones.size()) {
                return asignaciones.get(index).toString();
            }
            return "Posición inválida";
        } catch (NumberFormatException e) {
            return "La posición debe ser un número";
        }
    }

    @Override
    public int cantidadActual() {
        return asignaciones.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> listado = new ArrayList<>();
        for (CursoProfesor asignacion : asignaciones) {
            listado.add(asignacion.toString());
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
        asignaciones.clear();
        String sql = "SELECT * FROM asignaciones_cursos_profesores " +
                    "JOIN profesores ON profesor_id = profesores.id " +
                    "JOIN cursos ON curso_id = cursos.id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Profesor profesor = new Profesor(
                    rs.getDouble("profesores.id"),
                    rs.getString("profesores.nombres"),
                    rs.getString("profesores.apellidos"),
                    rs.getString("profesores.email"),
                    rs.getString("profesores.tipo_contrato")
                );
                
                Curso curso = new Curso(
                    rs.getInt("cursos.id"),
                    rs.getString("cursos.nombre"),
                    null,
                    rs.getBoolean("cursos.activo")
                );
                
                CursoProfesor asignacion = new CursoProfesor(
                    profesor,
                    rs.getInt("ano"),
                    rs.getInt("semestre"),
                    curso
                );
                
                asignaciones.add(asignacion);
            }
        } catch (SQLException e) {
            System.err.println("Error cargando datos: " + e.getMessage());
        }
    }
}