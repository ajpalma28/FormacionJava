package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;

import java.util.List;

public interface StudentService {

    void addStudent(Student student);
    List<Student> getTodos();
    Student findById(String id);
    Student editaStudent(Student student);
    void borraStudent(Student student);
    void addAsignaturas(String id, List<Estudiante_Asignatura> lista) throws EntityNotFoundException;
    void removeAsignaturas(String id, List<String> strings) throws EntityNotFoundException;
    void addAsignatura(String id, Estudiante_Asignatura ea) throws EntityNotFoundException;

}
