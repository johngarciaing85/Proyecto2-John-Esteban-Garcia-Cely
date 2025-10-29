package com.example.Proyecto2.models;

import com.example.Proyecto2.enums.Facultad;
import com.example.Proyecto2.enums.Universidad;
import java.util.Deque;

public class Profesor {
    private String nombre;
    private Facultad facultad;
    private Universidad universidad;
    private Deque<Estudiante> estudiantes;

    public Profesor() {}

    public Profesor(String nombre, Facultad facultad, Universidad universidad, Deque<Estudiante> estudiantes) {
        this.nombre = nombre;
        this.facultad = facultad;
        this.universidad = universidad;
        this.estudiantes = estudiantes;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Facultad getFacultad() { return facultad; }
    public void setFacultad(Facultad facultad) { this.facultad = facultad; }

    public Universidad getUniversidad() { return universidad; }
    public void setUniversidad(Universidad universidad) { this.universidad = universidad; }

    public Deque<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(Deque<Estudiante> estudiantes) { this.estudiantes = estudiantes; }

    @Override
    public String toString() {
        return "Profesor{nombre='" + nombre + "', facultad=" + facultad +
                ", universidad=" + universidad + ", estudiantes=" + estudiantes + "}";
    }
}