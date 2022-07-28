package ec.refill.domain.image.application;

import ec.refill.common.config.s3.S3Uploader;
import ec.refill.domain.image.dto.ImageUploadResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

  private final S3Uploader s3Uploader;


  public ImageUploadResponse upload(MultipartFile multipartFile) throws IOException {
    File file = toFile(multipartFile);
    String fileName = createFileName(file.getName());

    String url =s3Uploader.uploadFile(fileName, file);
    return  new ImageUploadResponse(url);
  }

  private String createFileName(String name){
    return UUID.randomUUID() + name;
  }


  private File toFile(MultipartFile multipartFile) throws IOException {
    File convertFile = new File(multipartFile.getOriginalFilename());

    if(convertFile.createNewFile()) {
      FileOutputStream outputStream = new FileOutputStream(convertFile);
      outputStream.write(multipartFile.getBytes());
      outputStream.close();
      return convertFile;
    }
    throw new IOException();
  }
}
