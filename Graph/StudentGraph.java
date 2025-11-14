import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Models.Student;

public class StudentGraph {

    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Set<String>> adjacency = new HashMap<>();

    public void addStudent(Student s) {
        students.putIfAbsent(s.getName(), s);
        adjacency.putIfAbsent(s.getName(), new HashSet<>());
    }

    public boolean removeStudent(String name) {
        if (!students.containsKey(name))
            return false;

        // borrar aristas que lo mencionen
        for (Set<String> vecinos : adjacency.values()) {
            vecinos.remove(name);
        }
        adjacency.remove(name);
        students.remove(name);
        return true;
    }

    public Student findByName(String name) {
        return students.get(name);
    }

    public void addRelation(String name1, String name2) {
        if (!students.containsKey(name1) || !students.containsKey(name2)) {
            System.out.println("No se puede crear relación, estudiante no existe.");
            return;
        }
        adjacency.get(name1).add(name2);
        adjacency.get(name2).add(name1);
    }

    public void removeRelation(String name1, String name2) {
        if (adjacency.containsKey(name1))
            adjacency.get(name1).remove(name2);
        if (adjacency.containsKey(name2))
            adjacency.get(name2).remove(name1);
    }

    public void printStudents() {
        if (students.isEmpty()) {
            System.out.println("No hay estudiantes.");
            return;
        }
        System.out.println("=== Estudiantes ===");
        for (Student s : students.values()) {
            System.out.println(s);
        }
    }

    public void printRelations(String name) {
        Student s = students.get(name);
        if (s == null) {
            System.out.println("El estudiante no existe.");
            return;
        }
        Set<String> amigos = adjacency.getOrDefault(name, Collections.emptySet());
        System.out.println("Relaciones de " + name + ":");
        if (amigos.isEmpty()) {
            System.out.println("  (sin relaciones)");
        } else {
            for (String amigo : amigos) {
                System.out.println("  - " + amigo);
            }
        }
    }
}
