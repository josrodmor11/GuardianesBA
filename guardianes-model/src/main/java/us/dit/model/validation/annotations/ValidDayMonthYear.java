package us.dit.model.validation.annotations;

import us.dit.model.validation.validators.DayMonthYearCycleChangeValidator;
import us.dit.model.validation.validators.DayMonthYearDayConfigurationValidator;
import us.dit.model.validation.validators.DayMonthYearScheduleDayValidator;
import us.dit.model.validation.validators.DayMonthYearValidator;

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
 * @author miggoncan
 * @see DayMonthYearValidator
 * @see DayMonthYearDayConfigurationValidator
 * @see DayMonthYearScheduleDayValidator
 * @see DayMonthYearCycleChangeValidator
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = {DayMonthYearScheduleDayValidator.class, DayMonthYearCycleChangeValidator.class,
        DayMonthYearDayConfigurationValidator.class})
public @interface ValidDayMonthYear {
    String message() default "{us.dit.model.entityvalidation.ValidDayMonthYear.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
