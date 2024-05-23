package us.dit.model.validation.annotations;

import us.dit.model.validation.validators.ShiftConfigurationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Uses the algorithm in {@link ShiftConfigurationValidator}
 *
 * @author miggoncan
 * @see ShiftConfigurationValidator
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = {ShiftConfigurationValidator.class})
public @interface ValidShiftConfiguration {
    String message() default "{us.dit.model.entityvalidation.ValidShiftConfiguration.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
