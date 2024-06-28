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
package us.dit.service.model.validation.annotations;

import us.dit.service.model.validation.validators.DayMonthYearCycleChangeValidator;
import us.dit.service.model.validation.validators.DayMonthYearDayConfigurationValidator;
import us.dit.service.model.validation.validators.DayMonthYearScheduleDayValidator;
import us.dit.service.model.validation.validators.DayMonthYearValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The {@link ConstraintValidator}s used to validate classes annotated with
 * {@link ValidDayMonthYear} will use the algorithm in
 * {@link DayMonthYearValidator}
 * 
 * @see DayMonthYearValidator
 * @see DayMonthYearDayConfigurationValidator
 * @see DayMonthYearScheduleDayValidator
 * @see DayMonthYearCycleChangeValidator
 * 
 * @author miggoncan
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = { DayMonthYearScheduleDayValidator.class, DayMonthYearCycleChangeValidator.class,
		DayMonthYearDayConfigurationValidator.class })
public @interface ValidDayMonthYear {
	String message() default "{us.dit.service.model.entityvalidation.ValidDayMonthYear.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
