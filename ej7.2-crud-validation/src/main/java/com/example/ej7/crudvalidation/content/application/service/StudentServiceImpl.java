package com.example.ej7.crudvalidation.content.application.service;

import com.example.ej7.crudvalidation.content.domain.Estudiante_Asignatura;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repository;
    @Autowired
    Estudiante_AsignaturaRepository estudiante_asignaturaRepository;


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

    @Override
    public void addAsignaturas(String id, List<Estudiante_Asignatura> lista) throws EntityNotFoundException {
        List<Student> aux = new ArrayList<>();
        repository.findAll().forEach(aux::add);
        Optional<Student> os = aux.stream().filter(x->x.getId_student().equals(id)).findFirst();
        if(os.isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado estudiantes con el id "+id);
        }else{
            List<Estudiante_Asignatura> matriculadas = os.get().getAsignaturas();
            List<Estudiante_Asignatura> aux2 = new ArrayList<>();
            List<Estudiante_Asignatura> aux3 = new ArrayList<>();
            estudiante_asignaturaRepository.findAll().forEach(aux2::add);
            lista.forEach(x-> {
                if(x.getId_asignatura()==null){
                    x.setId_student(os.get());
                    estudiante_asignaturaRepository.save(x);
                }else{
                    Optional<Estudiante_Asignatura> ea = aux2.stream().filter(x2 -> x2.getId_asignatura().equals(x.getId_asignatura())).findFirst();
                    if(ea.isPresent()){
                        ea.get().setId_student(os.get());
                        aux3.add(ea.get());
                        estudiante_asignaturaRepository.save(ea.get());
                    }
                }
            });
            if(!aux3.isEmpty()){
                matriculadas.addAll(aux3);
            }
            os.get().setAsignaturas(matriculadas);
        }
        repository.save(os.get());
    }

    @Override
    public void removeAsignaturas(String id, List<String> strings) throws EntityNotFoundException {
        List<Student> aux = new ArrayList<>();
        repository.findAll().forEach(aux::add);
        Optional<Student> os = aux.stream().filter(x->x.getId_student().equals(id)).findFirst();
        if(os.isEmpty()){
            throw new EntityNotFoundException("No se han encontrado estudiantes con el id "+id);
        }else{
            List<Estudiante_Asignatura> lista = new ArrayList<>();
            List<Estudiante_Asignatura> matriculadas = os.get().getAsignaturas();
            List<Estudiante_Asignatura> todas = new ArrayList<>();
            List<Estudiante_Asignatura> auxiliar = new ArrayList<>();
            estudiante_asignaturaRepository.findAll().forEach(x->{
                todas.add(x);
                strings.forEach(y->{
                    if(x.getId_asignatura().equals(y)){
                        lista.add(x);
                    }
                });
            });
            lista.forEach(x->{
                matriculadas.forEach(x2->{
                    if(x.getId_asignatura().equals(x2.getId_asignatura())){
                        Optional<Estudiante_Asignatura> eas = todas.stream().filter(a -> a.getId_asignatura().equals(x.getId_asignatura())).findFirst();
                        if(eas.isPresent()){
                            eas.get().setId_student(null);
                            estudiante_asignaturaRepository.save(eas.get());
                            auxiliar.add(x2);
                        }
                    }
                });
            });
            matriculadas.removeAll(auxiliar);
            os.get().setAsignaturas(matriculadas);
            repository.save(os.get());
        }
    }

    @Override
    public void addAsignatura(String id, Estudiante_Asignatura ea) throws EntityNotFoundException {
        List<Student> aux = new ArrayList<>();
        repository.findAll().forEach(aux::add);
        Optional<Student> os = aux.stream().filter(x->x.getId_student().equals(id)).findFirst();
        if(os.isEmpty()){
            throw new EntityNotFoundException("No se han encontrado estudiantes con el id "+id);
        }else{
            List<Estudiante_Asignatura> aux2 = os.get().getAsignaturas();
            aux2.add(ea);
            os.get().setAsignaturas(aux2);
        }
    }
}
