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

import lombok.extern.slf4j.Slf4j;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * This class will make sure that, provided a day, month and year, they are
 * valid. This is, for example, the 29th of February should be an invalid date
 * 
 * @author miggoncan
 */
@Slf4j
public class DayMonthYearValidator {
	public boolean isValid(Integer day, Integer month, Integer year) {
		log.debug("Request to validate the date: " + day + "/" + month + "/" + year);
		boolean valid = false;
		try {
			LocalDate.of(year, month, day);
			log.debug("The given date is valid");
			valid = true;
		} catch (DateTimeException e) {
			log.debug("The given date is invalid");
		}
		return valid;
	}
}
