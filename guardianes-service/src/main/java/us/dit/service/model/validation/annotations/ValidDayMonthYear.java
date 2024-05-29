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
