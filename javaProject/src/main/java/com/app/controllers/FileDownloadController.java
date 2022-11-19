package com.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.DatabaseFile;
import com.app.services.DatabaseFileService;
@CrossOrigin(origins = "*")
@RestController
public class FileDownloadController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @GetMapping("/downloadFile/{fileId:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int fileId, HttpServletRequest request) {
        // Load file as Resource
        DatabaseFile databaseFile = fileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }

}