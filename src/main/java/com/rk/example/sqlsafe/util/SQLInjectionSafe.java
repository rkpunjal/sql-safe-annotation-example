package com.rk.example.sqlsafe.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SQLInjectionSafeConstraintValidator.class)
@Target( { ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInjectionSafe {

    String message() default "{SQLInjectionSafe}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}