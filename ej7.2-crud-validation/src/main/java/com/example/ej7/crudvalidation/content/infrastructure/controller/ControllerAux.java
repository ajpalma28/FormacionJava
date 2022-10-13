package com.example.ej7.crudvalidation.content.infrastructure.controller;

import com.example.ej7.crudvalidation.content.application.service.*;
import com.example.ej7.crudvalidation.content.domain.Profesor;
import com.example.ej7.crudvalidation.content.domain.Student;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.domain.exceptions.RolPersonException;
import com.example.ej7.crudvalidation.content.domain.exceptions.UnprocessableEntityException;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class ControllerAux {

    public void compruebaId(PersonaService s, String id) throws EntityNotFoundException {
        if(s.findById(id)==null){
            throw new EntityNotFoundException("ID no encontrado en la base de datos");
        }
    }

    public void compruebaId(StudentService s, String id) throws EntityNotFoundException {
        if(s.findById(id)==null){
            throw new EntityNotFoundException("ID no encontrado en la base de datos");
        }
    }

    public void compruebaId(ProfesorService s, String id) throws EntityNotFoundException{
        if(s.getById(id)==null){
            throw new EntityNotFoundException("ID no encontrado en la base de datos");
        }
    }

    public void compruebaId(Estudiante_AsignaturaService s, String id) throws EntityNotFoundException{
        if(s.findById(id)==null){
            throw new EntityNotFoundException("ID no encontrado en la base de datos");
        }
    }

    public void compruebaUsuario(String u) throws UnprocessableEntityException {
        if(u == null){
            throw new UnprocessableEntityException("El usuario no puede estar vacío");
        }else{
            if(u.length()<6 || u.length()>10){
                throw new UnprocessableEntityException("La longitud del usuario debe ser entre 6 y 10");
            }
        }
    }

    public void compruebaPW(String pw) throws UnprocessableEntityException{
        if(pw == null || pw.equals("")){
            throw new UnprocessableEntityException("Falta la contraseña");
        }
    }

    public void compruebaNombre(String n) throws UnprocessableEntityException{
        if(n==null || n.equals("")){
            throw new UnprocessableEntityException("Falta el nombre");
        }
    }

    public void compruebaEmail1(String e) throws UnprocessableEntityException{
        if(e==null || e.equals("")){
            throw new UnprocessableEntityException("Falta el email corporativo");
        }
    }

    public void compruebaEmail2(String e) throws UnprocessableEntityException{
        if(e==null || e.equals("")){
            throw new UnprocessableEntityException("Falta el email personal");
        }
    }

    public void compruebaCiudad(String c) throws UnprocessableEntityException{
        if(c==null || c.equals("")){
            throw new UnprocessableEntityException("Falta la ciudad");
        }
    }

    public void compruebaAct(Boolean b) throws UnprocessableEntityException{
        if(b==null){
            throw new UnprocessableEntityException("Falta indicar si está activo o no");
        }
    }

    public void compruebaFecha(String d) throws UnprocessableEntityException{
        if(d==null){
            throw new UnprocessableEntityException("Falta la fecha de creación");
        }
    }

    public void compruebaNumHours(int nh) throws UnprocessableEntityException{
        if(nh == 0){
            throw new UnprocessableEntityException("Falta el número de horas del estudiante");
        }
    }

    public void compruebaBranch(String branch) throws UnprocessableEntityException{
        if(branch==null){
            throw new UnprocessableEntityException("Falta la rama principal del estudiante");
        }
    }

    public void compruebaRolCorrecto(StudentService ss, String id) throws RolPersonException {
        Optional<Student> s = ss.getTodos().stream().filter(x->x.getPersona().getId().equals(id)).findFirst();
        if (s.isPresent()) {
            throw new RolPersonException("Esta persona ya tiene rol de estudiante");
        }
    }

    public void compruebaRolCorrecto(ProfesorService ps, String id) throws RolPersonException {
        Optional<Profesor> p = ps.getProfesores().stream().filter(x->x.getPersona().getId().equals(id)).findFirst();
        if (p.isPresent()) {
            throw new RolPersonException("Esta persona ya tiene rol de profesor");
        }
    }

    public void compruebaProfesorOK(ProfesorService ps, String id) throws EntityNotFoundException {
        Profesor p = ps.getById(id);
        if(p==null){
            throw new EntityNotFoundException("No hay un profesor con dicho id");
        }
    }

    public void compruebaAlumnoOK(StudentService ss, String id) throws EntityNotFoundException {
        Student s = ss.findById(id);
        if(s==null){
            throw new EntityNotFoundException("No hay un alumno con dicho id");
        }
    }

    public String devuelveRol(ProfesorService ps, StudentService ss, String id){
        String res = "";
        Optional<Profesor> p = ps.getProfesores().stream().filter(x-> x.getPersona().getId().equals(id)).findFirst();
        Optional<Student> s = ss.getTodos().stream().filter(x-> x.getPersona().getId().equals(id)).findFirst();
        if(p.isPresent()){
            res = "Profesor";
        }else if(s.isPresent()){
            res = "Estudiante";
        }
        return res;
    }
}
