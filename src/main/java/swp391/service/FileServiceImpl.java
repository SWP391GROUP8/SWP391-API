package swp391.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.repository.FileRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    private AmazonS3 amazonS3;
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    private FileRepository fileRepository;

    public FileServiceImpl(AmazonS3 amazonS3, FileRepository fileRepository) {
        this.amazonS3 = amazonS3;
        this.fileRepository = fileRepository;
    }

    public swp391.entity.File uploadFile(MultipartFile multipartFile) {
        swp391.entity.File newFile = new swp391.entity.File();
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            String fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
            newFile.setCreateDAte(LocalDate.now());
            newFile.setName(multipartFile.getOriginalFilename());
            newFile.setPath(fileUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileRepository.save(newFile);
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
