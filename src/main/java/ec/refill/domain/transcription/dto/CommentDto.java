package ec.refill.domain.transcription.dto;

import ec.refill.domain.transcription.domain.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

  private Long commentId;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Long memberId;
  private String nickname;
  private String image;

  public static CommentDto toDto(Comment dto){
    return CommentDto.builder()
        .commentId(dto.getId())
        .content(dto.getContent())
        .createdAt(dto.getCreatedAt())
        .updatedAt(dto.getUpdatedAt())
        .memberId(dto.getMember().getId())
        .nickname(dto.getMember().getNickname())
        .image(dto.getMember().getImage())
        .build();
  }
}
