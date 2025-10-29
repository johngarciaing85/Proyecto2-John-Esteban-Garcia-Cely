package com.example.Proyecto2.models;


import com.example.Proyecto2.enums.Facultad;
import com.example.Proyecto2.enums.Universidad;

import java.util.Stack;
import java.util.function.Function;


public class Estudiante {
    private String nombre;
    private Facultad facultad;
    private Universidad universidad;
    private Stack<Integer> materias;

    // Constructores
    public Estudiante() {
        this.materias = new Stack<>();
    }

    public Estudiante(String nombre, Facultad facultad, Universidad universidad) {
        this();
        this.nombre = nombre;
        this.facultad = facultad;
        this.universidad = universidad;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Facultad getFacultad() { return facultad; }
    public void setFacultad(Facultad facultad) { this.facultad = facultad; }

    public Universidad getUniversidad() { return universidad; }
    public void setUniversidad(Universidad universidad) { this.universidad = universidad; }

    public Stack<Integer> getMaterias() { return materias; }
    public void setMaterias(Stack<Integer> materias) { this.materias = materias; }

    // Método para agregar materias
    public void agregarMateria(Integer materia) {
        this.materias.push(materia);
    }

    // PUNTO 5: Método verTotalMaterias
    public void verTotalMaterias(Function<Estudiante, Integer> function) {
        Integer total = function.apply(this);
        System.out.println("Total materias de " + this.nombre + ": " + total);
    }

    @Override
    public String toString() {
        return "Estudiante{nombre='" + nombre + "', facultad=" + facultad +
                ", universidad=" + universidad + ", materias=" + materias + "}";
    }
}