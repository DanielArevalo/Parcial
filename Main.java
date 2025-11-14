import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import Models.Student;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentGraph graph = new StudentGraph();

        // 1. Cargar estudiantes desde CSV
        try {
            cargarEstudiantesDesdeCsv("resources\\parcial_estudiantes.csv", graph);
        } catch (Exception e) {
            System.out.println("No se pudo leer el CSV: " + e.getMessage());
            return;
        }

        crearRelacionesIniciales(graph);

        graph.addStudent(new Student("111", "Luisa Fernandez", 4.6));
        graph.addStudent(new Student("112", "Manuel Ortiz", 4.7));

        graph.removeStudent("Hector Diaz");
        graph.removeStudent("Karina Silva");

        graph.addRelation("Luisa Fernandez", "Manuel Ortiz");

        graph.removeRelation("Hector Diaz", "Isabel Gomez");
        graph.removeRelation("Julian Castro", "Karina Silva");

        int opc;
        do {
            System.out.println("\n===== MENU ESTUDIANTES-GRAFO =====");
            System.out.println("1. Mostrar lista de estudiantes");
            System.out.println("2. Agregar estudiante");
            System.out.println("3. Eliminar estudiante");
            System.out.println("4. Buscar estudiante por nombre");
            System.out.println("5. Mostrar relaciones de un estudiante");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1:
                    graph.printStudents();
                    break;
                case 2:
                    agregarEstudiantePorConsola(graph, sc);
                    break;
                case 3:
                    eliminarEstudiantePorConsola(graph, sc);
                    break;
                case 4:
                    buscarPorNombre(graph, sc);
                    break;
                case 5:
                    mostrarRelaciones(graph, sc);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opc != 0);
    }

    private static void cargarEstudiantesDesdeCsv(String ruta, StudentGraph graph) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank())
                    continue;
                String[] partes = linea.split(",");
                if (partes.length < 3)
                    continue;

                String codigo = partes[1].trim();
                String nombre = partes[0].trim();
                double promedio = Double.parseDouble(partes[2].trim());

                graph.addStudent(new Student(codigo, nombre, promedio));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void crearRelacionesIniciales(StudentGraph g) {
        g.addRelation("Ana Garcia", "Carlos Ruiz");
        g.addRelation("Carlos Ruiz", "Diego Lopez");
        g.addRelation("Diego Lopez", "Elena Martinez");
        g.addRelation("Elena Martinez", "Fernando Perez");
        g.addRelation("Fernando Perez", "Gabriela Torres");
        g.addRelation("Gabriela Torres", "Hector Diaz");
        g.addRelation("Hector Diaz", "Isabel Gomez");
        g.addRelation("Isabel Gomez", "Julian Castro");
        g.addRelation("Julian Castro", "Karina Silva");
    }

    private static void agregarEstudiantePorConsola(StudentGraph graph, Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Promedio: ");
        double promedio = Double.parseDouble(sc.nextLine());

        graph.addStudent(new Student(codigo, nombre, promedio));
        System.out.println("Estudiante agregado.");
    }

    private static void eliminarEstudiantePorConsola(StudentGraph graph, Scanner sc) {
        System.out.print("Nombre del estudiante a eliminar: ");
        String nombre = sc.nextLine();
        if (graph.removeStudent(nombre)) {
            System.out.println("Estudiante eliminado.");
        } else {
            System.out.println("No se encontró el estudiante.");
        }
    }

    private static void buscarPorNombre(StudentGraph graph, Scanner sc) {
        System.out.print("Nombre del estudiante a buscar: ");
        String nombre = sc.nextLine();
        Student s = graph.findByName(nombre);
        if (s != null) {
            System.out.println("Encontrado: " + s);
        } else {
            System.out.println("No se encontró el estudiante.");
        }
    }

    private static void mostrarRelaciones(StudentGraph graph, Scanner sc) {
        System.out.print("Nombre del estudiante: ");
        String nombre = sc.nextLine();
        graph.printRelations(nombre);
    }

}
