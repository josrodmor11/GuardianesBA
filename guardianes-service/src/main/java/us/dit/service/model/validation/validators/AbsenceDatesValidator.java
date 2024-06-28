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
package us.dit.service.model.validation.validators;


import us.dit.service.model.entities.Absence;
import us.dit.service.model.validation.annotations.ValidAbsenceDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * This class is used to validate the {@link Absence}s annotated with
 * {@link ValidAbsenceDates}
 * <p>
 * It checks that the start date is before the end date. A null Absence is also
 * considered to be valid
 *
 * @author miggoncan
 */
public class AbsenceDatesValidator implements ConstraintValidator<ValidAbsenceDates, Absence> {
    @Override
    public boolean isValid(Absence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        LocalDate start = value.getStartDate();
        LocalDate end = value.getEndDate();
        return start != null && end != null && start.isBefore(end);
    }
}
