package swp391.service;

import org.springframework.web.multipart.MultipartFile;
import swp391.entity.File;

public interface FileService {
    File uploadFile(MultipartFile multipartFile);
}
