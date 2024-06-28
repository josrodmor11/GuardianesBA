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

import us.dit.service.model.entities.Schedule;
import us.dit.service.model.entities.Schedule.ScheduleStatus;
import us.dit.service.model.entities.ScheduleDay;
import us.dit.service.model.validation.annotations.ValidSchedule;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Set;

/**
 * This validator will make sure that if a {@link Schedule} is created, it will
 * have all the necessary days within the given month. However, if it is still
 * not created, it cannot have any {@link ScheduleDay}
 * 
 * @author miggoncan
 */
@Slf4j
public class ScheduleValidator implements ConstraintValidator<ValidSchedule, Schedule> {

	public ScheduleValidator() {
	}

	@Override
	public boolean isValid(Schedule value, ConstraintValidatorContext context) {
		log.debug("Request to validate Schedule: " + value);
		if (value == null) {
			log.debug("The given Schedule is null, which is considered a valid value");
			return true;
		}

		Boolean scheduleIsValid = null;

		Integer year = value.getYear();
		Integer month = value.getMonth();
		ScheduleStatus status = value.getStatus();

		YearMonth yearMonth = null;

		if (year == null || month == null || status == null) {
			log.debug("Either the month, year ot status are null, so the schedule is invalid");
			return false;
		}
		
		try {
			yearMonth = YearMonth.of(year, month);
		} catch (DateTimeException e) {
			log.debug("The year and month are not valid " + e.getMessage());
			return false;
		}

		Set<ScheduleDay> days = value.getDays();
		// If the schedule has not yet been created, it is allowed to have a null or
		// empty list of ScheduleDays
		if (status == ScheduleStatus.NOT_CREATED || status == ScheduleStatus.BEING_GENERATED || status == ScheduleStatus.GENERATION_ERROR) {
			if (days == null || days.size() == 0) {
				log.debug("The schedule has not yet been created or is being generated. It is valid");
				scheduleIsValid = true;
			} else {
				log.debug("The schedule is marked as not created, but it has some schedule days. It is invalid");
				scheduleIsValid = false;
			}
		} else {
			log.debug("The schedule has been created. Checking all needed days are present");
			DaysOfMonthChecker<ScheduleDay> daysChecker = new DaysOfMonthChecker<>(yearMonth);
			scheduleIsValid = daysChecker.areAllDaysPresent(days);
		}

		log.debug("The schedule is valid: " + scheduleIsValid);
		return scheduleIsValid;
	}

}
