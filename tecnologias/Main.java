import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Datos de ejemplo
        Facultad fac1 = new Facultad(1, "Ingenieria");
        Programa prog1 = new Programa(1, "Sistemas", fac1);

        Profesor prof1 = new Profesor(1, "Profesor Juan");
        Profesor prof2 = new Profesor(2, "Profesor Ana");

        Curso curso1 = new Curso(1, "Matematicas", prog1);
        Curso curso2 = new Curso(2, "Fisica", prog1);

        CursoProfesor cp1 = new CursoProfesor(1, curso1, prof1);
        CursoProfesor cp2 = new CursoProfesor(2, curso2, prof2);

        Estudiante est1 = new Estudiante(1, "Estudiante Maria");
        Estudiante est2 = new Estudiante(2, "Estudiante Pedro");

        // Maps para cargar
        Map<Integer, Estudiante> estudiantesMap = new HashMap<>();
        estudiantesMap.put(1, est1);
        estudiantesMap.put(2, est2);

        Map<Integer, CursoProfesor> cursoProfesoresMap = new HashMap<>();
        cursoProfesoresMap.put(1, cp1);
        cursoProfesoresMap.put(2, cp2);

        // Instancia con persistencia (seleccionado: CursosInscritos)
        CursosInscritos ci = new CursosInscritos();
        ci.cargarDatos(estudiantesMap, cursoProfesoresMap);

        // Otras instancias sin persistencia
        InscripcionesPersonas ip = new InscripcionesPersonas();
        ip.cargarDatos();

        CursosProfesores cprof = new CursosProfesores();
        cprof.cargarDatos();

        // Ejemplo de uso in-memory
        ip.inscribir(cp1);
        cprof.inscribirCurso(new Inscripcion(3, est2, cp2, 3.5));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAplicacion de Consola - Sistema Universitario");
            System.out.println("1. Gestionar CursosInscritos (con persistencia en H2)");
            System.out.println("2. Gestionar InscripcionesPersonas (in-memory)");
            System.out.println("3. Gestionar CursosProfesores (in-memory)");
            System.out.println("4. Salir");
            int opcionPrincipal = scanner.nextInt();

            if (opcionPrincipal == 4) {
                ci.cerrarConexion();
                break;
            }

            if (opcionPrincipal == 1) {
                gestionarCursosInscritos(ci, scanner, estudiantesMap, cursoProfesoresMap);
            } else if (opcionPrincipal == 2) {
                gestionarInscripcionesPersonas(ip, scanner, cursoProfesoresMap);
            } else if (opcionPrincipal == 3) {
                gestionarCursosProfesores(cprof, scanner, estudiantesMap, cursoProfesoresMap);
            }
        }
    }

    private static void gestionarCursosInscritos(CursosInscritos ci, Scanner scanner, Map<Integer, Estudiante> estudiantesMap, Map<Integer, CursoProfesor> cursoProfesoresMap) {
        while (true) {
            System.out.println("\nMenu CursosInscritos:");
            System.out.println("1. Listar");
            System.out.println("2. Inscribir nueva inscripcion");
            System.out.println("3. Eliminar inscripcion");
            System.out.println("4. Actualizar nota");
            System.out.println("5. Cargar datos (recargar desde DB)");
            System.out.println("6. Volver");
            int opcion = scanner.nextInt();

            if (opcion == 6) break;

            switch (opcion) {
                case 1:
                    System.out.println(ci.toString());
                    break;
                case 2:
                    System.out.print("Ingresa ID inscripcion: ");
                    int id = scanner.nextInt();
                    System.out.print("Ingresa ID estudiante (disponibles: 1,2): ");
                    int estId = scanner.nextInt();
                    System.out.print("Ingresa ID cursoProfesor (disponibles: 1,2): ");
                    int cpId = scanner.nextInt();
                    System.out.print("Ingresa nota: ");
                    double nota = scanner.nextDouble();
                    Estudiante est = estudiantesMap.get(estId);
                    CursoProfesor cpr = cursoProfesoresMap.get(cpId);
                    if (est != null && cpr != null) {
                        Inscripcion newIns = new Inscripcion(id, est, cpr, nota);
                        ci.inscribirCurso(newIns);
                        System.out.println("Inscripcion agregada y guardada en DB.");
                    } else {
                        System.out.println("ID no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingresa ID inscripcion a eliminar: ");
                    int delId = scanner.nextInt();
                    Inscripcion toDel = null;
                    for (Inscripcion i : ci.getLista()) {
                        if (i.getId() == delId) {
                            toDel = i;
                            break;
                        }
                    }
                    if (toDel != null) {
                        ci.eliminar(toDel);
                        System.out.println("Inscripcion eliminada de lista y DB.");
                    } else {
                        System.out.println("No encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingresa ID inscripcion a actualizar: ");
                    int upId = scanner.nextInt();
                    System.out.print("Nueva nota: ");
                    double newNota = scanner.nextDouble();
                    Inscripcion toUp = null;
                    for (Inscripcion i : ci.getLista()) {
                        if (i.getId() == upId) {
                            toUp = i;
                            break;
                        }
                    }
                    if (toUp != null) {
                        toUp.setNota(newNota);
                        ci.actualizar(toUp);
                        System.out.println("Nota actualizada en lista y DB.");
                    } else {
                        System.out.println("No encontrado.");
                    }
                    break;
                case 5:
                    ci.cargarDatos(estudiantesMap, cursoProfesoresMap);
                    System.out.println("Datos recargados desde DB.");
                    break;
            }
        }
    }

    private static void gestionarInscripcionesPersonas(InscripcionesPersonas ip, Scanner scanner, Map<Integer, CursoProfesor> cursoProfesoresMap) {
        while (true) {
            System.out.println("\nMenu InscripcionesPersonas (in-memory):");
            System.out.println("1. Listar");
            System.out.println("2. Inscribir CursoProfesor");
            System.out.println("3. Guardar informacion CursoProfesor");
            System.out.println("4. Volver");
            int opcion = scanner.nextInt();

            if (opcion == 4) break;

            switch (opcion) {
                case 1:
                    System.out.println(ip.toString());
                    break;
                case 2:
                    System.out.print("Ingresa ID cursoProfesor (disponibles: 1,2): ");
                    int cpId = scanner.nextInt();
                    CursoProfesor cpr = cursoProfesoresMap.get(cpId);
                    if (cpr != null) {
                        ip.inscribir(cpr);
                        System.out.println("CursoProfesor inscrito.");
                    } else {
                        System.out.println("ID no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingresa ID cursoProfesor para guardar: ");
                    int gcpId = scanner.nextInt();
                    CursoProfesor gcpr = cursoProfesoresMap.get(gcpId);
                    if (gcpr != null) {
                        ip.guardarInformacion(gcpr);
                        System.out.println("Informacion guardada (in-memory).");
                    } else {
                        System.out.println("ID no encontrado.");
                    }
                    break;
            }
        }
    }

    private static void gestionarCursosProfesores(CursosProfesores cprof, Scanner scanner, Map<Integer, Estudiante> estudiantesMap, Map<Integer, CursoProfesor> cursoProfesoresMap) {
        while (true) {
            System.out.println("\nMenu CursosProfesores (in-memory):");
            System.out.println("1. Listar");
            System.out.println("2. Inscribir inscripcion");
            System.out.println("3. Eliminar inscripcion");
            System.out.println("4. Actualizar inscripcion");
            System.out.println("5. Guardar informacion inscripcion");
            System.out.println("6. Volver");
            int opcion = scanner.nextInt();

            if (opcion == 6) break;

            switch (opcion) {
                case 1:
                    System.out.println(cprof.toString());
                    break;
                case 2:
                    System.out.print("Ingresa ID inscripcion: ");
                    int id = scanner.nextInt();
                    System.out.print("Ingresa ID estudiante (1,2): ");
                    int estId = scanner.nextInt();
                    System.out.print("Ingresa ID cursoProfesor (1,2): ");
                    int cpId = scanner.nextInt();
                    System.out.print("Ingresa nota: ");
                    double nota = scanner.nextDouble();
                    Estudiante est = estudiantesMap.get(estId);
                    CursoProfesor cpr = cursoProfesoresMap.get(cpId);
                    if (est != null && cpr != null) {
                        Inscripcion newIns = new Inscripcion(id, est, cpr, nota);
                        cprof.inscribirCurso(newIns);
                        System.out.println("Inscripcion inscrita.");
                    } else {
                        System.out.println("ID no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingresa ID inscripcion a eliminar: ");
                    int delId = scanner.nextInt();
                    Inscripcion toDel = null;
                    for (Inscripcion i : cprof.getLista()) {
                        if (i.getId() == delId) {
                            toDel = i;
                            break;
                        }
                    }
                    if (toDel != null) {
                        cprof.eliminar(toDel);
                        System.out.println("Inscripcion eliminada.");
                    } else {
                        System.out.println("No encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingresa ID inscripcion a actualizar: ");
                    int upId = scanner.nextInt();
                    System.out.print("Nueva nota: ");
                    double newNota = scanner.nextDouble();
                    Inscripcion temp = new Inscripcion(upId, null, null, newNota);  // Temp for update
                    cprof.actualizar(temp);
                    System.out.println("Inscripcion actualizada.");
                    break;
                case 5:
                    System.out.print("Ingresa ID inscripcion para guardar: ");
                    int gId = scanner.nextInt();
                    System.out.print("Nota (para temp): ");
                    double gNota = scanner.nextDouble();
                    Inscripcion gTemp = new Inscripcion(gId, null, null, gNota);
                    cprof.guardarInformacion(gTemp);
                    System.out.println("Informacion guardada (in-memory).");
                    break;
            }
        }
    }
}