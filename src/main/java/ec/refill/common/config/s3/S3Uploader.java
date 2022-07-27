package ec.refill.common.config.s3;

import com.amazonaws.services.s3.AmazonS3;
import ec.refill.common.exception.BusinessException;
import ec.refill.common.exception.ErrorType;
import ec.refill.common.property.AwsS3Property;
import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {

  private final AmazonS3 amazonS3;
  private final AwsS3Property property;
  private final String AWSURL = "https://refill-back.s3.ap-northeast-2.amazonaws.com/";
  public String uploadFile(String fileName, File file) throws IOException {
    try{
      amazonS3.putObject(property.getBucket(), fileName, file);
      return AWSURL+fileName;
    }catch (Exception e){
     throw new BusinessException(ErrorType.INTERNAL_SERVER_ERROR, "이미지 업로드 실패");
    } finally {
      if(file.delete()){
        log.info("파일 삭제 성공");
      }else {
        log.info("파일 삭제 실패");
      }
    }
  }

}
