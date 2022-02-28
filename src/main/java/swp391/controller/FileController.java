package swp391.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import swp391.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
    private FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestPart(value = "file") MultipartFile file) {

        return ResponseEntity.ok().body(fileService.uploadFile(file));
    }
}
