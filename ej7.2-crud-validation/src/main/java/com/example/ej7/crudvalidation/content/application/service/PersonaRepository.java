package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaRepository extends CrudRepository<Persona,Long> {
}
