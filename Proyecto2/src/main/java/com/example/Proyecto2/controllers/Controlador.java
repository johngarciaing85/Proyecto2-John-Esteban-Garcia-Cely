package com.example.Proyecto2.controllers;


import com.example.Proyecto2.enums.Facultad;
import com.example.Proyecto2.enums.Universidad;
import com.example.Proyecto2.models.Estudiante;
import com.example.Proyecto2.models.Profesor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class Controlador {

    @GetMapping("/")
    public String mostrarVista(Model model) {
        List<String> resultados = new ArrayList<>();

        // PUNTO 1: Crear estudiantes y profesor
        resultados.add("=== PUNTO 1: Creación de Estudiantes y Profesor ===");

        Stack<Estudiante> estudiantesStack = new Stack<>();

        // Crear 5 estudiantes de UdeA Ingenierias
        Estudiante est1 = new Estudiante("Daniel Graciano", Facultad.Ingenierias, Universidad.UdeA);
        Estudiante est2 = new Estudiante("Daniela Sanchez", Facultad.Ingenierias, Universidad.UdeA);
        Estudiante est3 = new Estudiante("Luis Martínez", Facultad.Ingenierias, Universidad.UdeA);
        Estudiante est4 = new Estudiante("María Rodríguez", Facultad.Ingenierias, Universidad.UdeA);
        Estudiante est5 = new Estudiante("Pedro López", Facultad.Ingenierias, Universidad.UdeA);

        // Asignar materias a cada estudiante (5 materias por estudiante)
        asignarMaterias(est1, Arrays.asList(101, 102, 103, 104, 105));
        asignarMaterias(est2, Arrays.asList(201, 202, 203, 204, 205));
        asignarMaterias(est3, Arrays.asList(301, 302, 303, 304, 305));
        asignarMaterias(est4, Arrays.asList(401, 402, 403, 404, 405));
        asignarMaterias(est5, Arrays.asList(501, 502, 503, 504, 505));

        // Agregar estudiantes al Stack
        estudiantesStack.push(est1);
        estudiantesStack.push(est2);
        estudiantesStack.push(est3);
        estudiantesStack.push(est4);
        estudiantesStack.push(est5);

        // Crear Deque para el profesor
        Deque<Estudiante> estudiantesDeque = new ArrayDeque<>(estudiantesStack);

        // Crear profesor
        Profesor profesor = new Profesor("Dr. Juan Carlos", Facultad.Ingenierias, Universidad.UdeA, estudiantesDeque);

        resultados.add("Profesor creado: " + profesor.getNombre());
        resultados.add("Estudiantes creados: " + estudiantesStack.size());

        // PUNTO 2: ListIterator e Iterator
        resultados.add("");
        resultados.add("=== PUNTO 2: ListIterator e Iterator ===");

        // ListIterator con Stack
        resultados.add("--- ListIterator con Stack ---");
        ListIterator<Estudiante> listIterator = estudiantesStack.listIterator();
        while (listIterator.hasNext()) {
            Estudiante e = listIterator.next();
            resultados.add("Estudiante: " + e.getNombre() + " | Facultad: " +
                    e.getFacultad() + " | Universidad: " + e.getUniversidad());
        }

        // Iterator con Deque del profesor
        resultados.add("--- Iterator con Deque del Profesor ---");
        Iterator<Estudiante> iterator = profesor.getEstudiantes().iterator();
        while (iterator.hasNext()) {
            Estudiante e = iterator.next();
            resultados.add("Estudiante: " + e.getNombre() + " | Facultad: " +
                    e.getFacultad() + " | Universidad: " + e.getUniversidad());
        }

        // PUNTO 3: Programación Funcional - Filtrar por universidad UNAL
        resultados.add("");
        resultados.add("=== PUNTO 3: Programación Funcional - Universidad UNAL ===");

        // Convertir Stack a Deque
        Deque<Estudiante> dequeEstudiantes = new ArrayDeque<>(estudiantesStack);

        // Agregar un estudiante de UNAL para probar
        Estudiante estUNAL = new Estudiante("Sofia UNAL", Facultad.Ingenierias, Universidad.UNAL);
        asignarMaterias(estUNAL, Arrays.asList(601, 602, 603));
        dequeEstudiantes.add(estUNAL);

        // Usar programación funcional para filtrar estudiantes de UNAL
        List<String> estudiantesUNAL = dequeEstudiantes.stream()
                .filter(e -> e.getUniversidad() == Universidad.UNAL)
                .map(Estudiante::getNombre)
                .collect(Collectors.toList());

        resultados.add("Estudiantes de la universidad UNAL: " + estudiantesUNAL);

        // PUNTO 4: Interface Function<T,R>
        resultados.add("");
        resultados.add("=== PUNTO 4: Interface Function<T,R> ===");

        // Función para sumar materias
        Function<Estudiante, Integer> sumarMaterias = estudiante ->
                estudiante.getMaterias().size();

        // Probar la función
        for (Estudiante e : estudiantesStack) {
            Integer totalMaterias = sumarMaterias.apply(e);
            resultados.add(e.getNombre() + " tiene " + totalMaterias + " materias");
        }

        // PUNTO 5: Método verTotalMaterias
        resultados.add("");
        resultados.add("=== PUNTO 5: Método verTotalMaterias ===");

        // Usar el método verTotalMaterias con un estudiante
        Estudiante estudianteEjemplo = estudiantesStack.get(0);
        // Para mostrar en la vista, capturamos el resultado
        StringBuilder punto5Resultado = new StringBuilder();
        estudianteEjemplo.verTotalMaterias(sumarMaterias);
        punto5Resultado.append("Método verTotalMaterias ejecutado para: ")
                .append(estudianteEjemplo.getNombre());

        resultados.add(punto5Resultado.toString());

        // Pasar resultados al modelo
        model.addAttribute("resultados", resultados);
        model.addAttribute("estudiantes", estudiantesStack);
        model.addAttribute("profesor", profesor);
        model.addAttribute("estudiantesUNAL", estudiantesUNAL);
        model.addAttribute("punto5", punto5Resultado.toString());

        return "vistas";
    }

    private void asignarMaterias(Estudiante estudiante, List<Integer> materias) {
        for (Integer materia : materias) {
            estudiante.agregarMateria(materia);
        }
    }
}