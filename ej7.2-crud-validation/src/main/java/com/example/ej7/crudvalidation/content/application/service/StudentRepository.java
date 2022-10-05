package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
