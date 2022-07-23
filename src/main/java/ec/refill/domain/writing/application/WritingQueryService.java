package ec.refill.domain.writing.application;

import ec.refill.domain.writing.dao.WritingRepository;
import ec.refill.domain.writing.domain.Writing;
import ec.refill.domain.writing.domain.WritingCategory;
import ec.refill.domain.writing.dto.WritingDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritingQueryService {
  private final WritingRepository writingRepository;

  public List<WritingDto> getWriting(int count,int pageNumber, WritingCategory category){
    PageRequest pageRequest = PageRequest.of(pageNumber, count);
    List<Writing> result = writingRepository.findByCategoryOrderByIdDesc(category,
        pageRequest);

    return result.stream().map(WritingDto::toDto)
        .collect(Collectors.toList());
  }
}
