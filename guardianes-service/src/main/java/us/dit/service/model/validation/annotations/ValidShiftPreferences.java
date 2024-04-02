package us.dit.service.model.validation.annotations;

import us.dit.service.model.validation.validators.ShiftPreferencesDayConfigValidator;
import us.dit.service.model.validation.validators.ShiftPreferencesShiftConfigValidator;
import us.dit.service.model.validation.validators.ShiftPreferencesValidator;

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
 * The {@link ConstraintValidator}s used to validate classes annotated by
 * {@link ValidShiftPreferences} will use the algorithm defined in
 * {@link ShiftPreferencesValidator}
 * 
 * @see ShiftPreferencesValidator
 * @see ShiftPreferencesShiftConfigValidator
 * @see ShiftPreferencesDayConfigValidator
 * 
 * @author miggoncan
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = { ShiftPreferencesShiftConfigValidator.class, ShiftPreferencesDayConfigValidator.class })
public @interface ValidShiftPreferences {
	String message() default "{us.dit.service.model.entityvalidation.ValidShiftPreferences.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
