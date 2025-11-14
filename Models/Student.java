package Models;

public class Student {
    private String codigo;
    private String nombre;
    private double promedio;

    public Student(String codigo, String nombre, double promedio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    public String getCode() {
        return codigo;
    }

    public String getName() {
        return nombre;
    }

    public double getAverage() {
        return promedio;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Código: " + codigo + ", Promedio: " + promedio;
    }
}
