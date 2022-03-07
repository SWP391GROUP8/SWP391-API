package swp391.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import swp391.entity.File;
import swp391.service.FileService;

import java.util.List;

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

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        List<File> fileList = fileService.getAll();
        return ResponseEntity.ok().body(fileList);
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        File file = fileService.getById(id);
        return ResponseEntity.ok().body(file);
    }

}
