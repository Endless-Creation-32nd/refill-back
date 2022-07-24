package ec.refill.domain.group.domain;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationId implements Serializable {

  private Long group;

  private Long member;

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ParticipationId) {

      if (Objects.equals(((ParticipationId) obj).getGroup(), group)
          && Objects.equals(((ParticipationId) obj).getMember(), member)) {
        return true;
      }else{
        return false;
      }

    }
    return false;
  }
}
