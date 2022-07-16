package ec.refill.domain.member.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterMemberRequestTest {

  private Validator validator;

  @BeforeEach
  void setUp(){
    this.validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  @DisplayName("유효하지 않은 nickname 값으로 실패")
  public void nicknamePropertyInvalid() throws Exception {
    //given
    String invalidNickname = "ni";
    List<String> tags = List.of("태그값","태그");
    RegisterMemberRequest request = new RegisterMemberRequest(invalidNickname,"email@email.com", "password",
        tags);
    //when
    Set<ConstraintViolation<RegisterMemberRequest>> violations = validator.validate(request);
    //then
    System.out.println(violations);
    assertThat(violations).isNotEmpty();
  }

  @Test
  @DisplayName("유효한 닉네임 값 성공")
  public void nicknamePropertyValidSuccess() throws Exception {
    //given
    String invalidNickname = "닉네임";
    List<String> tags = List.of("태그값","태그");
    RegisterMemberRequest request = new RegisterMemberRequest(invalidNickname,"email@email.com", "password",
        tags);
    //when
    Set<ConstraintViolation<RegisterMemberRequest>> violations = validator.validate(request);
    //then
    System.out.println(violations);
    assertThat(violations).isEmpty();
  }

  @Test
  @DisplayName("유효하지 않은 패스워드 값")
  public void passwordPropertyInvalid() throws Exception {
    //given
    String invalidPassword = "닉네임닉네임닉네임";
    List<String> tags = List.of("태그값","태그");
    RegisterMemberRequest request = new RegisterMemberRequest("닉네임","email@email.com", invalidPassword,
        tags);
    //when
    Set<ConstraintViolation<RegisterMemberRequest>> violations = validator.validate(request);
    //then
    System.out.println(violations);
    assertThat(violations).isNotEmpty();
  }

  @Test
  @DisplayName("유효한 패스워드 값")
  public void passwordPropertyValidSuccess() throws Exception {
    //given
    String invalidPassword = "password123";
    List<String> tags = List.of("태그값","태그");
    RegisterMemberRequest request = new RegisterMemberRequest("닉네임","email@email.com", invalidPassword,
        tags);
    //when
    Set<ConstraintViolation<RegisterMemberRequest>> violations = validator.validate(request);
    //then
    System.out.println(violations);
    assertThat(violations).isEmpty();
  }

  @Test
  @DisplayName("유효하지 않은 해시태그 값(갯수)")
  public void hashTagListInvalid_isLength() throws Exception {
    //given
    List<String> tags = List.of("태그하나","태그둘","태그셋","태그넷","태그다섯","태그여섯");
    RegisterMemberRequest request = new RegisterMemberRequest("닉네임","email@email.com", "password123",
        tags);
    //when
    Set<ConstraintViolation<RegisterMemberRequest>> violations = validator.validate(request);
    //then
    System.out.println(violations);
    assertThat(violations).isNotEmpty();
  }

  @Test
  @DisplayName("유효하지 않은 해시태그 값(값)")
  public void hashTagListInvalid_isValue() throws Exception {
    //given
    List<String> tags = List.of("태그하","태그나","태그둘","password");
    RegisterMemberRequest request = new RegisterMemberRequest("닉네임","email@email.com", "password123",
        tags);
    //when
    Set<ConstraintViolation<RegisterMemberRequest>> violations = validator.validate(request);
    //then
    System.out.println(violations);
    assertThat(violations).isNotEmpty();
  }

  @Test
  @DisplayName("유효하지 않은 해시태그 값(중복)")
  public void hashTagListInvalidDuplicate() throws Exception {
    //given
    List<String> tags = List.of("태그","수필","태그","태그");
    RegisterMemberRequest request = new RegisterMemberRequest("닉네임","email@email.com", "password123",
        tags);
    //when
    Set<ConstraintViolation<RegisterMemberRequest>> violations = validator.validate(request);
    //then
    System.out.println(violations);
    assertThat(violations).isNotEmpty();
  }

  @Test
  @DisplayName("유효한 해시태그 ")
  public void hashTagListValidSuccess() throws Exception {
    //given
    List<String> tags = List.of("태그","수필","태그하나","태그둘");
    RegisterMemberRequest request = new RegisterMemberRequest("닉네임","email@email.com", "password123",
        tags);
    //when
    Set<ConstraintViolation<RegisterMemberRequest>> violations = validator.validate(request);
    //then
    System.out.println(violations);
    assertThat(violations).isEmpty();
  }
}