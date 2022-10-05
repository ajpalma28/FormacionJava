package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repository;


    @Override
    public void addStudent(Student student) {
        repository.save(student);
    }

    @Override
    public List<Student> getTodos() {
        List<Student> estudiantes = new ArrayList<>();
        repository.findAll().forEach(estudiantes::add);
        return estudiantes;
    }

    @Override
    public Student findById(String id) {
        List<Student> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        Optional<Student> s = lista.stream().filter(x->x.getId_student().equals(id)).findFirst();
        return s.orElse(null);
    }

    @Override
    public Student editaStudent(Student student) {
        repository.save(student);
        return student;
    }

    @Override
    public void borraStudent(Student student) {
        repository.delete(student);
    }
}
