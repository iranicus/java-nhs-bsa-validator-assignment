package com.rDanby.domain.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// class level constraint
@Constraint(validatedBy = RegularAmountValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RegularAmountConstraint {
    String message() default "Invalid Amount";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
