/**
*  This file is part of GuardianesBA - Business Application for processes managing healthcare tasks planning and supervision.
*  Copyright (C) 2024  Universidad de Sevilla/Departamento de Ingeniería Telemática
*
*  GuardianesBA is free software: you can redistribute it and/or
*  modify it under the terms of the GNU General Public License as published
*  by the Free Software Foundation, either version 3 of the License, or (at
*  your option) any later version.
*
*  GuardianesBA is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
*  Public License for more details.
*
*  You should have received a copy of the GNU General Public License along
*  with GuardianesBA. If not, see <https://www.gnu.org/licenses/>.
**/
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
