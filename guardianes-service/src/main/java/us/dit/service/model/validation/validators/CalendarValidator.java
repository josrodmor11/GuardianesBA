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

import us.dit.service.model.entities.Calendar;
import us.dit.service.model.entities.DayConfiguration;
import us.dit.service.model.validation.annotations.ValidCalendar;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.YearMonth;

/**
 * This validator will make sure that a {@link Calendar} contains all the
 * {@link DayConfiguration}s given its month and year
 * 
 * @author miggoncan
 */
@Slf4j
public class CalendarValidator implements ConstraintValidator<ValidCalendar, Calendar> {

	@Override
	public boolean isValid(Calendar value, ConstraintValidatorContext context) {
		log.debug("Request to validate the Calendar: " + value);
		if (value == null) {
			log.debug("The given calendar is null. The calendar is considered valid");
			return true;
		}

		Integer year = value.getYear();
		Integer month = value.getMonth();
		if (year == null || month == null) {
			log.debug("Either the year or the month are null. The calendar is invalid");
			return false;
		}

		YearMonth yearMonth = null;
		try {
			yearMonth = YearMonth.of(year, month);
		} catch (DateTimeException e) {
			log.debug("The year and month are not valid: " + e.getMessage());
			return false;
		}

		DaysOfMonthChecker<DayConfiguration> checker = new DaysOfMonthChecker<>(yearMonth);
		boolean isValid = checker.areAllDaysPresent(value.getDayConfigurations());

		log.debug("The calendar is valid: " + isValid);
		return isValid;
	}

}
