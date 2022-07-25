package ec.refill.domain.writing.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoryValidator.class)
public @interface CategoryValid {
  String message() default "category is not match";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
