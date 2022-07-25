package ec.refill.domain.group.api;

import ec.refill.common.config.web.AuthMember;
import ec.refill.common.config.web.LoginMember;
import ec.refill.common.response.JsonResponse;
import ec.refill.domain.group.application.CreateGroupService;
import ec.refill.domain.group.application.GroupQueryService;
import ec.refill.domain.group.application.ParticipateGroupService;
import ec.refill.domain.group.dto.CreateGroupRequest;
import ec.refill.domain.group.dto.GroupDto;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupApi {

  private final CreateGroupService createGroupService;
  private final GroupQueryService groupQueryService;
  private final ParticipateGroupService participateGroupService;

  @PostMapping("")
  public ResponseEntity<?> createGroup(@Valid @RequestBody CreateGroupRequest request,
      @LoginMember AuthMember member){
    createGroupService.createGroup(request, member.getId());

    return JsonResponse.ok(HttpStatus.OK, "그룹 생성 성공");
  }

  @GetMapping("")
  public ResponseEntity<?> getMyGroup(@LoginMember AuthMember member){
    GroupDto result = groupQueryService.findMyGroup(member.getId());
    return JsonResponse.okWithData(HttpStatus.OK, "내 그룹 조회 성공", result);
  }

  @GetMapping("/recommendation")
  public ResponseEntity<?> recommendationGroup(){
    List<GroupDto> result = groupQueryService.findRecommendation();
    return JsonResponse.okWithData(HttpStatus.OK, "추천 그룹 조회 성공", result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getOneGroup(@PathVariable("id") Long groupId){
    GroupDto result = groupQueryService.findOne(groupId);
    return JsonResponse.okWithData(HttpStatus.OK, "그룹 조회 성공", result);
  }

  @GetMapping("/{id}/participation")
  public ResponseEntity<?> participateGroup(@PathVariable("id") Long groupId, @LoginMember AuthMember member){
    participateGroupService.participate(groupId,member.getId());

    return JsonResponse.ok(HttpStatus.OK,"그룹 가입 요청 성공");
  }
}
