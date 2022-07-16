package ec.refill.domain.hashtag.application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD) // 1
@Retention(RetentionPolicy.RUNTIME) // 2
@Constraint(validatedBy = HashtagValidator.class)
public @interface HashTagValid {
  String message() default "hashtag must be String";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
