package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Profesor;
import com.example.ej7.crudvalidation.content.domain.Student;

import java.util.List;

public interface ProfesorService {

    void addProfesor(Profesor profesor);
    Profesor getById(String id);
    Profesor actualizaProfesor(Profesor profesor);
    List<Profesor> getProfesores();
    void eliminaProfesor(Profesor profesor);
    void eliminaById(long id);
}
