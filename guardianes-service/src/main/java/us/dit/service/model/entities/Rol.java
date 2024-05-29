package us.dit.service.model.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * @author carcohcal
 */
@Data
@Entity
public class Rol {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nombreRol;


    public Rol() {

    }

    public Rol(String rol) {

        this.nombreRol = rol;

    }

    @Override
    public String toString() {
        return Rol.class.getSimpleName() + "(" + "Nombre rol=" + this.nombreRol + ")";
    }

    public String getNombreRol() {
        return this.nombreRol;
    }

    public void setNombreRol(String rol) {
        this.nombreRol = rol;
    }


}
