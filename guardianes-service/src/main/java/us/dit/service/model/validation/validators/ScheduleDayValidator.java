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
import us.dit.service.model.entities.Doctor;
import us.dit.service.model.entities.ScheduleDay;
import us.dit.service.model.validation.annotations.ValidScheduleDay;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This validator will make sure that if a {@link ScheduleDay} is a working day,
 * then its shifts have to be set. Otherwise, it cannot have any shifts.
 *
 * @author miggoncan
 */
@Slf4j
public class ScheduleDayValidator implements ConstraintValidator<ValidScheduleDay, ScheduleDay> {

    @Override
    public boolean isValid(ScheduleDay value, ConstraintValidatorContext context) {
        log.info("Request to validate the schedule day: " + value);
        if (value == null) {
            log.debug("The given ScheduleDay is null, which is considered a valid value");
            return true;
        }

        Boolean isWorkingDay = value.getIsWorkingDay();
        if (isWorkingDay == null) {
            log.debug("IsWorkingDay is null. The scheduleDay is not valid");
            return false;
        }

        boolean isValid = false;
        List<Doctor> shifts = value.getShifts();
        if (isWorkingDay) {
            log.debug("The day is a working day. Checking that shifts is not null and not empty");
            isValid = shifts != null && shifts.size() != 0;
        } else {
            log.debug("The day is not a working day. Checking that shifts is null or empty");
            isValid = shifts == null || shifts.size() == 0;
        }

        log.info("The given schedule day is valid: " + isValid);
        return isValid;
    }

}
