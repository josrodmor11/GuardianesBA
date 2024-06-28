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

import us.dit.service.model.entities.Doctor;
import us.dit.service.model.entities.ShiftConfiguration;
import us.dit.service.model.validation.annotations.ValidShiftConfiguration;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is used to validate the {@link ShiftConfiguration}s annotated with
 * {@link ValidShiftConfiguration}
 * 
 * Particularly, this class is used to make sure that the number of minimum
 * shifts a {@link Doctor} does is less than or equal to its maximum number of
 * shifts
 * 
 * @author miggoncan
 */
@Slf4j
public class ShiftConfigurationValidator
		implements ConstraintValidator<ValidShiftConfiguration, ShiftConfiguration> {
	@Override
	public boolean isValid(ShiftConfiguration value, ConstraintValidatorContext context) {
		log.debug("Request to validate the shift configuration: " + value);
		if (value == null) {
			log.debug("As the shift configuration is null, it is considered valid");
			return true;
		}

		Integer min = value.getMinShifts();
		Integer max = value.getMaxShifts();
		boolean minMaxShiftsAreValid = min != null && max != null && min <= max;
		log.debug("The given shift configuration has valid min and max shifts: " + minMaxShiftsAreValid);


		return minMaxShiftsAreValid;
	}
}
