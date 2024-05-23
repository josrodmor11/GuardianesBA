package us.dit.model.validation.validators;


import us.dit.model.entities.Absence;
import us.dit.model.validation.annotations.ValidAbsenceDates;

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
