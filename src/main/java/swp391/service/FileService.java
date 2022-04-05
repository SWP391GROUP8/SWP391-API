package swp391.service;

import org.springframework.web.multipart.MultipartFile;
import swp391.entity.File;

import java.util.List;

public interface FileService {
    File uploadFile(MultipartFile multipartFile, String email);

    List<File> getAll();

    File getById(Long id);

    boolean isExisted(Long fileId);

    List<File> getByUserId(String email);

    void deleteById(Long id);
}
