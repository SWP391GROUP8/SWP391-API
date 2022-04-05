package swp391.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.repository.FileRepository;
import swp391.repository.UserRepository;
import swp391.service.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {
    private AmazonS3 amazonS3;
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    private FileRepository fileRepository;
    private UserRepository userRepository;

    public FileServiceImpl(UserRepository userRepository,AmazonS3 amazonS3, FileRepository fileRepository) {
        this.amazonS3 = amazonS3;
        this.fileRepository = fileRepository;
        this.userRepository=userRepository;
    }
    @Override
    public swp391.entity.File uploadFile(MultipartFile multipartFile,String email) {
        swp391.entity.File newFile = new swp391.entity.File();
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            String fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();// xóa folder chứa file cần up
            newFile.setCreateDAte(LocalDate.now());
            newFile.setName(multipartFile.getOriginalFilename());
            newFile.setPath(fileUrl);
            newFile.setUser(userRepository.getUserById(email));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileRepository.save(newFile);
    }

    @Override
    public List<swp391.entity.File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public swp391.entity.File getById(Long id) {
        return fileRepository.getById(id);
    }

    @Override
    public boolean isExisted(Long fileId) {
        return fileRepository.existsById(fileId);
    }

    @Override
    public List<swp391.entity.File> getByUserId(String email) {
        return fileRepository.getFilesByUserId(email);
    }

    @Override
    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}
