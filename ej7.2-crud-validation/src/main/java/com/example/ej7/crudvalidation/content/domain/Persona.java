package com.example.ej7.crudvalidation.content.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Persona {

    @Id
    @GeneratedValue
    private int id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    public Persona(String u, String pw, String n, String sn, String ce, String pe, String c, Boolean act,
                   Date cd, String img, Date td){
        try {
            compruebaUsuario(u);
            usuario = u;
            compruebaPW(pw);
            password = pw;
            compruebaNombre(n);
            name = n;
            surname = sn;
            compruebaEmail1(ce);
            company_email = ce;
            compruebaEmail2(pe);
            personal_email = pe;
            compruebaCiudad(c);
            city = c;
            compruebaAct(act);
            active = act;
            compruebaFecha(cd);
            created_date = cd;
            imagen_url = img;
            termination_date = td;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String getUsuario(){
        return usuario;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getCompanyEmail(){
        return company_email;
    }

    public String getPersonalEmail(){
        return personal_email;
    }

    public String getCity(){
        return city;
    }

    public Boolean getActive(){
        return active;
    }

    public Date getCreatedDate(){
        return created_date;
    }

    public String getImagenUrl(){
        return imagen_url;
    }

    public Date getTerminationDate(){
        return getTerminationDate();
    }

    public void setUsuario(String u){
        try {
            compruebaUsuario(u);
            usuario = u;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setPassword(String pw){
        try {
            compruebaPW(pw);
            password = pw;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setName(String n){
        try {
            compruebaNombre(n);
            name = n;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setSurname(String sn){
        surname = sn;
    }

    public void setCompanyEmail(String ce){
        try {
            compruebaEmail1(ce);
            company_email = ce;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setCity(String c){
        try {
            compruebaCiudad(c);
            city = c;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setActive(Boolean b){
        try{
            compruebaAct(b);
            active = b;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setPersonalEmail(String pe){
        try {
            compruebaEmail2(pe);
            personal_email = pe;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void setCreatedDate(Date cd){
        try{
            compruebaFecha(cd);
            created_date = cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setImagenUrl(String u){
        imagen_url = u;
    }

    public void setTerminationDate(Date td){
        termination_date = td;
    }

    private void compruebaUsuario(String u) throws Exception {
        if(u == null || u.equals("")){
            throw new Exception("El usuario no puede estar vacío");
        }else{
            if(u.length()<6 || u.length()>10){
                throw new Exception("La longitud del usuario debe ser entre 6 y 10");
            }
        }
    }

    private void compruebaPW(String pw) throws Exception{
        if(pw == null || pw.equals("")){
            throw new Exception("Falta la contraseña");
        }
    }

    private void compruebaNombre(String n) throws Exception{
        if(n==null || n.equals("")){
            throw new Exception("Falta el nombre");
        }
    }

    private void compruebaEmail1(String e) throws Exception{
        if(e==null || e.equals("")){
            throw new Exception("Falta el email corporativo");
        }
    }

    private void compruebaEmail2(String e) throws Exception{
        if(e==null || e.equals("")){
            throw new Exception("Falta el email personal");
        }
    }

    private void compruebaCiudad(String c) throws Exception{
        if(c==null || c.equals("")){
            throw new Exception("Falta la ciudad");
        }
    }

    private void compruebaAct(Boolean b) throws Exception{
        if(b==null){
            throw new Exception("Falta indicar si está activo o no");
        }
    }

    private void compruebaFecha(Date d) throws Exception{
        if(d==null){
            throw new Exception("Falta la fecha de creación");
        }
    }

}
