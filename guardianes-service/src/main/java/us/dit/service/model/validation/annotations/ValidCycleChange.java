package us.dit.service.model.validation.annotations;

import us.dit.service.model.validation.validators.CycleChangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Uses the algorithm in {@link CycleChangeValidator}
 * 
 * @see CycleChangeValidator
 * 
 * @author miggoncan
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = { CycleChangeValidator.class })
public @interface ValidCycleChange {
	String message() default "{us.dit.service.model.entityvalidation.ValidCycleChange.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
