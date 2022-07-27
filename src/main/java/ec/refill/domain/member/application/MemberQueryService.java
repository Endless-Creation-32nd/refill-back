package ec.refill.domain.member.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.domain.Participation;
import ec.refill.domain.member.dao.MemberRepository;
import ec.refill.domain.member.domain.Member;
import ec.refill.domain.member.dto.MemberProfileDto;
import ec.refill.domain.member.dto.ProfileTranscriptionDto;
import ec.refill.domain.transcription.dao.BookMarkRepository;
import ec.refill.domain.transcription.dao.TranscriptionRepository;
import ec.refill.domain.transcription.domain.BookMark;
import ec.refill.domain.transcription.domain.Transcription;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

  private final MemberRepository memberRepository;
  private final BookMarkRepository bookMarkRepository;
  private final TranscriptionRepository transcriptionRepository;

  public MemberProfileDto getProfile(Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 멤버를 찾을 수 없습니다."));
    Long bookMarkCount = bookMarkRepository.countByMember(member);
    Long uploadCount = transcriptionRepository.countByMember(member);
    Long penaltyCount = 0L;

    Participation participation = member.participatedGroup();
    if (participation == null) {
      return MemberProfileDto.toDto(member, uploadCount, bookMarkCount, penaltyCount, null);
    }

    return MemberProfileDto.toDto(member,uploadCount,bookMarkCount,penaltyCount,participation.getGroup().getName());
  }

  public List<ProfileTranscriptionDto> getProfileTranscription(Long memberId, int pageNumber, int count) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 멤버를 찾을 수 없습니다."));
    PageRequest pageRequest = PageRequest.of(pageNumber, count);
    List<Transcription> list = transcriptionRepository.findByMemberOrderByIdDesc(
        member, pageRequest);

    return list.stream().map(ProfileTranscriptionDto::toDto).toList();
  }

  public List<ProfileTranscriptionDto> getProfileBookmark(Long memberId, int pageNumber, int count) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundResourceException("해당 멤버를 찾을 수 없습니다."));
    PageRequest pageRequest = PageRequest.of(pageNumber, count);

    List<BookMark> bookMarkList = bookMarkRepository.findByMemberOrderByCreatedAtDesc(
        member, pageRequest);

    return bookMarkList.stream().map((bookMark)-> ProfileTranscriptionDto.toDto(bookMark.getTranscription())).toList();
  }
}
