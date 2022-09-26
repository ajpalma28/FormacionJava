package com.example.ej7.crudvalidation.content.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
@Table(name = "persona")
public class Persona implements java.io.Serializable {

    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = "El usuario no puede ser nulo")
    private String usuario;
    @NotNull(message = "La contraseña no puede ser vacía")
    private String password;
    @NotNull(message = "La persona debe tener nombre")
    private String name;
    private String surname;
    @NotNull(message = "La persona debe tener correo corporativo")
    private String company_email;
    @NotNull(message = "La persona debe tener su correo personal registrado")
    private String personal_email;
    @NotNull(message = "La persona debe tener una ciudad registrada")
    private String city;
    @NotNull(message = "El valor de la actividad debe ser el correcto")
    private Boolean active;
    @NotNull(message = "Hay que guardar la fecha de creación de la persona")
    private String created_date;
    private String imagen_url;
    private String termination_date;

    public Persona(String u, String pw, String n, String sn, String ce, String pe, String c, Boolean act,
                       String cd, String img, String td){
        try {
            usuario = u;
            //compruebaPW(pw);
            password = pw;
            //compruebaNombre(n);
            name = n;
            surname = sn;
            //compruebaEmail1(ce);
            company_email = ce;
            //compruebaEmail2(pe);
            personal_email = pe;
            //compruebaCiudad(c);
            city = c;
            //compruebaAct(act);
            active = act;
            //compruebaFecha(cd);
            created_date = cd;
            imagen_url = img;
            termination_date = td;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public int getId(){
        return id;
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

    public String getCreatedDate(){
        return created_date;
    }

    public String getImagenUrl(){
        return imagen_url;
    }

    public String getTerminationDate(){
        return termination_date;
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

    public void setCreatedDate(String cd){
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

    public void setTerminationDate(String td){
        termination_date = td;
    }

    public void compruebaUsuario(String u) throws Exception {
        if(u == null){
            throw new Exception("El usuario no puede estar vacío");
        }else{
            if(u.length()<6 || u.length()>10){
                throw new Exception("La longitud del usuario debe ser entre 6 y 10");
            }
        }
    }

    public void compruebaPW(String pw) throws Exception{
        if(pw == null || pw.equals("")){
            throw new Exception("Falta la contraseña");
        }
    }

    public void compruebaNombre(String n) throws Exception{
        if(n==null || n.equals("")){
            throw new Exception("Falta el nombre");
        }
    }

    public void compruebaEmail1(String e) throws Exception{
        if(e==null || e.equals("")){
            throw new Exception("Falta el email corporativo");
        }
    }

    public void compruebaEmail2(String e) throws Exception{
        if(e==null || e.equals("")){
            throw new Exception("Falta el email personal");
        }
    }

    public void compruebaCiudad(String c) throws Exception{
        if(c==null || c.equals("")){
            throw new Exception("Falta la ciudad");
        }
    }

    public void compruebaAct(Boolean b) throws Exception{
        if(b==null){
            throw new Exception("Falta indicar si está activo o no");
        }
    }

    public void compruebaFecha(String d) throws Exception{
        if(d==null){
            throw new Exception("Falta la fecha de creación");
        }
    }

    public String toString(){
        return usuario+": "+name+" - "+company_email;
    }

    @Override
    public boolean equals(Object obj) {
        Persona p = (Persona) obj;
        return this.id==p.getId() && this.usuario.equals(p.getUsuario());
    }
}
