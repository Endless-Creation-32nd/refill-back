package ec.refill.domain.group.application;

import ec.refill.common.exception.NotFoundResourceException;
import ec.refill.domain.group.dao.GroupRepository;
import ec.refill.domain.group.domain.Group;
import ec.refill.domain.group.dto.GroupDetailDto;
import ec.refill.domain.group.dto.GroupDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupQueryService {

  private final GroupRepository groupRepository;

  public GroupDetailDto findOne(Long groupId){
    Group group = groupRepository.findById(groupId)
        .orElseThrow(() -> new NotFoundResourceException("해당 그룹을 찾을 수 없습니다."));
    return GroupDetailDto.toDto(group);
  }

  public List<GroupDto> findRecommendation(){
    List<Group> findGroups = groupRepository.findTop4ByOrderByIdDesc();

    return findGroups.stream().map(GroupDto::toDto).collect(Collectors.toList());
  }
}
