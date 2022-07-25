package ec.refill.domain.writing.api;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator implements ConstraintValidator<CategoryValid, String> {

  private final List<String> Categorys = new ArrayList<>(
      List.of("LITERATURE", "COLUMN", "FOREIGN", "ARTICLE", "OTHER"));


  @Override
  public boolean isValid(String category, ConstraintValidatorContext context) {
    if(Categorys.contains(category)){
      return true;
    }
    return false;
  }

  @Override
  public void initialize(CategoryValid constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }
}


