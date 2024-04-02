package us.dit.service.model.validation.validators;


import us.dit.service.model.entities.Absence;
import us.dit.service.model.validation.annotations.ValidAbsenceDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * This class is used to validate the {@link Absence}s annotated with
 * {@link ValidAbsenceDates}
 * 
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
		LocalDate start = value.getStart();
		LocalDate end = value.getEnd();
		return start != null && end != null && start.isBefore(end);
	}
}
