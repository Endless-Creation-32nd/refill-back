package ec.refill.domain.group.api;

import ec.refill.common.config.web.AuthMember;
import ec.refill.common.config.web.LoginMember;
import ec.refill.common.exception.ErrorType;
import ec.refill.common.response.JsonResponse;
import ec.refill.domain.group.application.AdminService;
import ec.refill.domain.group.application.PenaltyService;
import ec.refill.domain.group.dto.AdminParticipationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group/admin")
public class AdminApi {

  private final AdminService adminService;
  private final PenaltyService penaltyService;

  @GetMapping("/{groupId}/members")
  public ResponseEntity<?> getGroupMembers(@PathVariable("groupId") Long groupId,
      @LoginMember AuthMember member) {

    AdminParticipationResponse result = adminService.getGroupMembers(groupId, member.getId());

    return JsonResponse.okWithData(HttpStatus.OK, "그룹 멤버 조회 성공", result);
  }

  @GetMapping("/{groupId}/participation/{memberId}")
  public ResponseEntity<?> approveParticipation(@PathVariable("groupId") Long groupId,
      @PathVariable("memberId") Long memberId,
      @LoginMember AuthMember member) {
    boolean result = adminService.approve(groupId, member.getId(), memberId);
    if(result) {
      return JsonResponse.ok(HttpStatus.OK, "참여 승인");
    } else {
      return JsonResponse.fail(ErrorType.ALREADY_PARTICIPATE_GROUP, "이미 그룹에 참여중입니다");
    }
  }

  @DeleteMapping("/{groupId}/participation/{memberId}")
  public ResponseEntity<?> rejectParticipation(@PathVariable("groupId") Long groupId,
      @PathVariable("memberId") Long memberId,
      @LoginMember AuthMember member) {
    adminService.reject(groupId, member.getId(), memberId);
    return JsonResponse.ok(HttpStatus.OK, "참여 거절");
  }

  @DeleteMapping("/{groupId}/member/{memberId}")
  public ResponseEntity<?> expelParticipation(@PathVariable("groupId") Long groupId,
      @PathVariable("memberId") Long memberId,
      @LoginMember AuthMember member) {
    adminService.expel(groupId, member.getId(), memberId);
    return JsonResponse.ok(HttpStatus.OK, "참여 거절");
  }


  @PostMapping("/{groupId}/penalty/{memberId}")
  public ResponseEntity<?> addPenalty(@PathVariable("groupId") Long groupId,
      @PathVariable("memberId") Long targetMemberId,
      @LoginMember AuthMember admin) {
    penaltyService.addPenalty(targetMemberId, admin.getId(), groupId);
    return JsonResponse.ok(HttpStatus.OK, "패널티 주기 성공");
  }

  @DeleteMapping("/{groupId}/penalty/{memberId}")
  public ResponseEntity<?> cancelPenalty(@PathVariable("groupId") Long groupId,
      @PathVariable("memberId") Long targetMemberId,
      @LoginMember AuthMember admin) {
    penaltyService.cancelPenalty(targetMemberId, admin.getId(), groupId);
    return JsonResponse.ok(HttpStatus.OK, "패널티 취소 성공");
  }
}
