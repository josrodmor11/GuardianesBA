package us.dit.model.validation.annotations;

import us.dit.model.validation.validators.CalendarValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Uses the algorithm in {@link CalendarValidator}
 *
 * @author miggoncan
 * @see CalendarValidator
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = {CalendarValidator.class})
public @interface ValidCalendar {
    String message() default "{us.dit.model.entityvalidation.ValidCalendar.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
