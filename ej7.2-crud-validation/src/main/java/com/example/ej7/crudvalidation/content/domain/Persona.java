package com.example.ej7.crudvalidation.content.domain;

import com.example.ej7.crudvalidation.Generador2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "personas")
@AllArgsConstructor
@NoArgsConstructor
public class Persona implements java.io.Serializable {

    /*@Id
    @GeneratedValue(generator = "prod-generator2")
    @GenericGenerator(name="prod-generator2",
            parameters = @org.hibernate.annotations.Parameter(name="prefijo", value="person"),
            strategy = "com.example.ej7.crudvalidation.MiGenerador")*/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @GenericGenerator(
            name = "person_seq",
            strategy = "com.example.ej7.crudvalidation.Generador2",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = Generador2.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = Generador2.VALUE_PREFIX_PARAMETER, value = "person-"),
                    @org.hibernate.annotations.Parameter(name = Generador2.NUMBER_FORMAT_PARAMETER, value = "%04d")
            })
    private String id;
    @Column
    @NotNull(message = "El usuario no puede ser nulo")
    private String usuario;
    @Column
    @NotNull(message = "La contraseña no puede ser vacía")
    private String password;
    @Column
    @NotNull(message = "La persona debe tener nombre")
    private String name;
    @Column
    private String surname;
    @Column
    @NotNull(message = "La persona debe tener correo corporativo")
    private String company_email;
    @Column
    @NotNull(message = "La persona debe tener su correo personal registrado")
    private String personal_email;
    @Column
    @NotNull(message = "La persona debe tener una ciudad registrada")
    private String city;
    @Column
    @NotNull(message = "El valor de la actividad debe ser el correcto")
    private Boolean active;

    @Column
    @NotNull(message = "Hay que guardar la fecha de creación de la persona")
    private String created_date;
    @Column
    private String imagen_url;
    @Column
    private String termination_date;


    public String toString(){
        return id + " - "+ usuario+": "+company_email;
    }

    @Override
    public boolean equals(Object obj) {
        Persona p = (Persona) obj;
        return this.id==p.getId() && this.usuario.equals(p.getUsuario());
    }
}
