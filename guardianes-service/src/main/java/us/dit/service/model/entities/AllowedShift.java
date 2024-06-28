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
import javax.validation.constraints.NotBlank;

/**
 * AllowedShift represents the shifts a {@link Doctor} may have in their
 * unwanted/unavailable/wanted/mandatory shifts
 *
 * @author miggoncan
 * @see ShiftConfiguration
 */
@Data
@Entity
public class AllowedShift {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    //@NotNull
    private Integer id;

    /**
     * shift is the meaningful name used to specify an AllowedShift. For example,
     * they could be days of the week: "Monday", "Tuesday", "Wednesday", ...
     * <p>
     * Although shift is unique, and could be used as the id for the database,
     * numerical primary keys are preferred over string ones
     */
    @Column(unique = true, nullable = false)
    @NotBlank
    private String shift;

    public AllowedShift(String shift) {
        this.shift = shift;
    }

    public AllowedShift() {
    }
}
