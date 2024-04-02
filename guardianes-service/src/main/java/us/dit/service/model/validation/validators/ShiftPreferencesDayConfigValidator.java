package us.dit.service.model.validation.validators;

import us.dit.service.model.entities.DayConfiguration;
import us.dit.service.model.entities.Doctor;
import us.dit.service.model.validation.annotations.ValidShiftPreferences;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This validator applies the algorithm in
 * {@link ShiftPreferencesValidator} to the shift preferences of a
 * {@link DayConfiguration}
 * 
 * @author miggoncan
 */
public class ShiftPreferencesDayConfigValidator
		implements ConstraintValidator<ValidShiftPreferences, DayConfiguration> {
	@Override
	public boolean isValid(DayConfiguration value, ConstraintValidatorContext context) {
		ShiftPreferencesValidator<Doctor> validator = new ShiftPreferencesValidator<>();
		return validator.isValid(value.getUnwantedShifts(), value.getUnavailableShifts(), value.getWantedShifts(),
				value.getMandatoryShifts());
	}
}
