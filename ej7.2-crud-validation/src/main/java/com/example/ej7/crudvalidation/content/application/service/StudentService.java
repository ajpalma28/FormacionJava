package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Student;

import java.util.List;

public interface StudentService {

    void addStudent(Student student);
    List<Student> getTodos();
    Student findById(String id);
    Student editaStudent(Student student);
    void borraStudent(Student student);

}
