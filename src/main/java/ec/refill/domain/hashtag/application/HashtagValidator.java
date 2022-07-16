package ec.refill.domain.hashtag.application;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class HashtagValidator implements ConstraintValidator<HashTagValid, List<String>> {

  @Override
  public boolean isValid(List<String> hashTags, ConstraintValidatorContext context) {
    if(hashTags.size()< 1 || hashTags.size() > 5){
      return false;
    }
    List<String> preTagList = new ArrayList<>();
    for(String tag : hashTags){
      if(!(Pattern.matches("[가-힣]+", tag)) || preTagList.contains(tag)){
        return false;
      }
      preTagList.add(tag);
    }
    return true;
  }

  @Override
  public void initialize(HashTagValid constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }
}
