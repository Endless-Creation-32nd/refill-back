package ec.refill.domain.member.domain;

import ec.refill.common.BaseTimeEntity;
import ec.refill.domain.hashtag.domain.HashTag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "member")
@NoArgsConstructor
@Getter
public class Member extends BaseTimeEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(nullable = false, unique = true , columnDefinition = "varchar(50)")
  private String email;

  @Column(name = "nickname", unique = true, nullable = false, columnDefinition = "varchar(8)")
  private String nickname;

  @Column(nullable = false, columnDefinition = "varchar(100)")
  private String password;

  @Column(name = "refresh_token")
  private String refreshToken;

  @OneToMany(mappedBy = "member")
  private Set<MemberHashTag> memberHashTags = new HashSet<>();

  @Builder
  public Member(String email, String nickname, String password) {
    this.email = email;
    this.nickname = nickname;
    this.password = password;
  }

  public void addMemberHashTag(MemberHashTag memberHashTag){
    memberHashTags.add(memberHashTag);
  }

  public void setRefreshToken(String token){
    this.refreshToken = token;
  }
}
