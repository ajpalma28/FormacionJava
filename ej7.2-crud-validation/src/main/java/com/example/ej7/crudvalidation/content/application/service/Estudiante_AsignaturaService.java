package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;

import java.util.List;

public interface Estudiante_AsignaturaService {

    void addAsignatura(Estudiante_Asignatura asignatura);
    Estudiante_Asignatura findById(String id);
    Estudiante_Asignatura devuelvePorNombre(String nombre);
    Estudiante_Asignatura modificaAsignatura(Estudiante_Asignatura asignatura);
    List<Estudiante_Asignatura> devuelveTodas();
    void eliminaAsignatura(Estudiante_Asignatura asignatura);
    void eliminaTodas();
    void eliminaPorId(String id);

}