package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Profesor;
import com.example.ej7.crudvalidation.content.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements  ProfesorService {

    @Autowired
    ProfesorRepository repository;

    @Override
    public void addProfesor(Profesor profesor) {
        repository.save(profesor);
    }

    @Override
    public Profesor getById(String id) {
        List<Profesor> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        Optional<Profesor> p = lista.stream().filter(x->x.getId_profesor().equals(id)).findFirst();
        return p.orElse(null);
    }

    @Override
    public Profesor actualizaProfesor(Profesor profesor) {
        repository.save(profesor);
        return profesor;
    }

    @Override
    public List<Profesor> getProfesores() {
        List<Profesor> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        return lista;
    }

    @Override
    public void eliminaProfesor(Profesor profesor) {
        repository.delete(profesor);
    }

    @Override
    public void eliminaById(long id) {
        repository.deleteById(id);
    }

}
