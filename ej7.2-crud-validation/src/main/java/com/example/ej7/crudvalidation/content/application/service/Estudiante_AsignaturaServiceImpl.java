package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import com.example.ej7.crudvalidation.content.domain.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Estudiante_AsignaturaServiceImpl implements Estudiante_AsignaturaService{

    @Autowired
    Estudiante_AsignaturaRepository repository;

    @Override
    public void addAsignatura(Estudiante_Asignatura asignatura) {
        repository.save(asignatura);
    }

    @Override
    public Estudiante_Asignatura findById(String id) {
        List<Estudiante_Asignatura> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        Optional<Estudiante_Asignatura> p = lista.stream().filter(x->x.getId_asignatura().equals(id)).findFirst();
        return p.orElse(null);
    }

    @Override
    public Estudiante_Asignatura devuelvePorNombre(String nombre) {
        List<Estudiante_Asignatura> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        Optional<Estudiante_Asignatura> ea = lista.stream().filter(x->x.getAsignatura().equals(nombre)).findFirst();
        return ea.orElse(null);
    }

    @Override
    public Estudiante_Asignatura modificaAsignatura(Estudiante_Asignatura asignatura) {
        repository.save(asignatura);
        return asignatura;
    }

    @Override
    public List<Estudiante_Asignatura> devuelveTodas() {
        List<Estudiante_Asignatura> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        return lista;
    }

    @Override
    public void eliminaAsignatura(Estudiante_Asignatura asignatura) {
        repository.delete(asignatura);
    }

    @Override
    public void eliminaTodas() {
        repository.deleteAll();
    }

    @Override
    public void eliminaPorId(String id) {
        List<Estudiante_Asignatura> lista = new ArrayList<>();
        repository.findAll().forEach(lista::add);
        Optional<Estudiante_Asignatura> ea = lista.stream().filter(x->x.getId_asignatura().equals(id)).findFirst();
        ea.ifPresent(asignatura -> repository.delete(asignatura));
    }

}