package com.example.ej7.crudvalidation.content.infrastructure.controller.persona;

import com.example.ej7.crudvalidation.content.application.service.PersonaService;
import com.example.ej7.crudvalidation.content.domain.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.content.domain.exceptions.UnprocessableEntityException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PersonaControllerAux {

    public void compruebaId(PersonaService s, int id) throws EntityNotFoundException {
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

}
